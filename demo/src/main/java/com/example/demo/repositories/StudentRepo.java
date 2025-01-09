package com.example.demo.repositories;

import com.example.demo.models.Specialty;
import com.example.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByFacultyNumber(Integer num);

    Optional<Student> findByFacultyNumber(Integer num);

    @Modifying
    @Query("UPDATE Student SET specialty = :specialty WHERE facultyNumber = :facultyNumber")
    void updateSpecialty(Specialty specialty, Integer facultyNumber);
}
