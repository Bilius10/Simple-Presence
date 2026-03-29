package com.simple.presence.domain.course;

import com.simple.presence.domain.course.dto.PatchCourseInput;
import com.simple.presence.infrastrcuture.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.simple.presence.infrastrcuture.exception.ExceptionMessages.ENTITY_NOT_FOUND;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void patchCourse(Integer id, PatchCourseInput input) {
        Course course = findCourseById(id);
        course = course.update(input.name(), input.description());
        courseRepository.save(course);
    }

    private Course findCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ENTITY_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
