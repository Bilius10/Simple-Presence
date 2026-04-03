package com.simple.presence.controller;

import com.simple.presence.domain.cohort.CohortService;
import com.simple.presence.domain.cohort.dto.PatchCohortInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cohorts")
@RestController
public class CohortController {

    private CohortService cohortService;

     public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
     }

     @PatchMapping("/patch/{id}")
     public ResponseEntity<Void> patchCohort(@Valid @RequestBody PatchCohortInput input, @PathVariable Integer id) {
         cohortService.patchCohort(input.name(), input.semester(), input.year(), input.courseId(), id);
         return ResponseEntity.noContent().build();
     }
}
