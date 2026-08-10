package com.ejemplo.ingesoft_calculator_microservice_java.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.ejemplo.ingesoft_calculator_microservice_java.dto.OperationDto;
import com.ejemplo.ingesoft_calculator_microservice_java.model.Operation;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    OperationDto toDto(Operation entity);
    
    Operation toEntity(OperationDto dto);

    List<OperationDto> toDtoList(List<Operation> entities);
}
