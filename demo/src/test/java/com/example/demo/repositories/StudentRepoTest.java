package com.example.demo.repositories;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Specialty;
import com.example.demo.models.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SpecialtyRepo specialtyRepo;

    @Test
    void testSaveStudent() {
        // Създаване на специалност
        Specialty specialty = new Specialty();
        specialty.setName("Computer Science");
        Specialty savedSpecialty = specialtyRepo.save(specialty);

        // Създаване и запис на студент
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setFacultyNumber(12345);
        student.setEmail("john.doe@example.com");
        student.setSpecialty(savedSpecialty);

        Student savedStudent = studentRepo.save(student);

        // Проверка
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getSpecialty()).isEqualTo(savedSpecialty);
    }

    @Test
    void testExistsByFacultyNumber() {
        // Създаване и запис на студент
        Student student = new Student();
        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setFacultyNumber(54321);
        student.setEmail("jane.smith@example.com");
        studentRepo.save(student);

        // Проверка за съществуване
        boolean exists = studentRepo.existsByFacultyNumber(54321);
        assertThat(exists).isTrue();
    }

    @Test
    void testFindByFacultyNumber() {
        // Създаване и запис на студент
        Student student = new Student();
        student.setFirstName("Alice");
        student.setLastName("Johnson");
        student.setFacultyNumber(67890);
        student.setEmail("alice.johnson@example.com");
        studentRepo.save(student);

        // Търсене по facultyNumber
        Optional<Student> foundStudent = studentRepo.findByFacultyNumber(67890);

        // Проверка
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getFirstName()).isEqualTo("Alice");
        assertThat(foundStudent.get().getLastName()).isEqualTo("Johnson");
    }

   @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void testUpdateSpecialty() {
        // Създаване на специалности
        Specialty specialtyMath = new Specialty();
        specialtyMath.setName("Mathematics");
        Specialty savedSpecialtyMath = specialtyRepo.saveAndFlush(specialtyMath);
    
        Specialty specialtyPhysics = new Specialty();
        specialtyPhysics.setName("Physics");
        Specialty savedSpecialtyPhysics = specialtyRepo.saveAndFlush(specialtyPhysics);
    
        // Създаване на студент
        Student student = new Student();
        student.setFirstName("Alice");
        student.setLastName("Johnson");
        student.setFacultyNumber(12345);
        student.setEmail("alice.johnson@example.com");
        student.setSpecialty(savedSpecialtyMath);
        studentRepo.saveAndFlush(student);
    
        // Актуализиране на специалността
        int updatedRows = studentRepo.updateSpecialty(savedSpecialtyPhysics, 12345);
        assertThat(updatedRows).isGreaterThan(0);
    
        // Изчистване на кеша и проверка
        entityManager.clear();
        Optional<Student> updatedStudent = studentRepo.findByFacultyNumber(12345);
        assertThat(updatedStudent).isPresent();
        assertThat(updatedStudent.get().getSpecialty()).isEqualTo(savedSpecialtyPhysics);
    }
}
