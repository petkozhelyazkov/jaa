package com.example.demo.mappers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CourseDto;
import com.example.demo.models.Course;

class CourseMapperTest {

    private final CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void testConvertDtoToEntity() {
        Set<Long> specialtyIds = Set.of(1L, 2L); // Примерни данни за specialtyIds
        CourseDto dto = new CourseDto("Mathematics", specialtyIds);
    
        // Конвертиране от DTO към Entity
        Course course = courseMapper.convertDtoToEntity(dto, 1L); // Добавете Long id
    
        // Assertions
        assertNotNull(course);
        assertEquals(dto.name(), course.getName());
        assertNotNull(course.getSpecialty());
        assertEquals(dto.specialtyIds().size(), course.getSpecialty().size());
    }

    @Test
    void testConvertDtoToEntityWithId() {
    Set<Long> specialtyIds = Set.of(1L, 2L);
    CourseDto dto = new CourseDto("Physics", specialtyIds);

    // Конвертиране от DTO към Entity с ID
    Course course = courseMapper.convertDtoToEntity(dto, 1L);

    // Assertions
    assertNotNull(course, "Course object is null");
    assertEquals("Physics", course.getName(), "Course name does not match");
    assertEquals(1L, course.getId(), "Course ID does not match");
    assertNotNull(course.getSpecialty(), "Specialty should not be null");
    assertEquals(2, course.getSpecialty().size(), "Specialty size should match");
}

    @Test
    void testUpdateCourseFromDto() {
        Set<Long> specialtyIds = Set.of(1L, 2L);
        CourseDto dto = new CourseDto("Biology", specialtyIds);

        Course existingCourse = new Course();
        existingCourse.setName("Physics");
        existingCourse.setId(1L);

        courseMapper.updateCourseFromDto(dto, existingCourse);

        assertNotNull(existingCourse);
        assertEquals("Biology", existingCourse.getName(), "Course name should be updated");
        assertNotNull(existingCourse.getSpecialty(), "Specialty should not be null");
        assertEquals(specialtyIds.size(), existingCourse.getSpecialty().size(), "Specialty IDs size should match");
        specialtyIds.forEach(id -> assertTrue(
            existingCourse.getSpecialty().stream().anyMatch(s -> s.getId().equals(id)),
            "Specialty ID " + id + " should be present"
        ));
    }
}
