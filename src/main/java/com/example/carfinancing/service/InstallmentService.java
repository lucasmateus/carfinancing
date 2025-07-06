package com.example.carfinancing.service;

import com.example.carfinancing.enums.FinancingType;
import com.example.carfinancing.model.BaseInstallment;
import com.example.carfinancing.model.InstallmentResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class InstallmentService {

    @Value("${financing.factor.internal}")
    private double factorInternal;
    @Value("${financing.factor.external}")
    private double factorExternal;

    private int getInstallmentCount(BaseInstallment baseInstallment) {
        int minInstallments = 12;
        int maxInstallments = baseInstallment.getFinancingType() == FinancingType.INTERNAL ? 48 : 60;
        Set<Integer> allowedSteps = Set.of(12, 24, 36, 48, 60);
        int installmentCount = baseInstallment.getInstallmentCount();

        if (installmentCount < minInstallments ||
                installmentCount > maxInstallments ||
                !allowedSteps.contains(installmentCount)) {
            throw new IllegalArgumentException("Número de parcelas inválido. Valores permitidos: " + allowedSteps);
        }
        return installmentCount;
    }

    private double getFactor(FinancingType financingType) {
        return switch (financingType) {
            case INTERNAL -> factorInternal;
            case EXTERNAL -> factorExternal;
            default -> throw new IllegalArgumentException("Tipo de financiamento inválido.");
        };
    }

    public InstallmentResult calculateInstallment(BaseInstallment baseInstallment) {
        int installmentCount = getInstallmentCount(baseInstallment);

        double factor = getFactor(baseInstallment.getFinancingType());

        BigDecimal monthlyInstallment = BigDecimal.valueOf(baseInstallment.getCarValue() * factor / installmentCount)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        return new InstallmentResult(monthlyInstallment, baseInstallment.getFinancingType(), installmentCount,
                baseInstallment.getCarValue());
    }
} 