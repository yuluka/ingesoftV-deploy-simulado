package com.ejemplo.ingesoft_calculator_microservice_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.ingesoft_calculator_microservice_java.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    
}
