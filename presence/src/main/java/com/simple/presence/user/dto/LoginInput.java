package com.simple.presence.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginInput(

        @NotNull(message = "{email.not.null}")
        @NotBlank(message = "{email.not.blank}")
        @Size(max = 100, message = "{email.size.max}")
        String email,

        @NotNull(message = "{password.not.null}")
        @NotBlank(message = "{password.not.blank}")
        @Size(max = 255, message = "{password.size.max}")
        String password
) {
}
