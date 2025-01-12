package com.example.demo.controllers;

import com.example.demo.dto.CourseDto;
import com.example.demo.models.Course;
import com.example.demo.services.CourseService;

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
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAll();
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.get(id);
    }

    @Transactional
    @PostMapping("/courses")
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDto dto) {
        Course newCourse = courseService.save(dto);
        return new ResponseEntity<Course>(newCourse, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody CourseDto dto) {
        Course course = courseService.update(id, dto);

        return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PatchMapping("/courses/{id}")
    public ResponseEntity<Course> partialUpdate(@PathVariable Long id,
            @RequestBody CourseDto dto) {
        Course course = courseService.partialUpdate(id, dto);

        return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        Course course = courseService.delete(id);

        return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping("/courses/{courseId}/addSpecialty")
    public ResponseEntity<Course> addSpecialty(@PathVariable Long courseId, @RequestBody Long specialtyId) {
        Course course = courseService.addCourseToSpecialty(courseId, specialtyId);

        return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping("/courses/{courseId}/removeSpecialty")
    public ResponseEntity<Course> removeSpecialty(@PathVariable Long courseId, @RequestBody Long specialtyId) {
        Course course = courseService.removeCourseFromSpecialty(courseId, specialtyId);

        return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
    }
}
