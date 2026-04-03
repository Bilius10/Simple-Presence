package com.simple.presence.domain.cohort;

import com.simple.presence.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "cohorts")
public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    @Override
    @Query("SELECT c FROM Cohort c WHERE c.createdBy = ?#{principal.username}")
    List<Cohort> findAll();

    @Override
    @Query("SELECT c FROM Cohort c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    Optional<Cohort> findById(Integer id);

    @Override
    @Query("DELETE FROM Cohort c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    void deleteById(Integer id);
}
