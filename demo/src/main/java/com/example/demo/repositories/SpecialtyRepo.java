package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Faculty;
import com.example.demo.models.Specialty;

@Repository
public interface SpecialtyRepo extends JpaRepository<Specialty, Long> {

    boolean existsById(Long num);

    Optional<Specialty> findById(Long num);

    @Modifying
    @Query("UPDATE Specialty SET faculty = :faculty WHERE id =:specialtyId")
    void updateFaculty(Faculty faculty, Long specialtyId);
}