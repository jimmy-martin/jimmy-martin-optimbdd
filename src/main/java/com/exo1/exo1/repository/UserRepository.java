package com.exo1.exo1.repository;

import com.exo1.exo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.projets p " +
            "LEFT JOIN FETCH p.tasks t " +
            "LEFT JOIN FETCH u.task")
    List<User> findAllWithProjects();

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.projets p " +
            "LEFT JOIN FETCH p.tasks t " +
            "LEFT JOIN FETCH u.task " +
            "WHERE u.id = :id")
    Optional<User> findByIdWithProjects(@Param("id") Long id);
}
