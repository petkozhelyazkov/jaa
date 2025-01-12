package com.example.demo.repositories;

import com.example.demo.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    boolean existsById(Long num);

    Optional<Course> findById(Long num);

    @Modifying
    @Query(value = "INSERT INTO course_specialty(course_id, specialty_id) VALUES (?,?)", nativeQuery = true)
    void addCourseToSpecialty(Long courseId, Long specialtyId);

    @Modifying
    @Query(value = "DELETE FROM course_specialty WHERE courseId= ? AND specialtyID=?", nativeQuery = true)
    void removeCourseFromSpecialty(Long courseId, Long specialtyId);
}