package com.example.demo.mappers;

import com.example.demo.dto.SpecialtyDto;
import com.example.demo.models.Specialty;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialtyMapper {

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "faculty", expression = "java(new com.example.demo.models.Faculty(dto.facultyId()))")
    Specialty convertDtoToEntity(SpecialtyDto dto, Long id);

    @Mapping(target = "faculty", expression = "java(new com.example.demo.models.Faculty(dto.facultyId()))")
    Specialty convertDtoToEntity(SpecialtyDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Specialty updateSpecialtyFromDto(SpecialtyDto dto, @MappingTarget Specialty entity);
}