package com.example.demo.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.SpecialtyDto;
import com.example.demo.models.Faculty;
import com.example.demo.models.Specialty;


class SpecialtyMapperTest {

    private final SpecialtyMapper specialtyMapper = Mappers.getMapper(SpecialtyMapper.class);

    @Test
    void testConvertDtoToEntity() {
        // Подготовка на входни данни
        SpecialtyDto dto = new SpecialtyDto("Computer Science", 1L);

        // Конвертиране от DTO към Entity
        Specialty specialty = specialtyMapper.convertDtoToEntity(dto);

        // Assertions
        assertNotNull(specialty, "Specialty object should not be null");
        assertEquals("Computer Science", specialty.getName(), "Specialty name does not match");
        assertNotNull(specialty.getFaculty(), "Faculty should not be null");
        assertEquals(1L, specialty.getFaculty().getId(), "Faculty ID does not match");
        assertNull(specialty.getId(), "Specialty ID should be null");
    }

    @Test
    void testConvertDtoToEntityWithId() {
        // Подготовка на входни данни
        SpecialtyDto dto = new SpecialtyDto("Mathematics", 2L);
        Long id = 3L;

        // Конвертиране от DTO към Entity с ID
        Specialty specialty = specialtyMapper.convertDtoToEntity(dto, id);

        // Assertions
        assertNotNull(specialty, "Specialty object should not be null");
        assertEquals("Mathematics", specialty.getName(), "Specialty name does not match");
        assertNotNull(specialty.getFaculty(), "Faculty should not be null");
        assertEquals(2L, specialty.getFaculty().getId(), "Faculty ID does not match");
        assertEquals(3L, specialty.getId(), "Specialty ID does not match");
    }

    @Test
    void testUpdateSpecialtyFromDto() {
        // Подготовка на входни данни
        SpecialtyDto dto = new SpecialtyDto("Physics", null);
        Specialty existingSpecialty = new Specialty();
        existingSpecialty.setId(4L);
        existingSpecialty.setName("Old Specialty");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        existingSpecialty.setFaculty(faculty);

        // Обновяване на съществуващ Specialty
        specialtyMapper.updateSpecialtyFromDto(dto, existingSpecialty);

        // Assertions
        assertNotNull(existingSpecialty, "Specialty object should not be null");
        assertEquals("Physics", existingSpecialty.getName(), "Specialty name does not match");
        assertNotNull(existingSpecialty.getFaculty(), "Faculty should not be null");
        assertEquals(1L, existingSpecialty.getFaculty().getId(), "Faculty ID should not be updated");
        assertEquals(4L, existingSpecialty.getId(), "Specialty ID should not be updated");
    }
}
