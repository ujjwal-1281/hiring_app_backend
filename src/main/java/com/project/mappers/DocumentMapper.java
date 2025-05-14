package com.project.mappers;

import com.project.dto.DocumentDTO;
import com.project.enities.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target="candidate",ignore=true)
    @Mapping(target="id",ignore=true)
    @Mapping(target="fileUrl",ignore=true)
    @Mapping(target="verified",ignore=true)


    //DocumentDTO toDTO(DocumentEntity documentEntity);

    DocumentEntity toEntity(DocumentDTO documentDTO);

}
