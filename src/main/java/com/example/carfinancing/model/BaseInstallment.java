package com.example.carfinancing.model;

import com.example.carfinancing.enums.FinancingType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseInstallment {
    @NotNull
    private FinancingType financingType;
    @Min(value = 12)
    private int installmentCount;
    @DecimalMin(value = "0.01")
    private double carValue;
}

