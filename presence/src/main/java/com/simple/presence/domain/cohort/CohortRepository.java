package com.simple.presence.domain.cohort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(path = "cohorts")
public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    Optional<Cohort> findById(Integer id);
}
