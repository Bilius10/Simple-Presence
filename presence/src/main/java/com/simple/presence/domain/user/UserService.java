package com.simple.presence.domain.user;

import com.simple.presence.domain.cohort.Cohort;
import com.simple.presence.domain.cohort.CohortService;
import com.simple.presence.infrastrcuture.exception.ServiceException;
import com.simple.presence.infrastrcuture.security.TokenService;
import com.simple.presence.domain.user.dto.LoginOutput;
import com.simple.presence.domain.user.dto.RegisterOutput;
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
        User user = findUserByEmail(email);

        validatePassword(password, user.getPassword());

        String generatedToken = tokenService.generateToken(user);

        return new LoginOutput(user, generatedToken);
    }

    public RegisterOutput register(String name, String email, String password, Integer cohortID) {
        existsByEmail(email);
        Cohort cohort = cohortService.findById(cohortID);

        String encodedPassword = encoder.encode(password);

        User user = new User(name, email, encodedPassword, cohort);

        user = userRepository.save(user);

        return  new RegisterOutput(user);
    }

    public void patch(Integer id, String name, String email) {
        User user = findUserById(id);
        user = user.update(name, email);
        userRepository.save(user);
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new ServiceException(ENTITY_NOT_FOUND.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException(INVALID_CREDENTIALS.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(EMAIL_ALREADY_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ENTITY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND));
    }
}
