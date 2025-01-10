package com.example.demo.controllers;

import com.example.demo.dto.SpecialtyDto;
import com.example.demo.models.Specialty;
import com.example.demo.services.SpecialtyService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping("/specialties")
    public List<Specialty> getStudents() {
        return specialtyService.getAll();
    }

    @GetMapping("/specialties/{id}")
    public Specialty getStudent(@PathVariable Long id) {
        return specialtyService.get(id);
    }

    @Transactional
    @PostMapping("/specialties")
    public ResponseEntity<Specialty> saveStudent(@RequestBody SpecialtyDto dto) {
        Specialty newSpecialty = specialtyService.save(dto);
        return new ResponseEntity<Specialty>(newSpecialty, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/specialties/{id}")
    public ResponseEntity<Specialty> update(@PathVariable Long id, @RequestBody SpecialtyDto dto) {
        Specialty specialty = specialtyService.update(id, dto);

        return new ResponseEntity<Specialty>(specialty, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PatchMapping("/specialties/{id}")
    public ResponseEntity<Specialty> partialUpdate(@PathVariable Long id, @RequestBody SpecialtyDto dto) {
        Specialty specialty = specialtyService.partialUpdate(id, dto);

        return new ResponseEntity<Specialty>(specialty, HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/specialties/{id}")
    public ResponseEntity<Specialty> deleteSpecialty(@PathVariable Long id) {
        Specialty specialty = specialtyService.delete(id);

        return new ResponseEntity<Specialty>(specialty, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PutMapping("/specialties/{specialtyId}/faculty")
    public ResponseEntity<Specialty> updateFaculty(@PathVariable Long specialtyId, @RequestBody Long facultyId) {
        Specialty specialty = specialtyService.updateFaculty(specialtyId, facultyId);

        return new ResponseEntity<Specialty>(specialty, HttpStatus.ACCEPTED);
    }
}