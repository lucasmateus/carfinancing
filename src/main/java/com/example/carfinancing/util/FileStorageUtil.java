package com.example.carfinancing.util;

import com.example.carfinancing.enums.FinancingType;
import com.example.carfinancing.model.SavedInstallment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.logging.Logger;

@UtilityClass
public class FileStorageUtil {
    private static final String FILE_PATH = "saved_installments.txt";

    public static void save(SavedInstallment installment) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            out.printf(Locale.US, "Name: %s | Contact: %s | Type: %s | Installments: %d | Value: %.2f | Monthly: %.2f\n",
                installment.getName(),
                installment.getContact(),
                installment.getFinancingType().name(),
                installment.getInstallmentCount(),
                installment.getCarValue(),
                installment.getMonthlyInstallment()
            );
        }
    }

    public static List<SavedInstallment> findAll() throws IOException {
        List<SavedInstallment> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Esperado: Name: %s | Contact: %s | Type: %s | Installments: %d | Value: %.2f | Monthly: %.2f\n
                String[] parts = line.split("\\|");
                if (parts.length != 6) continue;
                String name = parts[0].replace("Name:", "").trim();
                String contact = parts[1].replace("Contact:", "").trim();
                String typeStr = parts[2].replace("Type:", "").trim();
                String installmentsStr = parts[3].replace("Installments:", "").trim();
                String valueStr = parts[4].replace("Value:", "").trim();
                String monthlyStr = parts[5].replace("Monthly:", "").trim();
                try {
                    FinancingType financingType = FinancingType.valueOf(typeStr);
                    int installmentCount = Integer.parseInt(installmentsStr);
                    double carValue = Double.parseDouble(valueStr);
                    BigDecimal monthlyInstallment = BigDecimal.valueOf(Double.parseDouble(monthlyStr));
                    list.add(new SavedInstallment(financingType, installmentCount, carValue, name, contact, monthlyInstallment));
                } catch (Exception e) {
                    Logger.getLogger("FileStorageUtil.findAll").warning(e.getMessage());
                }
            }
        }
        return list;
    }
} 