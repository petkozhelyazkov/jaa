package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CourseStudentDto;
import com.example.demo.mappers.CourseStudentMapper;
import com.example.demo.models.CourseStudent;
import com.example.demo.models.CourseStudentId;
import com.example.demo.repositories.CourseStudentRepo;

@Service
public class CourseStudentService {

    private final CourseStudentMapper courseStudentMapper;
    private final CourseStudentRepo courseStudentRepo;

    public CourseStudentService(CourseStudentMapper courseStudentMapper,
            CourseStudentRepo courseStudentRepository) {
        this.courseStudentMapper = courseStudentMapper;
        this.courseStudentRepo = courseStudentRepository;
    }

    public CourseStudent get(Long courseId, Long studentId) {
        CourseStudent courseStudent = courseStudentRepo.findById(new CourseStudentId(courseId, studentId))
                .orElseThrow();

        return courseStudent;
    }

    public List<CourseStudent> getAll() {
        return courseStudentRepo.findAll();
    }

    @Transactional
    public CourseStudent save(CourseStudentDto dto) {
        CourseStudent courseStudent = courseStudentMapper.convertDtoToEntity(dto);
        return courseStudentRepo.saveAndFlush(courseStudent);
    }

    @Transactional
    public CourseStudent update(CourseStudentId id, CourseStudentDto dto) {
        Optional<CourseStudent> courseStudent = courseStudentRepo.findById(id);
        if (courseStudent.isPresent()) {
            CourseStudent temp = courseStudentMapper.convertDtoToEntity(dto);
            return courseStudentRepo.saveAndFlush(temp);
        }

        return null;
    }

    @Transactional
    public CourseStudent delete(CourseStudentId id) {
        Optional<CourseStudent> courseStudent = courseStudentRepo.findById(id);
        if (courseStudent.isPresent()) {
            CourseStudent temp = courseStudent.get();
            courseStudentRepo.delete(temp);
            return temp;
        }

        return null;
    }
}
