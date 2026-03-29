package com.simple.presence.controller;

import com.simple.presence.domain.user.UserService;
import com.simple.presence.domain.user.dto.LoginInput;
import com.simple.presence.domain.user.dto.LoginOutput;
import com.simple.presence.domain.user.dto.RegisterInput;
import com.simple.presence.domain.user.dto.RegisterOutput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("api/auth")
@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@Valid @RequestBody LoginInput loginInput) {
        LoginOutput login = userService.login(loginInput.email(), loginInput.password());

        return ResponseEntity.ok().body(login);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterOutput> register(@Valid @RequestBody RegisterInput registerInput) {
        RegisterOutput register = userService.register(registerInput.name(), registerInput.email(),
                registerInput.password());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(register.id())
                .toUri();

        return ResponseEntity.created(location).body(register);
    }
}
