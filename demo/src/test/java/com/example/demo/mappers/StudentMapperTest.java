package com.example.demo.mappers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.dto.StudentDto;
import com.example.demo.models.Specialty;
import com.example.demo.models.Student;
import com.example.demo.repositories.SpecialtyRepo;

class StudentMapperTest {

    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
    private SpecialtyRepo specialtyRepo;

    @BeforeEach
    void setup() {
        specialtyRepo = mock(SpecialtyRepo.class);

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        when(specialtyRepo.findById(1L)).thenReturn(Optional.of(specialty));
    }

    @Test
    void testConvertDtoToEntity() {
        // Подготовка на входни данни
        StudentDto dto = new StudentDto(
                "John",// firstName
                "Doe",// lastName
                "john.doe@example.com", // email
                12345,// facultyNumber
                1L // specialtyId
        );

        // Добавяне на обект Specialty
        Specialty specialty = new Specialty();
        specialty.setId(1L);

        // Конвертиране от DTO към Entity
        Student student = studentMapper.convertDtoToEntity(dto);

        // Проверки
        assertNotNull(student, "Student object should not be null");
        assertEquals("John", student.getFirstName(), "First name does not match");
        assertEquals("Doe", student.getLastName(), "Last name does not match");
        assertEquals(12345, student.getFacultyNumber(), "Faculty number does not match");
        assertEquals("john.doe@example.com", student.getEmail(), "Email does not match");
        assertNotNull(student.getSpecialty(), "Specialty should not be null");
        assertEquals(1L, student.getSpecialty().getId(), "Specialty ID does not match");

        // Проверка на Specialty
        assertNotNull(student.getSpecialty(), "Specialty should not be null");
        assertEquals(1L, student.getSpecialty().getId(), "Specialty ID does not match");
    }

    @Test
void testConvertDtoToEntityWithId() {
    // Подготовка на входни данни
    StudentDto dto = new StudentDto(
        "Jane", // firstName
        "Smith", // lastName
        "jane.smith@example.com", // email
        54321, // facultyNumber
        2L // specialtyId
    );
    Long id = 10L;

    // Конвертиране от DTO към Entity с ID
    Student student = studentMapper.convertDtoToEntity(dto, id);

    // Проверки
    assertNotNull(student, "Student object should not be null");
    assertEquals("Jane", student.getFirstName(), "First name does not match");
    assertEquals("Smith", student.getLastName(), "Last name does not match");
    assertEquals(54321, student.getFacultyNumber(), "Faculty number does not match");
    assertEquals("jane.smith@example.com", student.getEmail(), "Email does not match");
    assertNotNull(student.getSpecialty(), "Specialty should not be null");
    assertEquals(2L, student.getSpecialty().getId(), "Specialty ID does not match");
    assertEquals(10L, student.getId(), "Student ID does not match");
}

@Test
void testUpdateStudentFromDto() {
    // Подготовка на входни данни
    StudentDto dto = new StudentDto(
        "Michael", // firstName
        "Johnson", // lastName
        "michael.johnson@example.com", // email
        99999, // facultyNumber
        null // specialtyId
    );

    Student existingStudent = new Student();
    existingStudent.setId(15L);
    existingStudent.setFirstName("Old FirstName");
    existingStudent.setLastName("Old LastName");
    existingStudent.setFacultyNumber(88888);
    existingStudent.setEmail("old.email@example.com");

    // Актуализация на съществуващ студент от DTO
    studentMapper.updateStudentFromDto(dto, existingStudent);

    // Проверки
    assertNotNull(existingStudent, "Student object should not be null");
    assertEquals("Michael", existingStudent.getFirstName(), "First name does not match");
    assertEquals("Johnson", existingStudent.getLastName(), "Last name does not match");
    assertEquals(99999, existingStudent.getFacultyNumber(), "Faculty number does not match");
    assertEquals("michael.johnson@example.com", existingStudent.getEmail(), "Email does not match");
    assertNull(existingStudent.getSpecialty(), "Specialty should be null");
    assertEquals(15L, existingStudent.getId(), "Student ID should not be updated");
}
}
