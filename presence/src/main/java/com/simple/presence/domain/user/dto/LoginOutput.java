package com.simple.presence.domain.user.dto;

import com.simple.presence.domain.user.User;

public record LoginOutput(
        Integer id,
        String email,
        String token
) {

    public LoginOutput(User user, String token) {
        this(user.getId(), user.getEmail(), token);
    }
}
