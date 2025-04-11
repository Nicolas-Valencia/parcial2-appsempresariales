package com.VetFinal.core.mapper;

import com.VetFinal.dto.request.*;
import com.VetFinal.dto.response.*;
import com.VetFinal.entity.*;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    Cita comoEntidad(SolicitudCita solicitudCita);
    RespuestaCita comoSalida(Cita cita);
    List<RespuestaCita> comoSalida(List<Cita> listaCitas);
    void actualizar(@MappingTarget Cita entidad, SolicitudCita solicitudCita);
}