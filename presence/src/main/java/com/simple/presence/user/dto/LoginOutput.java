package com.simple.presence.user.dto;

import com.simple.presence.user.UserEntity;

public record LoginOutput(
        Integer id,
        String email,
        String token
) {

    public LoginOutput(UserEntity user, String token) {
        this(user.getId(), user.getEmail(), token);
    }
}
