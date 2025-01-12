package com.example.demo.mappers;

import com.example.demo.models.CourseStudent;
import com.example.demo.models.CourseStudentId;
import com.example.demo.dto.CourseStudentDto;

import org.springframework.stereotype.Component;

@Component
public class CourseStudentMapper {

    public CourseStudentDto toDto(CourseStudent courseStudent) {
        if (courseStudent == null) {
            return null;
        }

        Long courseId = courseStudent.getId().getCourseId();
        Long studentId = courseStudent.getId().getStudentId();
        Double grade = courseStudent.getGrade();

        return new CourseStudentDto(courseId, studentId, grade);
    }

    public CourseStudent convertDtoToEntity(CourseStudentDto courseStudentDTO) {
        if (courseStudentDTO == null) {
            return null;
        }

        CourseStudentId courseStudentId = new CourseStudentId(courseStudentDTO.getCourseId(),
                courseStudentDTO.getStudentId());

        return new CourseStudent(courseStudentId.getCourseId(), courseStudentId.getStudentId(),
                courseStudentDTO.getGrade());
    }

}
