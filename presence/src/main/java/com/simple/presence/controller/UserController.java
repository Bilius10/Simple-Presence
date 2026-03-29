package com.simple.presence.controller;

import com.simple.presence.domain.user.User;
import com.simple.presence.domain.user.UserService;
import com.simple.presence.domain.user.dto.PatchUserInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/patch")
    public ResponseEntity<Void> patchUser(@AuthenticationPrincipal User currentUser, @Valid @RequestBody PatchUserInput input) {
        userService.patch(currentUser.getId(), input.name(), input.email());
        return ResponseEntity.noContent().build();
    }
}
