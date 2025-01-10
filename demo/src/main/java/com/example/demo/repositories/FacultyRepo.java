package com.example.demo.repositories;

import com.example.demo.models.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    boolean existsById(Long num);

    Optional<Faculty> findById(Long num);
}
