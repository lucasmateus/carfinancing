package com.example.carfinancing.model;

import com.example.carfinancing.enums.FinancingType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InstallmentResult extends BaseInstallment {
    private BigDecimal monthlyInstallment;

    public InstallmentResult(BigDecimal monthlyInstallment, FinancingType financingType,
                             int installmentCount, double carValue) {
        super(financingType, installmentCount, carValue);
        this.monthlyInstallment = monthlyInstallment;
    }
}