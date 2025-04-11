package com.VetFinal.core.mapper;


import com.VetFinal.dto.request.SolicitudDoctor;
import com.VetFinal.dto.response.RespuestaDoctor;
import com.VetFinal.entity.Doctor;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor comoEntidad(SolicitudDoctor solicitudDoctor);
    RespuestaDoctor comoSalida(Doctor doctor);
    List<RespuestaDoctor> comoSalida(List<Doctor> listaDoctores);
    void actualizar(@MappingTarget Doctor entidad, SolicitudDoctor solicitud);
}