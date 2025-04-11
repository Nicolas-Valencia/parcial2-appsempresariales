package com.VetFinal.core.mapper;

import com.VetFinal.dto.request.SolicitudFechaDisponible;
import com.VetFinal.dto.response.RespuestaFechaDisponible;
import com.VetFinal.entity.FechaDisponible;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FechaDisponibleMapper {
    FechaDisponible comoEntidad(SolicitudFechaDisponible solicitudFechaDisponible);
    RespuestaFechaDisponible comoSalida(FechaDisponible fechaDisponible);
    List<RespuestaFechaDisponible> comoSalida(List<FechaDisponible> listaFechasDisponibles);
    void actualizar(@MappingTarget FechaDisponible entidad, SolicitudFechaDisponible solicitud);
}