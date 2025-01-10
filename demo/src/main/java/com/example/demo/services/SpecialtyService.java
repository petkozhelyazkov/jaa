package com.example.demo.services;

import com.example.demo.dto.SpecialtyDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.mappers.SpecialtyMapper;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.models.Faculty;
import com.example.demo.models.Specialty;
import com.example.demo.models.Student;
import com.example.demo.repositories.FacultyRepo;
import com.example.demo.repositories.SpecialtyRepo;
import com.example.demo.repositories.StudentRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpecialtyService {

    private final SpecialtyRepo specialtyRepo;
    private final SpecialtyMapper specialtyMapper;
    private final FacultyRepo facultyRepo;

    public Specialty get(Long specialtyId) {
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);
        if (specialty.isPresent()) {
            return specialty.get();
        }

        return null;
    }

    public List<Specialty> getAll() {
        return specialtyRepo.findAll();
    }

    public Specialty save(SpecialtyDto dto) {
        Specialty specialty = specialtyMapper.convertDtoToEntity(dto);
        return specialtyRepo.saveAndFlush(specialty);
    }

    public Specialty update(Long specialtyId, SpecialtyDto dto) {
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);
        if (specialty.isPresent()) {
            Long id = specialty.get().getId();
            Specialty temp = specialtyMapper.convertDtoToEntity(dto, id);
            return specialtyRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Specialty partialUpdate(Long specialtyId, SpecialtyDto dto) {
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);
        if (specialty.isPresent()) {
            Specialty temp = specialty.get();
            specialtyMapper.updateSpecialtyFromDto(dto, temp);
            return specialtyRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Specialty delete(Long specialtyId) {
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);
        if (specialty.isPresent()) {
            Specialty temp = specialty.get();
            specialtyRepo.delete(temp);
            return temp;
        }

        return null;
    }

    public Specialty updateFaculty(Long specialtyId, Long facultyId) {
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);
        Optional<Faculty> faculty = facultyRepo.findById(facultyId);

        if (specialty.isPresent() && faculty.isPresent()) {
            Specialty temp = specialty.get();
            specialtyRepo.updateFaculty(faculty.get(), specialtyId);

            return temp;
        }

        return null;
    }
}