package com.restaurant_system.GastroManager.table_areas;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableAreaMapper {

    TableAreaResponseDto toResponseDto(TableArea tableArea);
    TableArea toEntity(TableAreaRequestDto tableAreaResponseDto);
    List<TableAreaResponseDto> toResponseDtoList(List<TableArea> tableArea);
    void updateFromDtoMapper(TableAreaRequestDto tableAreaRequestDto, @MappingTarget TableArea tableArea);
}
