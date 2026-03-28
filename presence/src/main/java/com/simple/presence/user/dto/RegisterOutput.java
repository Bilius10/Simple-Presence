package com.simple.presence.user.dto;

import com.simple.presence.user.UserEntity;

public record RegisterOutput(

        Integer id,
        String name,
        String email,
        Integer cohortID
) {

        public RegisterOutput(UserEntity userEntity) {
                this(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getEmail(),
                        userEntity.getCohort().getId()
                );
        }
}
