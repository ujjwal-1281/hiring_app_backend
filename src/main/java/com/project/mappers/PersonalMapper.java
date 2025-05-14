package com.project.mappers;

import com.project.dto.PersonalDTO;
import com.project.enities.PersonalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    PersonalEntity toEntity(PersonalDTO personalDTO);
    PersonalDTO toDTO(PersonalEntity personalEntity);
}
