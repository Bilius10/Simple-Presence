package com.simple.presence.domain.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatchCourseInput(

        @NotNull(message = "{course.name.not.null}")
        @NotBlank(message = "{course.name.not.blank}")
        String name,

        @NotNull(message = "{course.description.not.null}")
        @NotBlank(message = "{course.description.not.blank}")
        String description
) {
}
