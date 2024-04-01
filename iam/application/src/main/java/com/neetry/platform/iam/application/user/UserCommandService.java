package com.neetry.platform.iam.application.user;

import com.neetry.platform.iam.application.user.exception.EmailAlreadyRegisteredException;
import com.neetry.platform.iam.domain.common.exception.ErrorCode;
import com.neetry.platform.iam.domain.common.exception.ServiceRuntimeException;
import com.neetry.platform.iam.domain.user.Email;
import com.neetry.platform.iam.domain.user.Role;
import com.neetry.platform.iam.domain.user.User;
import com.neetry.platform.iam.domain.user.UserRepository;
import com.neetry.platform.iam.domain.user.commands.AuthenticateUserCommand;
import com.neetry.platform.iam.domain.user.commands.RegisterUserCommand;
import com.neetry.platform.iam.domain.user.commands.UpdateUserCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserCommandService {

    private static final Logger logger = LoggerFactory.getLogger(UserCommandService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long handle(RegisterUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        checkEmailNotRegistered(command.getEmail());
        User user = userRepository.save(new User(command));
        logger.debug("Successfully handled command - {}", command);
        return user.getId();
    }

    @Transactional
    public Long update(UpdateUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");

        User user = userRepository.getById(command.getId());
        if (user.getRole() != Role.USER) {
            throw new ServiceRuntimeException(String.format("No access for user ID - %s", user.getId()), 
                    ErrorCode.USER_NOT_FOUND);
        }
        user.setAddress(command.getAddress());
        user.setFirstname(command.getFirstname());
        user.setLastname(command.getLastname());
        user.setPhone(command.getPhone());
        user = userRepository.save(user);
        logger.debug("Successfully handled command - {}", command);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public void handle(AuthenticateUserCommand command) {
        logger.trace("Handling command - {}", command);
        Assert.notNull(command, "command cannot be null");
        var user = userRepository.getByEmail(command.getEmail());
        user.authenticate(command);
        logger.debug("Successfully handled command - {}", command);
    }

    @Transactional
    public Long delete(Long id) {
        logger.trace("Handling command - {}", id);
        Assert.notNull(id, "id cannot be null");

        User user = userRepository.getById(id);
        if (user.getRole() != Role.ADMIN) {
            throw new ServiceRuntimeException(String.format("No access for user ID - %s", user.getId()),
                    ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        logger.debug("Successfully deleted id - {}", id);
        return id;
    }

    private void checkEmailNotRegistered(Email email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyRegisteredException(String.format("Email - %s already registered", email));
        }
    }
}
