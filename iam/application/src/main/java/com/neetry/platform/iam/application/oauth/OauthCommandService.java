package com.neetry.platform.iam.application.oauth;

import com.neetry.platform.iam.application.user.UserCommandService;
import com.neetry.platform.iam.domain.oauth.jwt.CreateJwtCommand;
import com.neetry.platform.iam.domain.oauth.jwt.JwtService;
import com.neetry.platform.iam.domain.oauth.jwt.JwtUserInfo;
import com.neetry.platform.iam.domain.user.commands.AuthenticateUserCommand;
import com.neetry.platform.iam.domain.user.exception.UserNotFoundException;
import com.neetry.platform.iam.domain.user.view.UserInfo;
import com.neetry.platform.iam.domain.user.view.UserQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.Instant;

@Service
public class OauthCommandService {

    private static final Logger logger = LoggerFactory.getLogger(OauthCommandService.class);

    private final UserCommandService userCommandService;
    private final UserQueryRepository userQueryRepository;
    private final JwtService jwtService;
    private final Duration accessTokenExpiration;
    private final Duration refreshTokenExpiration;

    @Autowired
    public OauthCommandService(
            UserCommandService userCommandService,
            UserQueryRepository userQueryRepository, JwtService jwtService,
            @Value("${neetry.platform.iam.accessTokenExpirationInHours}") int accessTokenExpiration,
            @Value("${neetry.platform.iam.refreshTokenExpirationInHours}") int refreshTokenExpiration) {
        this.userCommandService = userCommandService;
        this.userQueryRepository = userQueryRepository;
        this.jwtService = jwtService;
        this.accessTokenExpiration = Duration.ofHours(accessTokenExpiration);
        this.refreshTokenExpiration = Duration.ofHours(refreshTokenExpiration);
    }

    @Transactional(readOnly = true)
    public OauthToken grant(JwtTokenGrantCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        userCommandService.handle(new AuthenticateUserCommand(command.getEmail(), command.getPassword()));
        var userInfo = userQueryRepository.findUserByEmail(command.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found by email - %s", command.getEmail())));
        String accessJwtToken = createAccessToken(userInfo);
        String refreshJwtToken = createRefreshToken(userInfo);
        logger.debug("Successfully handled command - {}", command);
        return OauthToken.bearerTokenOf(accessJwtToken, refreshJwtToken);
    }

    private String createAccessToken(JwtUserInfo jwtUserInfo) {
        var issuedAt = Instant.now();
        var expiresAt = issuedAt.plusSeconds(accessTokenExpiration.toSeconds());
        return jwtService.createJwt(new CreateJwtCommand(jwtUserInfo, issuedAt, expiresAt));
    }

    private String createAccessToken(UserInfo userInfo) {
        return createAccessToken(JwtUserInfo.of(userInfo));
    }

    private String createRefreshToken(JwtUserInfo jwtUserInfo) {
        var issuedAt = Instant.now();
        var expiresAt = issuedAt.plusSeconds(refreshTokenExpiration.toSeconds());
        return jwtService.createJwt(new CreateJwtCommand(jwtUserInfo, issuedAt, expiresAt));
    }

    private String createRefreshToken(UserInfo userInfo) {
        return createRefreshToken(JwtUserInfo.of(userInfo));
    }
}
