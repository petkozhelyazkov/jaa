package com.example.demo.mappers;

import com.example.demo.dto.CourseDto;
import com.example.demo.models.Course;
import com.example.demo.models.Faculty;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    @Mapping(target = "name", source = "dto.name")

    Course convertDtoToEntity(CourseDto dto, Long id);

    Course convertDtoToEntity(CourseDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course updateCourseFromDto(CourseDto dto, @MappingTarget Course entity);
}