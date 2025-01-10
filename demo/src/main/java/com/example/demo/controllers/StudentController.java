package com.example.demo.controllers;

import com.example.demo.dto.StudentDto;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;

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
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAll();
    }

    @GetMapping("/students/{facultyNumber}")
    public Student getStudent(@PathVariable Integer facultyNumber) {
        return studentService.get(facultyNumber);
    }

    @Transactional
    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto dto) {
        Student newStudent = studentService.save(dto);
        return new ResponseEntity<Student>(newStudent, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/students/{facultyNumber}")
    public ResponseEntity<Student> update(@PathVariable Integer facultyNumber, @RequestBody StudentDto dto) {
        Student student = studentService.update(facultyNumber, dto);

        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PatchMapping("/students/{facultyNumber}")
    public ResponseEntity<Student> partialUpdate(@PathVariable Integer facultyNumber, @RequestBody StudentDto dto) {
        Student student = studentService.partialUpdate(facultyNumber, dto);

        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/students/{facultyNumber}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer facultyNumber) {
        Student student = studentService.delete(facultyNumber);

        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PutMapping("/students/{facultyNumber}/specialty")
    public ResponseEntity<Student> updateSpecialty(@PathVariable Integer facultyNumber, @RequestBody Long specialtyId) {
        Student student = studentService.updateSpecialty(facultyNumber, specialtyId);

        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }
}