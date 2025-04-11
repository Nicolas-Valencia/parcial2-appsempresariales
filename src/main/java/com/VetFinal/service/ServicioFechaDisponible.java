package com.VetFinal.service;


import com.VetFinal. core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.FechaDisponibleMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudFechaDisponible;
import com.VetFinal. dto.response.RespuestaFechaDisponible;
import com.VetFinal. entity.FechaDisponible;
import com.VetFinal. repository.FechaDisponibleRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioFechaDisponible {
    private final FechaDisponibleRepositorio fechaDisponibleRepositorio;
    private final FechaDisponibleMapper fechaDisponibleMapper;

    public List<RespuestaFechaDisponible> obtenerTodas() {
        return fechaDisponibleMapper.comoSalida(fechaDisponibleRepositorio.findAll());
    }

    public RespuestaFechaDisponible obtenerPorId(Long id) {
        return fechaDisponibleMapper.comoSalida(fechaDisponibleRepositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Fecha disponible con ID " + id + " no encontrada!")));
    }

    public RespuestaFechaDisponible crear(SolicitudFechaDisponible solicitud) {
        Optional<FechaDisponible> existeFecha = fechaDisponibleRepositorio.findByFechaDisponible(solicitud.getFechaDisponible());
        if (existeFecha.isPresent()) {
            throw new RuntimeException("¡La fecha no está disponible!");
        }

        FechaDisponible fechaGuardada = fechaDisponibleRepositorio.save(fechaDisponibleMapper.comoEntidad(solicitud));
        return fechaDisponibleMapper.comoSalida(fechaGuardada);
    }

    public RespuestaFechaDisponible actualizar(Long id, SolicitudFechaDisponible solicitud) {
        Optional<FechaDisponible> fechaDeBD = fechaDisponibleRepositorio.findById(id);
        Optional<FechaDisponible> existeFecha = fechaDisponibleRepositorio.findByFechaDisponible(solicitud.getFechaDisponible());

        if (fechaDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Fecha con ID " + id + " no encontrada!");
        }
        if (existeFecha.isPresent()) {
            throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Esta fecha ya está registrada!");
        }

        FechaDisponible fecha = fechaDeBD.get();
        fechaDisponibleMapper.actualizar(fecha, solicitud);
        return fechaDisponibleMapper.comoSalida(fechaDisponibleRepositorio.save(fecha));
    }

    public void eliminarPorId(Long id) {
        Optional<FechaDisponible> fechaDeBD = fechaDisponibleRepositorio.findById(id);
        if (fechaDeBD.isPresent()) {
            fechaDisponibleRepositorio.delete(fechaDeBD.get());
        } else {
            throw new RuntimeException("Fecha con ID " + id + " no encontrada!");
        }
    }

    public boolean disponibleDoctor(Long doctorId, LocalDateTime fechaHora) {
        return !fechaDisponibleRepositorio.existsByDoctorIdAndFechaDisponible(doctorId, fechaHora.toLocalDate());
    }

    public List<RespuestaFechaDisponible> buscarPorDoctorId(Long doctorId) {
        return fechaDisponibleMapper.comoSalida(fechaDisponibleRepositorio.findByDoctorId(doctorId));
    }
}