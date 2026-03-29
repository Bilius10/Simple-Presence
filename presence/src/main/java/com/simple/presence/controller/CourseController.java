package com.simple.presence.controller;

import com.simple.presence.domain.course.CourseService;
import com.simple.presence.domain.course.dto.PatchCourseInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private CourseService courseService;

     public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PatchMapping("/patch{id}")
    public ResponseEntity<Void> patchCourse(@PathVariable Integer id, @Valid @RequestBody PatchCourseInput input) {
         courseService.patchCourse(id, input);
         return ResponseEntity.noContent().build();
    }
}
