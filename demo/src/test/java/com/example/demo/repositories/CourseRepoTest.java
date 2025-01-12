package com.example.demo.repositories;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Course;
import com.example.demo.models.Specialty;

@DataJpaTest
public class CourseRepoTest {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private SpecialtyRepo specialtyRepo;

    @BeforeEach
    void setup() {
        // Създаване и записване на Specialty
        Specialty specialty = new Specialty();
        specialty.setName("Engineering");
        specialty = specialtyRepo.save(specialty); // Specialty е вече в базата

        // Създаване и записване на Course
        Course course = new Course();
        course.setName("Mathematics");
        courseRepo.save(course); // Course е вече в базата

        // Свързване на Specialty с Course
        course.getSpecialty().add(specialty); // Променено от getSpecialties() на getSpecialty()
        courseRepo.save(course);
    }

    @Test
    void testExistsById() {
        boolean exists = courseRepo.existsById(1L);
        assertThat(exists).isTrue();
    }

    @Test
    void testFindById() {
        Course course = new Course();
        course.setName("Mathematics");
        courseRepo.save(course);
    
        Optional<Course> foundCourse = courseRepo.findById(course.getId());
        assertThat(foundCourse).isPresent(); // Проверка, че е намерено
        assertThat(foundCourse.get().getName()).isEqualTo("Mathematics");
    }

    @Test
    void testAddCourseToSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setName("Engineering");
        specialty = specialtyRepo.save(specialty);
    
        Course course = new Course();
        course.setName("Mathematics");
        course = courseRepo.save(course);
    
        course.getSpecialty().add(specialty); // Променено от getSpecialties() на getSpecialty()
        courseRepo.save(course);
    
        assertThat(course.getSpecialty()).contains(specialty); // Променено от getSpecialties() на getSpecialty()
    }

    @Test
    void testRemoveCourseFromSpecialty() {
    Specialty specialty = new Specialty();
    specialty.setName("Engineering");
    specialty = specialtyRepo.save(specialty);

    Course course = new Course();
    course.setName("Mathematics");
    course = courseRepo.save(course);

    course.getSpecialty().add(specialty);
    courseRepo.save(course);

    assertThat(courseRepo.findById(course.getId()).get().getSpecialty()).contains(specialty);

    course.getSpecialty().remove(specialty);
    courseRepo.save(course);

    assertThat(courseRepo.findById(course.getId()).get().getSpecialty()).isEmpty();
}
}
