package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dto.StudentDto;
import com.example.demo.models.Specialty;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetAllStudents() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setFacultyNumber(12345);
        student1.setEmail("john.doe@example.com");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setFacultyNumber(67890);
        student2.setEmail("jane.smith@example.com");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.getAll()).thenReturn(students);

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    void testAddStudent() throws Exception {
        StudentDto studentDto = new StudentDto("John", "Doe", "john.doe@example.com", 12345, 1L);

        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setFirstName("John");
        savedStudent.setLastName("Doe");
        savedStudent.setFacultyNumber(12345);
        savedStudent.setEmail("john.doe@example.com");

        when(studentService.save(Mockito.any(StudentDto.class))).thenReturn(savedStudent);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"facultyNumber\":12345,\"specialtyId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.facultyNumber").value(12345));
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentDto studentDto = new StudentDto("Jane", "Doe", "jane.doe@example.com", 12345, 1L);

        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setFirstName("Jane");
        updatedStudent.setLastName("Doe");
        updatedStudent.setFacultyNumber(12345);
        updatedStudent.setEmail("jane.doe@example.com");

        when(studentService.update(Mockito.eq(12345), Mockito.any(StudentDto.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/students/12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.com\",\"facultyNumber\":12345,\"specialtyId\":1}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Student deletedStudent = new Student();
        deletedStudent.setId(1L);
        deletedStudent.setFirstName("John");
        deletedStudent.setLastName("Doe");
        deletedStudent.setFacultyNumber(12345);
        deletedStudent.setEmail("john.doe@example.com");

        when(studentService.delete(12345)).thenReturn(deletedStudent);

        mockMvc.perform(delete("/students/12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testUpdateStudentSpecialty() throws Exception {
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setFirstName("John");
        updatedStudent.setLastName("Doe");
        updatedStudent.setFacultyNumber(12345);
        updatedStudent.setEmail("john.doe@example.com");
    
        Specialty newSpecialty = new Specialty();
        newSpecialty.setId(2L); // Увери се, че id е зададено
        updatedStudent.setSpecialty(newSpecialty);
    
        when(studentService.updateSpecialty(Mockito.eq(12345), Mockito.eq(2L))).thenReturn(updatedStudent);
    
        mockMvc.perform(put("/students/12345/specialty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.specialty.id").value(2)) // Проверка за specialty.id
                .andExpect(jsonPath("$.firstName").value("John"));
    }
}
