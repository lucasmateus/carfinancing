package com.example.carfinancing.model;

import com.example.carfinancing.enums.FinancingType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SavedInstallment extends BaseInstallment {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String contact;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal monthlyInstallment;

    public SavedInstallment(FinancingType financingType, int installmentCount, double carValue,
                            String name, String contact, BigDecimal monthlyInstallment) {
        super(financingType, installmentCount, carValue);
        this.name = name;
        this.contact = contact;
        this.monthlyInstallment = monthlyInstallment;
    }
}