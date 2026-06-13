package com.example.prestamos.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.prestamos.dto.zona.ZonaCreateRequestDTO;
import com.example.prestamos.dto.zona.ZonaResponseDTO;
import com.example.prestamos.model.Zona;

@Mapper(componentModel = "spring")
public interface ZonaMapper {
    
    // Mapeo simple — campos con mismo nombre se mapean solos
    ZonaResponseDTO toResponseDTO(Zona zona);

    @Mapping(target = "id", ignore = true)
    Zona toEntity(ZonaCreateRequestDTO zonaDto);

    // Para actualizar — solo pisa campos no nulos (útil para PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ZonaCreateRequestDTO dto, @MappingTarget Zona zona);
}
