package com.VetFinal.service;


import com.VetFinal. core.exception.ExcepcionCitaDoctor;
import com.VetFinal. core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.CitaMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudCita;
import com.VetFinal. dto.response.RespuestaCita;
import com.VetFinal. entity.Cita;
import com.VetFinal. repository.CitaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioCita {
    private final CitaRepositorio citaRepositorio;
    private final CitaMapper citaMapper;
    private final ServicioFechaDisponible servicioFechaDisponible;

    public List<RespuestaCita> obtenerTodas() {
        return citaMapper.comoSalida(citaRepositorio.findAll());
    }

    public RespuestaCita obtenerPorId(Long id) {
        return citaMapper.comoSalida(citaRepositorio.findById(id).orElseThrow(() ->
                new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Cita con ID " + id + " no encontrada!")));
    }

    public RespuestaCita crear(SolicitudCita solicitud) {
        LocalDateTime fechaHora = solicitud.getFechaCita();
        Long doctorId = solicitud.getDoctor().getId();

        if (!servicioFechaDisponible.disponibleDoctor(doctorId, fechaHora)) {
            throw new ExcepcionCitaDoctor("¡El doctor no está disponible en esta fecha/hora!");
        }
        if (existeCitaEnFecha(doctorId, fechaHora)) {
            throw new RuntimeException("¡Ya existe una cita en este horario!");
        }

        Cita citaGuardada = citaRepositorio.save(citaMapper.comoEntidad(solicitud));
        return citaMapper.comoSalida(citaGuardada);
    }

    private boolean existeCitaEnFecha(Long doctorId, LocalDateTime fechaCita) {
        return citaRepositorio.existsByDoctorIdAndFechaCita(doctorId, fechaCita);
    }

    public RespuestaCita actualizar(Long id, SolicitudCita solicitud) {
        Optional<Cita> citaDeBD = citaRepositorio.findById(id);
        Optional<Cita> existeCita = citaRepositorio.findByFechaCita(solicitud.getFechaCita());

        if (citaDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Cita con ID " + id + " no encontrada!");
        }
        if (existeCita.isPresent()) {
            throw new ExcepcionDuplicado("¡Ya existe una cita para esta fecha!");
        }

        Cita cita = citaDeBD.get();
        citaMapper.actualizar(cita, solicitud);
        return citaMapper.comoSalida(citaRepositorio.save(cita));
    }

    public void eliminarPorId(Long id) {
        Optional<Cita> citaDeBD = citaRepositorio.findById(id);
        if (citaDeBD.isPresent()) {
            citaRepositorio.delete(citaDeBD.get());
        } else {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Cita con ID " + id + " no encontrada!");
        }
    }

    public List<RespuestaCita> buscarPorFechaYAnimal(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long animalId) {
        return citaMapper.comoSalida(citaRepositorio.findByFechaCitaBetweenAndAnimalId(fechaInicio, fechaFin, animalId));
    }

    public List<RespuestaCita> buscarPorFechaYDoctor(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long doctorId) {
        return citaMapper.comoSalida(citaRepositorio.findByFechaCitaBetweenAndDoctorId(fechaInicio, fechaFin, doctorId));
    }
}