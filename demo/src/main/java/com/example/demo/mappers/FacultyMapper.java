package com.example.demo.mappers;

import com.example.demo.dto.FacultyDto;
import com.example.demo.models.Faculty;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FacultyMapper {

    @Mapping(target = "name", source = "dto.name")
    Faculty convertDtoToEntity(FacultyDto dto, Long id);

    Faculty convertDtoToEntity(FacultyDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Faculty updateFacultyFromDto(FacultyDto dto, @MappingTarget Faculty entity);
}