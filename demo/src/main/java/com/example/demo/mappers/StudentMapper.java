package com.example.demo.mappers;

import com.example.demo.dto.StudentDto;
import com.example.demo.models.Student;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "facultyNumber", source = "dto.facultyNumber")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "specialty.id", source = "dto.specialtyId")

    Student convertDtoToEntity(StudentDto dto, Long id);

    Student convertDtoToEntity(StudentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student updateStudentFromDto(StudentDto dto, @MappingTarget Student entity);
}