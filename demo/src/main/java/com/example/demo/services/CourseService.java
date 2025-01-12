package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CourseDto;
import com.example.demo.mappers.CourseMapper;
import com.example.demo.models.Course;
import com.example.demo.models.Specialty;
import com.example.demo.repositories.CourseRepo;
import com.example.demo.repositories.SpecialtyRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final SpecialtyRepo specialtyRepo;

    public Course get(Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        }

        return null;
    }

    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Transactional
    public Course save(CourseDto dto) {
        Course course = courseMapper.convertDtoToEntity(dto);
        return courseRepo.saveAndFlush(course);
    }

    @Transactional
    public Course update(Long courseId, CourseDto dto) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            Long id = course.get().getId();
            Course temp = courseMapper.convertDtoToEntity(dto, id);
            return courseRepo.saveAndFlush(temp);
        }

        return null;
    }

    @Transactional
    public Course partialUpdate(Long courseId, CourseDto dto) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            Course temp = course.get();
            courseMapper.updateCourseFromDto(dto, temp);
            return courseRepo.saveAndFlush(temp);
        }

        return null;
    }

    @Transactional
    public Course delete(Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            Course temp = course.get();
            courseRepo.delete(temp);
            return temp;
        }

        return null;
    }

    @Transactional
    public Course addCourseToSpecialty(Long courseId, Long specialtyId) {
        Optional<Course> course = courseRepo.findById(courseId);
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);

        if (course.isPresent() && specialty.isPresent()) {
            Course temp = course.get();

            courseRepo.addCourseToSpecialty(temp.getId(), specialty.get().getId());
            return temp;
        }

        return null;
    }

    @Transactional
    public Course removeCourseFromSpecialty(Long courseId, Long specialtyId) {
        Optional<Course> course = courseRepo.findById(courseId);
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);

        if (course.isPresent() && specialty.isPresent()) {
            Course temp = course.get();
            specialtyRepo.removeCourseFromSpecialty(temp.getId(), specialty.get().getId());
            return temp;
        }
        return null;
    }
    
}
