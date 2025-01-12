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

import com.example.demo.dto.FacultyDto;
import com.example.demo.models.Faculty;
import com.example.demo.services.FacultyService;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void testGetAllFaculties() throws Exception {
        Faculty faculty1 = new Faculty();
        faculty1.setId(1L);
        faculty1.setName("Engineering");

        Faculty faculty2 = new Faculty();
        faculty2.setId(2L);
        faculty2.setName("Science");

        List<Faculty> faculties = Arrays.asList(faculty1, faculty2);

        when(facultyService.getAll()).thenReturn(faculties);

        mockMvc.perform(get("/api/faculties")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Engineering"))
                .andExpect(jsonPath("$[1].name").value("Science"));
    }

    @Test
    void testAddFaculty() throws Exception {
        // Създаване на DTO с използване на конструктора на record
        FacultyDto facultyDto = new FacultyDto("New Faculty", null);
    
        Faculty savedFaculty = new Faculty();
        savedFaculty.setId(1L);
        savedFaculty.setName("New Faculty");
    
        when(facultyService.save(Mockito.any(FacultyDto.class))).thenReturn(savedFaculty);
    
        mockMvc.perform(post("/api/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Faculty\"}"))
                .andExpect(status().isCreated())  // EXPECT 201 CREATED
                .andExpect(jsonPath("$.name").value("New Faculty"));
    }

    @Test
    void testUpdateFaculty() throws Exception {
    // Създаване на DTO с използване на конструктора на record
    FacultyDto facultyDto = new FacultyDto("Updated Faculty", 1L);

    Faculty updatedFaculty = new Faculty();
    updatedFaculty.setId(1L);
    updatedFaculty.setName("Updated Faculty");

    when(facultyService.update(Mockito.eq(1L), Mockito.any(FacultyDto.class))).thenReturn(updatedFaculty);

    mockMvc.perform(put("/api/faculties/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"Updated Faculty\"}"))
            .andExpect(status().isAccepted())  // EXPECT 202 ACCEPTED
            .andExpect(jsonPath("$.name").value("Updated Faculty"));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        Faculty deletedFaculty = new Faculty();
        deletedFaculty.setId(1L);
        deletedFaculty.setName("Deleted Faculty");

        when(facultyService.delete(1L)).thenReturn(deletedFaculty);

        mockMvc.perform(delete("/api/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())  // EXPECT 202 ACCEPTED
                .andExpect(jsonPath("$.name").value("Deleted Faculty"));
    }
}
