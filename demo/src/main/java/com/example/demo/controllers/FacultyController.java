package com.example.demo.controllers;

import com.example.demo.dto.FacultyDto;
import com.example.demo.models.Faculty;
import com.example.demo.services.FacultyService;

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
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/faculties")
    public List<Faculty> getStudents() {
        return facultyService.getAll();
    }

    @GetMapping("/faculties/{id}")
    public Faculty getStudent(@PathVariable Long id) {
        return facultyService.get(id);
    }

    @Transactional
    @PostMapping("/faculties")
    public ResponseEntity<Faculty> saveStudent(@RequestBody FacultyDto dto) {
        Faculty newFaculty = facultyService.save(dto);
        return new ResponseEntity<Faculty>(newFaculty, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/faculties/{id}")
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestBody FacultyDto dto) {
        Faculty faculty = facultyService.update(id, dto);

        return new ResponseEntity<Faculty>(faculty, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PatchMapping("/faculties/{id}")
    public ResponseEntity<Faculty> partialUpdate(@PathVariable Long id, @RequestBody FacultyDto dto) {
        Faculty faculty = facultyService.partialUpdate(id, dto);

        return new ResponseEntity<Faculty>(faculty, HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<Faculty> deleteSpecialty(@PathVariable Long id) {
        Faculty faculty = facultyService.delete(id);

        return new ResponseEntity<Faculty>(faculty, HttpStatus.ACCEPTED);
    }
}
