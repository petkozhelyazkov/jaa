package com.example.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Course;
import com.example.demo.models.Faculty;
import com.example.demo.models.Specialty;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
public class SpecialtyRepoTest {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private SpecialtyRepo specialtyRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    @Test
    void testSaveSpecialty() {
        // Създаване на нова специалност и запис
        Faculty faculty = new Faculty();
        faculty.setName("Engineering");
        faculty = facultyRepo.save(faculty);

        Specialty specialty = new Specialty();
        specialty.setName("Computer Science");
        specialty.setFaculty(faculty);

        Specialty savedSpecialty = specialtyRepo.save(specialty);

        assertThat(savedSpecialty).isNotNull();
        assertThat(savedSpecialty.getId()).isNotNull();
        assertThat(savedSpecialty.getFaculty()).isEqualTo(faculty);
    }

    @Test
    void testExistsById() {
        // Създаване и запис на специалност
        Faculty faculty = new Faculty();
        faculty.setName("Mathematics");
        faculty = facultyRepo.save(faculty);

        Specialty specialty = new Specialty();
        specialty.setName("Applied Math");
        specialty.setFaculty(faculty);
        Specialty savedSpecialty = specialtyRepo.save(specialty);

        // Проверка за съществуване
        boolean exists = specialtyRepo.existsById(savedSpecialty.getId());
        assertThat(exists).isTrue();
    }

    @Test
    void testUpdateFaculty() {
        // Създаване на два факултета
        Faculty faculty1 = new Faculty();
        faculty1.setName("Science");
        faculty1 = facultyRepo.save(faculty1);
    
        Faculty faculty2 = new Faculty();
        faculty2.setName("Arts");
        faculty2 = facultyRepo.save(faculty2);
    
        // Създаване на специалност и свързване с първия факултет
        Specialty specialty = new Specialty();
        specialty.setName("Physics");
        specialty.setFaculty(faculty1);
        Specialty savedSpecialty = specialtyRepo.save(specialty);
    
        // Актуализиране на факултета
        specialtyRepo.updateFaculty(faculty2, savedSpecialty.getId());
        entityManager.flush();
        entityManager.clear();
    
        // Зареждане на обновената специалност
        Specialty updatedSpecialty = specialtyRepo.findById(savedSpecialty.getId()).orElseThrow();
    
        // Проверка
        assertThat(updatedSpecialty.getFaculty().getId()).isEqualTo(faculty2.getId());
        assertThat(updatedSpecialty.getFaculty().getName()).isEqualTo(faculty2.getName());
    }

    @Test
    void testRemoveCourseFromSpecialty() {
    // Създаване на курс
    Course course = new Course();
    course.setName("Mathematics");
    course = courseRepo.save(course); // Уверете се, че имате `courseRepo`

    // Създаване на специалност
    Specialty specialty = new Specialty();
    specialty.setName("Algebra");
    specialty = specialtyRepo.save(specialty);

    // Добавяне на връзка между курс и специалност
    specialtyRepo.addCourseToSpecialty(course.getId(), specialty.getId());

    // Премахване на връзката
    specialtyRepo.removeCourseFromSpecialty(course.getId(), specialty.getId());

    // Проверка: няма грешки при изпълнението
    assertThat(specialtyRepo.existsById(specialty.getId())).isTrue();
}

@Autowired
private EntityManager entityManager;
}
