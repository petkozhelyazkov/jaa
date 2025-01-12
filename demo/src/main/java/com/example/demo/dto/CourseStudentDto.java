package com.example.demo.dto;

public class CourseStudentDto {

    private Long courseId;
    private Long studentId;
    private Double grade;

    public CourseStudentDto() {
    }

    public CourseStudentDto(Long courseId, Long studentId, Double grade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
