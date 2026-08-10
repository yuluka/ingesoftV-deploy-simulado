package com.ejemplo.ingesoft_calculator_microservice_java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.ingesoft_calculator_microservice_java.model.Operation;
import com.ejemplo.ingesoft_calculator_microservice_java.repository.OperationRepository;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public double substractNumbers(double a, double b) {
        double result = a - b;
        
        Operation operation = new Operation();
        operation.setOperandA(a);
        operation.setOperandB(b);
        operation.setOperator("-");
        operation.setResult(result);

        operationRepository.save(operation);

        return result;
    }
}
