package com.example.demo.repositories;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Faculty;

@DataJpaTest
public class FacultyRepoTest {

    @Autowired
    private FacultyRepo facultyRepo;

    @BeforeEach
    void setup() {
        Faculty faculty = new Faculty();
        faculty.setName("Engineering");
        Faculty savedFaculty = facultyRepo.save(faculty); // Запазваме обекта
        System.out.println("Saved Faculty ID: " + savedFaculty.getId()); // Отпечатваме ID-то
    }

    @Test
    void testExistsById() {
    Faculty faculty = new Faculty();
    faculty.setName("Test Faculty");
    faculty = facultyRepo.save(faculty); // Вземаме ID-то на записания обект

    boolean exists = facultyRepo.existsById(faculty.getId());
    assertThat(exists).isTrue(); // Проверяваме дали записът съществува
}

    @Test
    void testFindById() {
        Faculty faculty = new Faculty();
        faculty.setName("Mathematics");
        facultyRepo.save(faculty);

        Optional<Faculty> foundFaculty = facultyRepo.findById(faculty.getId());
        assertThat(foundFaculty).isPresent(); // Проверка, че е намерено
        assertThat(foundFaculty.get().getName()).isEqualTo("Mathematics");
    }

    @Test
    void testAddFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Science");
        Faculty savedFaculty = facultyRepo.save(faculty);

        assertThat(savedFaculty.getId()).isNotNull();
        assertThat(savedFaculty.getName()).isEqualTo("Science");
    }

    @Test
    void testUpdateFaculty() {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(1L);
        assertThat(optionalFaculty).isPresent();

        Faculty faculty = optionalFaculty.get();
        faculty.setName("Updated Name");
        facultyRepo.save(faculty);

        Optional<Faculty> updatedFaculty = facultyRepo.findById(1L);
        assertThat(updatedFaculty).isPresent();
        assertThat(updatedFaculty.get().getName()).isEqualTo("Updated Name");
    }

    @Test
    void testDeleteFaculty() {
        facultyRepo.deleteById(1L);

        boolean exists = facultyRepo.existsById(1L);
        assertThat(exists).isFalse();
    }
}
