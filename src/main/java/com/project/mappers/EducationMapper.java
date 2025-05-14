package com.project.mappers;

import com.project.dto.EducationDTO;
import com.project.enities.EducationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    @Mapping( target="id",ignore=true)
    @Mapping( target ="candidate", ignore=true)
    EducationEntity toEntity(EducationDTO dto);
    EducationDTO toDto(EducationEntity entity);
}
