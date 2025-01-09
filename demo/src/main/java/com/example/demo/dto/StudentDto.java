package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StudentDto(
        String firstName,
        String lastName,
        String email,
        Integer facultyNumber,
        Long specialtyId) {
}
