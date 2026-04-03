package com.simple.presence.domain.cohort;

import com.simple.presence.domain.course.Course;
import com.simple.presence.domain.course.CourseService;
import com.simple.presence.infrastrcuture.exception.ServiceException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.simple.presence.infrastrcuture.exception.ExceptionMessages.ENTITY_NOT_FOUND;

@Service
public class CohortService {

    private final CohortRepository cohortRepository;
    private final CourseService courseService;

    public CohortService(CohortRepository cohortRepository, CourseService courseService) {
        this.cohortRepository = cohortRepository;
        this.courseService = courseService;
    }

    public void patchCohort(String name,  String semester, Integer year,  Integer courseId, Integer id) {
        Cohort cohort = findCohortById(id);
        Course course = courseService.findCourseById(courseId);
        cohort.update(name, course, semester, year);
        cohortRepository.save(cohort);
    }

    private Cohort findCohortById(Integer id) {
        return cohortRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ENTITY_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
