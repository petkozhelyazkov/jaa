package com.example.demo.services;

import com.example.demo.dto.StudentDto;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.models.Specialty;
import com.example.demo.models.Student;
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
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final SpecialtyRepo specialtyRepo;

    public Student get(Integer facultyNumber) {
        Optional<Student> student = studentRepo.findByFacultyNumber(facultyNumber);
        if (student.isPresent()) {
            return student.get();
        }

        return null;
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public Student save(StudentDto dto) {
        Student student = studentMapper.convertDtoToEntity(dto);
        return studentRepo.saveAndFlush(student);
    }

    public Student update(Integer facultyNumber, StudentDto dto) {
        Optional<Student> student = studentRepo.findByFacultyNumber(facultyNumber);
        if (student.isPresent()) {
            Long id = student.get().getId();
            Student temp = studentMapper.convertDtoToEntity(dto, id);
            return studentRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Student partialUpdate(Integer facultyNumber, StudentDto dto) {
        Optional<Student> student = studentRepo.findByFacultyNumber(facultyNumber);
        if (student.isPresent()) {
            Student temp = student.get();
            studentMapper.updateStudentFromDto(dto, temp);
            return studentRepo.saveAndFlush(temp);
        }

        return null;
    }

    public Student delete(Integer facultyNumber) {
        Optional<Student> student = studentRepo.findByFacultyNumber(facultyNumber);
        if (student.isPresent()) {
            Student temp = student.get();
            studentRepo.delete(temp);
            return temp;
        }

        return null;
    }

    public Student updateSpecialty(Integer facultyNumber, Long specialtyId) {
        Optional<Student> student = studentRepo.findByFacultyNumber(facultyNumber);
        Optional<Specialty> specialty = specialtyRepo.findById(specialtyId);

        if (student.isPresent() && specialty.isPresent()) {
            Student temp = student.get();
            studentRepo.updateSpecialty(specialty.get(), facultyNumber);

            return temp;
        }

        return null;
    }
}
