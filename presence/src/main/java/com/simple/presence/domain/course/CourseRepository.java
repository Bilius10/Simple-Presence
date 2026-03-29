package com.simple.presence.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "courses")
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Override
    @Query("SELECT c FROM Course c WHERE c.createdBy = ?#{principal.username}")
    List<Course> findAll();

    @Override
    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    Optional<Course> findById(Integer id);

    @Override
    @Query("DELETE FROM Course c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    void deleteById(Integer id);
}
