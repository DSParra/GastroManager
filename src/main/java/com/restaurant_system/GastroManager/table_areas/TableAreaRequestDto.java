package com.restaurant_system.GastroManager.table_areas;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableAreaRequestDto {

    @NotBlank(message = "name required")
    private String name;
    private String description;
}
