package com.neetry.platform.iam.infrastructure.inbound.rest.controller;


import com.neetry.platform.iam.application.oauth.JwtTokenGrantCommand;
import com.neetry.platform.iam.application.oauth.OauthCommandService;
import com.neetry.platform.iam.application.oauth.OauthToken;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.request.GrantOauthTokenRequest;
import com.neetry.platform.iam.infrastructure.inbound.rest.model.response.OauthTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth/token")
@Tag(name = "Token")
public class OauthController {

    private final OauthCommandService oauthCommandService;

    @Autowired
    public OauthController(OauthCommandService oauthCommandService) {
        this.oauthCommandService = oauthCommandService;
    }

    @PostMapping
    @Operation(summary = "Grant OAUTH Token")
    public OauthTokenResponse grantToken(
            @RequestBody GrantOauthTokenRequest tokenRequest) {
        OauthToken oauthToken = oauthCommandService.grant(new JwtTokenGrantCommand(tokenRequest.getUserName(),
                tokenRequest.getPassword()));
        return OauthTokenResponse.from(oauthToken);
    }
}
