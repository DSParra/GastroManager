package com.restaurant_system.GastroManager.table_areas;

import java.util.List;

public interface ITableAreaService {
    TableArea getByIdEntity(Long id);
    List<TableAreaResponseDto> getAll();
    TableAreaResponseDto getById(Long id);
    TableAreaResponseDto save(TableAreaRequestDto tableAreaRequestDto);
    TableAreaResponseDto update (Long id , TableAreaRequestDto tableAreaRequestDto);
    void deleteById(Long id);


}
