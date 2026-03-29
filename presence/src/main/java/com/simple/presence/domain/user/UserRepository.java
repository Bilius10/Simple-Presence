package com.simple.presence.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Override
    @Query("SELECT c FROM User c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    Optional<User> findById(Integer id);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM User c WHERE c.id = :id AND c.createdBy = ?#{principal.username}")
    void deleteById(Integer id);
}
