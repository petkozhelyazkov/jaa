package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Faculty;
import com.example.demo.models.Specialty;

import jakarta.transaction.Transactional;


@Repository
public interface SpecialtyRepo extends JpaRepository<Specialty, Long> {

    boolean existsById(Long num);

    Optional<Specialty> findById(Long num);

    @Modifying
    @Transactional
    @Query("UPDATE Specialty s SET s.faculty = :faculty WHERE s.id = :specialtyId")
    void updateFaculty(@Param("faculty") Faculty faculty, @Param("specialtyId") Long specialtyId);

     // Добавен метод за премахване на връзката между курс и специалност
     @Modifying
     @Transactional
     @Query(value = "DELETE FROM course_specialty WHERE course_id = ?1 AND specialty_id = ?2", nativeQuery = true)
     void removeCourseFromSpecialty(Long courseId, Long specialtyId);
 
     // Добавен метод за добавяне на връзка между курс и специалност
     @Modifying
     @Transactional
     @Query(value = "INSERT INTO course_specialty (course_id, specialty_id) VALUES (?1, ?2)", nativeQuery = true)
     void addCourseToSpecialty(Long courseId, Long specialtyId);

     

}