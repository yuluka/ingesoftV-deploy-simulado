package com.ejemplo.ingesoft_calculator_microservice_java.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.ingesoft_calculator_microservice_java.service.OperationService;

@RestController
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping("/substract/{a}/{b}")
    ResponseEntity<?> getSubstractNumbers(
        @PathVariable("a") double a,
        @PathVariable("b") double b
    ) {
        try {
            return ResponseEntity.ok(Map.of(
                "result", operationService.substractNumbers(a, b)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/divide/{a}/{b}")
    ResponseEntity<?> getDivideNumbers(
        @PathVariable("a") double a,
        @PathVariable("b") double b
    ) {
        try {
            if ((int) b != 0) {
                return ResponseEntity.ok(Map.of(
                    "result", operationService.divideNumbers(a, b)
                ));
            } else {
                return ResponseEntity.status(400).body("El divisor no puede ser 0");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/history")
    ResponseEntity<?> getOperationHistory() {
        try {
            return ResponseEntity.ok(operationService.getOperationHistory());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
