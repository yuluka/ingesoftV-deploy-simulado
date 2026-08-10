package com.ejemplo.ingesoft_calculator_microservice_java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationDto {
    private Integer id;
    private double operandA;
    private double operandB;
    private String operator;
    private double result;
}
