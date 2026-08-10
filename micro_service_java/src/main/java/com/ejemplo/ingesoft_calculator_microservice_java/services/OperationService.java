package com.ejemplo.ingesoft_calculator_microservice_java.services;

import org.springframework.stereotype.Service;

@Service
public class OperationService {
    public double substractNumbers(double a, double b) {
        return a - b;
    }
}
