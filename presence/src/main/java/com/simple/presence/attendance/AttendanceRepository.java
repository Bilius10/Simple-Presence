package com.simple.presence.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "attendance")
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
