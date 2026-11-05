package com.restaurant_system.GastroManager.table_areas;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/table_areas/")
@AllArgsConstructor
public class TableAreaController {

    private final TableAreaService tableAreaService;

    @GetMapping("")
    public ResponseEntity<List<TableAreaResponseDto>> getAll() {
       return  ResponseEntity.ok(tableAreaService.getAll());

    }
    @GetMapping("{id}")
    public ResponseEntity<TableAreaResponseDto> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(tableAreaService.getById(id));

    }

    @PostMapping()
    public ResponseEntity<TableAreaResponseDto> save(@RequestBody @Valid TableAreaRequestDto tableAreaRequestDto) {
        return  ResponseEntity.ok(tableAreaService.save(tableAreaRequestDto));

    }

    @PutMapping("{id}")
    public ResponseEntity<TableAreaResponseDto> update(@PathVariable Long id,@RequestBody @Valid TableAreaRequestDto tableAreaRequestDto) {
        return  ResponseEntity.ok(tableAreaService.update(id, tableAreaRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tableAreaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
