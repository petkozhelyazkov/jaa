package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Specialty;
import com.example.demo.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByFacultyNumber(Integer num);

    Optional<Student> findByFacultyNumber(Integer num);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.specialty = :specialty WHERE s.facultyNumber = :facultyNumber")
    int updateSpecialty(@Param("specialty") Specialty specialty, @Param("facultyNumber") int facultyNumber);
}
