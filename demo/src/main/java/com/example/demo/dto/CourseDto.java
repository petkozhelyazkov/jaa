package com.example.demo.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CourseDto(
        String name,
        Set<Long> specialtyIds) {
}