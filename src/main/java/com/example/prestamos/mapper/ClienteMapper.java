package com.example.prestamos.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.prestamos.dto.cliente.ClienteCreateDTO;
import com.example.prestamos.dto.cliente.ClienteEditarDTO;
import com.example.prestamos.dto.cliente.ClienteResponseDTO;
import com.example.prestamos.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target= "zona", ignore = true) //debe ser como se llama en el modelo, ya que ese es el destino 
    @Mapping(target= "referencia", ignore = true)
    @Mapping(target = "creditos", ignore = true)//se debe añadir en inorar en el mapeo poruqe aunque no sea un campo en mi base de datos, si es un campo en mi entidad en java, que es con la entidad con quien trabaja mapstruct. 
    Cliente toEntity(ClienteCreateDTO clienteDto);

    @Mapping(source = "zona.id", target = "zonaId") // le digo que el campo zonaid de dto lo va a llenar con lo que tare mi entidad cliente.zona.id
    ClienteResponseDTO toResponseDto(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target= "cedula", ignore = true)
    @Mapping(target= "zona", ignore = true)
    @Mapping(target= "referencia", ignore = true)
    @Mapping(target = "creditos", ignore = true)
    void updateEntityFromDto(ClienteEditarDTO dto, @MappingTarget Cliente cliente);
} 
    
   

