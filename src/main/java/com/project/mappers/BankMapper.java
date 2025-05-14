package com.project.mappers;


import com.project.dto.BankDTO;
import com.project.enities.BankEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMapper {
    @Mapping(target="id", ignore = true)
    @Mapping(target="candidate", ignore = true)
    BankEntity toEntity(BankDTO dto);
    BankDTO toDTO(BankEntity entity);
}
