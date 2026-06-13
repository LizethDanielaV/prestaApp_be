package com.example.prestamos.mapper;

import org.mapstruct.Mapper;

import com.example.prestamos.dto.referencia.ReferenciaCreateDTO;
import com.example.prestamos.model.Referencia;

@Mapper(componentModel = "spring")
public interface ReferenciaMapper {

    // Mapeo simple — campos con mismo nombre se mapean solos
    ReferenciaCreateDTO toResponseDto(Referencia referencia);

    // Mapeo simple — campos con mismo nombre se mapean solos
    Referencia toEntity(ReferenciaCreateDTO refDTO);
    
} 
