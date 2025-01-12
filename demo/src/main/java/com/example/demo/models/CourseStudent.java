package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @EmbeddedId
    private CourseStudentId id;

    @Column(name = "grade")
    private Double grade;

    public CourseStudent() {
    }

    public CourseStudent(Course course, Student student, Double grade) {
        this.grade = grade;
        this.id = new CourseStudentId(course.getId(), student.getId());
    }

    public CourseStudent(Long courseId, Long studentId, Double grade) {
        this.grade = grade;
        this.id = new CourseStudentId(courseId, studentId);
    }

    public CourseStudentId getId() {
        return id;
    }

    public void setId(CourseStudentId id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
