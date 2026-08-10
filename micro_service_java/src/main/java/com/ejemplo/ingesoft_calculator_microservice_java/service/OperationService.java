package com.ejemplo.ingesoft_calculator_microservice_java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.ingesoft_calculator_microservice_java.dto.OperationDto;
import com.ejemplo.ingesoft_calculator_microservice_java.mappers.OperationMapper;
import com.ejemplo.ingesoft_calculator_microservice_java.model.Operation;
import com.ejemplo.ingesoft_calculator_microservice_java.repository.OperationRepository;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationMapper operationMapper;

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

    public List<OperationDto> getOperationHistory() {
        List<Operation> operations = operationRepository.findTop5ByOrderByIdDesc();
        
        return operationMapper.toDtoList(operations);
    }
}
