package com.restaurant_system.GastroManager.table_areas;

import com.restaurant_system.GastroManager.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TableAreaService implements ITableAreaService  {

    private final TableAreaRepository tableAreaRepository;
    private final TableAreaMapper tableAreaMapper;

    @Override
    public TableArea getByIdEntity(Long id) {
        return tableAreaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("table area not found"));
    }

    @Override
    public List<TableAreaResponseDto> getAll() {
        return tableAreaMapper.toResponseDtoList(tableAreaRepository.findAll());
    }

    @Override
    public TableAreaResponseDto getById(Long id) {
        return tableAreaMapper.toResponseDto(this.getByIdEntity(id));
    }

    @Override
    public TableAreaResponseDto save(TableAreaRequestDto tableAreaRequestDto) {
        System.out.println("dto");
        System.out.println(tableAreaRequestDto);
        System.out.println("entity");

        TableArea tableArea = tableAreaMapper.toEntity(tableAreaRequestDto);
        System.out.println(tableArea);
        return tableAreaMapper.toResponseDto(tableAreaRepository.save(tableArea));
    }

    @Override
    public TableAreaResponseDto update(Long id, TableAreaRequestDto tableAreaRequestDto) {
        TableArea tableArea = this.getByIdEntity(id);
        tableAreaMapper.updateFromDtoMapper(tableAreaRequestDto , tableArea);
        return tableAreaMapper.toResponseDto(tableAreaRepository.save(tableArea));
    }

    @Override
    public void deleteById(Long id) {
        TableArea tableArea = this.getByIdEntity(id);
        tableAreaRepository.delete(tableArea);

    }
}
