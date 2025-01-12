package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.example.demo.dto.CourseDto;
import com.example.demo.models.Course;
import com.example.demo.services.CourseService;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    void testGetCourses() throws Exception {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Mathematics");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Physics");

        when(courseService.getAll()).thenReturn(List.of(course1, course2));

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mathematics"))
                .andExpect(jsonPath("$[1].name").value("Physics"));
    }

    @Test
    void testGetCourseById() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Mathematics");

        when(courseService.get(1L)).thenReturn(course);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mathematics"));
    }

    @Test
    void testSaveCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Mathematics");

        when(courseService.save(any(CourseDto.class))).thenReturn(course);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Mathematics\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mathematics"));
    }

    @Test
    void testUpdateCourse() throws Exception {
        Course updatedCourse = new Course();
        updatedCourse.setId(1L);
        updatedCourse.setName("Updated Course");

        when(courseService.update(eq(1L), any(CourseDto.class))).thenReturn(updatedCourse);

        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Course\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Updated Course"));
    }

    @Test
    void testDeleteCourse() throws Exception {
        Course deletedCourse = new Course();
        deletedCourse.setId(1L);
        deletedCourse.setName("Deleted Course");

        when(courseService.delete(1L)).thenReturn(deletedCourse);

        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Deleted Course"));
    }
}
