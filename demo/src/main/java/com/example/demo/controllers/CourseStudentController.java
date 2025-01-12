package com.example.demo.controllers;

import com.example.demo.dto.CourseStudentDto;
import com.example.demo.models.CourseStudent;
import com.example.demo.models.CourseStudentId;
import com.example.demo.services.CourseStudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
public class CourseStudentController {

    private final CourseStudentService courseStudentService;

    @GetMapping("/courseStudents")
    public List<CourseStudent> getCourseStudents() {
        return courseStudentService.getAll();
    }

    @GetMapping("/courseStudent")
    public CourseStudent getCourseStudent(@RequestBody List<Long> ids) {
        return courseStudentService.get(ids.get(0), ids.get(1));
    }

    @Transactional
    @PostMapping("/courseStudents")
    public ResponseEntity<CourseStudent> saveCourse(@RequestBody CourseStudentDto dto) {
        CourseStudent newCourseStudent = courseStudentService.save(dto);
        return new ResponseEntity<CourseStudent>(newCourseStudent, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/courseStudents/{id}")
    public ResponseEntity<CourseStudent> update(@PathVariable CourseStudentId id, @RequestBody CourseStudentDto dto) {
        CourseStudent courseStudent = courseStudentService.update(id, dto);

        return new ResponseEntity<CourseStudent>(courseStudent, HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/courseStudents/{id}")
    public ResponseEntity<CourseStudent> deleteCourse(@PathVariable CourseStudentId id) {
        CourseStudent courseStudent = courseStudentService.delete(id);

        return new ResponseEntity<CourseStudent>(courseStudent, HttpStatus.ACCEPTED);
    }
}
