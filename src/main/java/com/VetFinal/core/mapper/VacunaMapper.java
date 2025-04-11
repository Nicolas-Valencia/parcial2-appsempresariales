package com.VetFinal.core.mapper;

import com.VetFinal.dto.request.SolicitudVacuna;
import com.VetFinal.dto.response.RespuestaVacuna;
import com.VetFinal.entity.Vacuna;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacunaMapper {
    Vacuna comoEntidad(SolicitudVacuna solicitudVacuna);
    RespuestaVacuna comoSalida(Vacuna vacuna);
    List<RespuestaVacuna> comoSalida(List<Vacuna> listaVacunas);
    void actualizar(@MappingTarget Vacuna entidad, SolicitudVacuna solicitud);
}