package com.simple.presence.user;

import com.simple.presence.cohort.Cohort;
import com.simple.presence.cohort.CohortService;
import com.simple.presence.infrastrcuture.exception.ServiceException;
import com.simple.presence.infrastrcuture.security.TokenService;
import com.simple.presence.user.dto.LoginOutput;
import com.simple.presence.user.dto.RegisterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.simple.presence.infrastrcuture.exception.ExceptionMessages.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final CohortService cohortService;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, TokenService tokenService, CohortService cohortService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
        this.cohortService = cohortService;
    }

    public LoginOutput login(String email, String password) {
        UserEntity user = findUserByEmail(email);

        validatePassword(password, user.getPassword());

        String generatedToken = tokenService.generateToken(user);

        return new LoginOutput(user, generatedToken);
    }

    public RegisterOutput register(String name, String email, String password, Integer cohortID) {
        existsByEmail(email);
        Cohort cohort = cohortService.findById(cohortID);

        String encodedPassword = encoder.encode(password);

        UserEntity userEntity = new UserEntity(name, email, encodedPassword, cohort);

        userEntity = userRepository.save(userEntity);

        return  new RegisterOutput(userEntity);
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new ServiceException(ENTITY_NOT_FOUND.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException(INVALID_CREDENTIALS.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(EMAIL_ALREADY_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
