package com.restaurant_system.GastroManager.table_areas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableAreaResponseDto {
    private String name;
    private String description;
}
