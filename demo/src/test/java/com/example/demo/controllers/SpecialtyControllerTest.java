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

import com.example.demo.dto.SpecialtyDto;
import com.example.demo.models.Specialty;
import com.example.demo.services.SpecialtyService;

@WebMvcTest(SpecialtyController.class)
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialtyService specialtyService;

    @Test
    void testGetAllSpecialties() throws Exception {
        Specialty specialty1 = new Specialty();
        specialty1.setId(1L);
        specialty1.setName("Computer Science");

        Specialty specialty2 = new Specialty();
        specialty2.setId(2L);
        specialty2.setName("Mathematics");

        List<Specialty> specialties = Arrays.asList(specialty1, specialty2);

        when(specialtyService.getAll()).thenReturn(specialties);

        mockMvc.perform(get("/specialties")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Computer Science"))
                .andExpect(jsonPath("$[1].name").value("Mathematics"));
    }

    @Test
    void testAddSpecialty() throws Exception {
        SpecialtyDto specialtyDto = new SpecialtyDto("Physics", null);

        Specialty savedSpecialty = new Specialty();
        savedSpecialty.setId(1L);
        savedSpecialty.setName("Physics");

        when(specialtyService.save(Mockito.any(SpecialtyDto.class))).thenReturn(savedSpecialty);

        mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Physics\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Physics"));
    }

    @Test
    void testUpdateSpecialty() throws Exception {
        SpecialtyDto specialtyDto = new SpecialtyDto("Updated Physics", null);

        Specialty updatedSpecialty = new Specialty();
        updatedSpecialty.setId(1L);
        updatedSpecialty.setName("Updated Physics");

        when(specialtyService.update(Mockito.eq(1L), Mockito.any(SpecialtyDto.class))).thenReturn(updatedSpecialty);

        mockMvc.perform(put("/specialties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Physics\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Updated Physics"));
    }

    @Test
    void testDeleteSpecialty() throws Exception {
        Specialty deletedSpecialty = new Specialty();
        deletedSpecialty.setId(1L);
        deletedSpecialty.setName("Deleted Specialty");

        when(specialtyService.delete(1L)).thenReturn(deletedSpecialty);

        mockMvc.perform(delete("/specialties/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Deleted Specialty"));
    }

    @Test
    void testUpdateFacultyForSpecialty() throws Exception {
        Specialty updatedSpecialty = new Specialty();
        updatedSpecialty.setId(1L);
        updatedSpecialty.setName("Specialty with Updated Faculty");

        when(specialtyService.updateFaculty(Mockito.eq(1L), Mockito.eq(2L))).thenReturn(updatedSpecialty);

        mockMvc.perform(put("/specialties/1/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Specialty with Updated Faculty"));
    }
}
