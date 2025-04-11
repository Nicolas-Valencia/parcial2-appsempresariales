package com.VetFinal.core.mapper;



import com.VetFinal.dto.request.SolicitudCliente;
import com.VetFinal.dto.response.RespuestaCliente;
import com.VetFinal.entity.Cliente;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente comoEntidad(SolicitudCliente solicitudCliente);
    RespuestaCliente comoSalida(Cliente cliente);
    List<RespuestaCliente> comoSalida(List<Cliente> listaClientes);
    void actualizar(@MappingTarget Cliente entidad, SolicitudCliente solicitud);
}

