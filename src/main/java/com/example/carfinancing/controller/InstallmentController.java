package com.example.carfinancing.controller;

import com.example.carfinancing.model.BaseInstallment;
import com.example.carfinancing.model.InstallmentResult;
import com.example.carfinancing.model.SavedInstallment;
import com.example.carfinancing.service.InstallmentService;
import com.example.carfinancing.util.FileStorageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/installment")
public class InstallmentController {

    @Autowired
    private InstallmentService installmentService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody @Valid BaseInstallment baseInstallment) {
        InstallmentResult result = installmentService.calculateInstallment(baseInstallment);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid SavedInstallment savedInstallment) throws IOException {
        FileStorageUtil.save(savedInstallment);
        return ResponseEntity.ok(Map.of("message", "Information saved successfully."));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws IOException {
        return ResponseEntity.ok(FileStorageUtil.findAll());
    }
} 