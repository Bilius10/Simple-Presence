package com.simple.presence.domain.cohort.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatchCohortInput(

        @NotNull(message = "{cohort.name.not.null}")
        @NotBlank(message = "{cohort.name.not.blank}")
        @Size(max = 50, message = "{cohort.name.size.max}")
        String name,

        @NotNull(message = "{cohort.course.not.null}")
        @Positive(message = "{cohort.course.positive}")
        Integer courseId,

        @Size(max = 10, message = "{cohort.semester.size.max}")
        @NotNull(message = "{cohort.semester.not.null}")
        @Positive(message = "{cohort.semester.positive}")
        String semester,

        @NotNull(message = "{cohort.year.not.null}")
        @Positive(message = "{cohort.year.positive}")
        Integer year
) {
}
