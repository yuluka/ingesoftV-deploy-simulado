package com.ejemplo.ingesoft_calculator_microservice_java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Operation")
@Getter
@Setter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq")
    @SequenceGenerator(name = "operation_seq", sequenceName = "operation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name ="operand_a", nullable = false)
    private double operandA;
    
    @Column(name ="operand_b", nullable = false)
    private double operandB;

    @Column(name ="operator", nullable = false)
    private String operator;

    @Column(name ="result", nullable = false)
    private double result;
}
