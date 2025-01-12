package com.example.demo.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.FacultyDto;
import com.example.demo.models.Faculty;

class FacultyMapperTest {

    private final FacultyMapper facultyMapper = Mappers.getMapper(FacultyMapper.class);

    @Test
    void testConvertDtoToEntity() {

        FacultyDto dto = new FacultyDto("Engineering", null);

        
        Faculty faculty = facultyMapper.convertDtoToEntity(dto);

        // Assertions
        assertNotNull(faculty, "Faculty object should not be null");
        assertEquals("Engineering", faculty.getName(), "Faculty name does not match");
        assertNull(faculty.getId(), "Faculty ID should be null");
        assertNull(faculty.getSpecialties(), "Specialties should be null");
    }

    @Test
    void testConvertDtoToEntityWithId() {
    
    FacultyDto dto = new FacultyDto("Science", null); // ID тук е null
    Long id = 2L;

   
    Faculty faculty = facultyMapper.convertDtoToEntity(dto, id);

    // Assertions
    assertNotNull(faculty, "Faculty object should not be null");
    assertEquals("Science", faculty.getName(), "Faculty name does not match");
    assertEquals(id, faculty.getId(), "Faculty ID does not match"); // ID трябва да съвпада с очакваното
}

    @Test
    void testUpdateFacultyFromDto() {
       
        FacultyDto dto = new FacultyDto("Arts", null);

        
        Faculty existingFaculty = new Faculty();
        existingFaculty.setId(3L);
        existingFaculty.setName("Old Faculty");

        
        facultyMapper.updateFacultyFromDto(dto, existingFaculty);

        // Assertions
        assertNotNull(existingFaculty, "Faculty object should not be null");
        assertEquals("Arts", existingFaculty.getName(), "Faculty name should be updated");
        assertEquals(3L, existingFaculty.getId(), "Faculty ID should not be changed");
    }
}
