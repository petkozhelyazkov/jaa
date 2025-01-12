package com.example.demo.repositories;

import com.example.demo.models.CourseStudent;
import com.example.demo.models.CourseStudentId;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepo extends JpaRepository<CourseStudent, CourseStudentId> {
    boolean existsById(CourseStudentId id);

    Optional<CourseStudent> findById(CourseStudentId id);
}
