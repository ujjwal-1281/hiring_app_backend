package com.project.mappers;

import com.project.dto.CandidateDTO;
import com.project.enities.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

        // Used to convert DTO to Entity
    CandidateEntity toEntity(CandidateDTO dto);

    // Used to convert entity to DTO
    CandidateDTO toDTO(CandidateEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCandidateFromDto(CandidateDTO dto, @MappingTarget CandidateEntity entity);
}

