package com.simple.presence.domain.user.dto;

import com.simple.presence.domain.user.User;

public record RegisterOutput(

        Integer id,
        String name,
        String email,
        Integer cohortID
) {

        public RegisterOutput(User user) {
                this(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCohort().getId()
                );
        }
}
