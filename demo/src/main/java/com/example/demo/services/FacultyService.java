package com.example.demo.services;

import com.example.demo.dto.FacultyDto;
import com.example.demo.mappers.FacultyMapper;
import com.example.demo.models.Faculty;
import com.example.demo.repositories.FacultyRepo;

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
public class FacultyService {

    private final FacultyRepo facultyRepo;
    private final FacultyMapper facultyMapper;

    public Faculty get(Long facultyId) {
        Optional<Faculty> faculty = facultyRepo.findById(facultyId);
        if (faculty.isPresent()) {
            return faculty.get();
        }

        return null;
    }

    public List<Faculty> getAll() {
        return facultyRepo.findAll();
    }

    public Faculty save(FacultyDto dto) {
        Faculty faculty = facultyMapper.convertDtoToEntity(dto);
        return facultyRepo.saveAndFlush(faculty);
    }

    public Faculty update(Long facultyId, FacultyDto dto) {
        Optional<Faculty> faculty = facultyRepo.findById(facultyId);
        if (faculty.isPresent()) {
            Long id = faculty.get().getId();
            Faculty temp = facultyMapper.convertDtoToEntity(dto, id);
            return facultyRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Faculty partialUpdate(Long facultyId, FacultyDto dto) {
        Optional<Faculty> faculty = facultyRepo.findById(facultyId);
        if (faculty.isPresent()) {
            Faculty temp = faculty.get();
            facultyMapper.updateFacultyFromDto(dto, temp);
            return facultyRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Faculty delete(Long facultyId) {
        Optional<Faculty> faculty = facultyRepo.findById(facultyId);
        if (faculty.isPresent()) {
            Faculty temp = faculty.get();
            facultyRepo.delete(temp);
            return temp;
        }

        return null;
    }
}
