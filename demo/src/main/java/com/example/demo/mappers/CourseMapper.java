package com.example.demo.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.demo.dto.CourseDto;
import com.example.demo.models.Course;
import com.example.demo.models.Specialty;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "specialty", source = "dto.specialtyIds", qualifiedByName = "mapSpecialtyIdsToSpecialties")
    Course convertDtoToEntity(CourseDto dto);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "specialty", source = "dto.specialtyIds", qualifiedByName = "mapSpecialtyIdsToSpecialties")
    Course convertDtoToEntity(CourseDto dto, Long id);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default Course updateCourseFromDto(CourseDto dto, @MappingTarget Course entity) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }
        if (dto.specialtyIds() != null) {
            entity.setSpecialty(mapSpecialtyIdsToSpecialties(dto.specialtyIds()));
        }
        return entity;
    }

    @Named("mapSpecialtyIdsToSpecialties")
    default Set<Specialty> mapSpecialtyIdsToSpecialties(Set<Long> specialtyIds) {
        if (specialtyIds == null) {
            return null;
        }
        return specialtyIds.stream()
                .map(id -> {
                    Specialty specialty = new Specialty();
                    specialty.setId(id);
                    return specialty;
                })
                .collect(Collectors.toSet());

            }
}