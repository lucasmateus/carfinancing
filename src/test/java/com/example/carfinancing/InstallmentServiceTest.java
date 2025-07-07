package com.example.carfinancing;

import com.example.carfinancing.enums.FinancingType;
import com.example.carfinancing.model.BaseInstallment;
import com.example.carfinancing.model.InstallmentResult;
import com.example.carfinancing.service.InstallmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InstallmentServiceTest {

    @InjectMocks
    private InstallmentService installmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            var fieldInternal = InstallmentService.class.getDeclaredField("factorInternal");
            fieldInternal.setAccessible(true);
            fieldInternal.set(installmentService, 1.04);
            var fieldExternal = InstallmentService.class.getDeclaredField("factorExternal");
            fieldExternal.setAccessible(true);
            fieldExternal.set(installmentService, 1.065);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testCalculateInstallment_Internal() {
        BaseInstallment base = new BaseInstallment(FinancingType.INTERNAL, 24, 50000.0);
        InstallmentResult result = installmentService.calculateInstallment(base);
        assertEquals(FinancingType.INTERNAL, result.getFinancingType());
        assertEquals(24, result.getInstallmentCount());
        assertEquals(50000.0, result.getCarValue());
        assertEquals(new BigDecimal("2166.67"), result.getMonthlyInstallment());
    }

    @Test
    void testCalculateInstallment_External() {
        BaseInstallment base = new BaseInstallment(FinancingType.EXTERNAL, 60, 60000.0);
        InstallmentResult result = installmentService.calculateInstallment(base);
        assertEquals(FinancingType.EXTERNAL, result.getFinancingType());
        assertEquals(60, result.getInstallmentCount());
        assertEquals(60000.0, result.getCarValue());
        assertEquals(new BigDecimal("1065.00"), result.getMonthlyInstallment());
    }

    @Test
    void testCalculateInstallment_InvalidInstallments() {
        BaseInstallment base = new BaseInstallment(FinancingType.INTERNAL, 10, 50000.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            installmentService.calculateInstallment(base);
        });
        assertTrue(ex.getMessage().contains("Número de parcelas inválido"));
    }

    @Test
    void testCalculateInstallment_InvalidType() {
        BaseInstallment base = new BaseInstallment(null, 24, 50000.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            installmentService.calculateInstallment(base);
        });
        assertTrue(ex.getMessage().contains("Tipo de financiamento inválido"));
    }
} 