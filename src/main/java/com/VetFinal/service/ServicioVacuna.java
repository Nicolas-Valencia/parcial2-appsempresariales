package com.VetFinal.service;


import com.VetFinal. core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.VacunaMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudVacuna;
import com.VetFinal. dto.response.RespuestaVacuna;
import com.VetFinal. entity.Vacuna;
import com.VetFinal. repository.VacunaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioVacuna {
    private final VacunaRepositorio vacunaRepositorio;
    private final VacunaMapper vacunaMapper;

    public List<RespuestaVacuna> obtenerTodas() {
        return vacunaMapper.comoSalida(vacunaRepositorio.findAll());
    }

    public RespuestaVacuna obtenerPorId(Long id) {
        return vacunaMapper.comoSalida(vacunaRepositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Vacuna con ID " + id + " no encontrada")));
    }

    public RespuestaVacuna crear(SolicitudVacuna solicitud) {
        List<Vacuna> existeVacuna = vacunaRepositorio
                .findByAnimalIdAndCodigoAndFechaFinProteccionAfter(
                        solicitud.getAnimal().getId(),
                        solicitud.getCodigo(),
                        solicitud.getFechaInicioProteccion());

        if (!existeVacuna.isEmpty()) {
            throw new RuntimeException("¡La protección de la vacuna sigue activa!");
        }

        Vacuna vacunaGuardada = vacunaRepositorio.save(vacunaMapper.comoEntidad(solicitud));
        return vacunaMapper.comoSalida(vacunaGuardada);
    }

    public RespuestaVacuna actualizar(Long id, SolicitudVacuna solicitud) {
        Optional<Vacuna> vacunaDeBD = vacunaRepositorio.findById(id);
        Optional<Vacuna> existeVacuna = vacunaRepositorio.findByAnimalIdAndNombreAndCodigo(
                solicitud.getAnimal().getId(), solicitud.getNombre(), solicitud.getCodigo());

        if (vacunaDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Vacuna con ID " + id + " no encontrada!");
        }
        if (existeVacuna.isPresent() && !existeVacuna.get().getId().equals(id)) {
            throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Ya existe una vacuna con estos datos!");
        }

        Vacuna vacuna = vacunaDeBD.get();
        vacunaMapper.actualizar(vacuna, solicitud);
        return vacunaMapper.comoSalida(vacunaRepositorio.save(vacuna));
    }

    public void eliminarPorId(Long id) {
        Optional<Vacuna> vacunaDeBD = vacunaRepositorio.findById(id);
        if (vacunaDeBD.isPresent()) {
            vacunaRepositorio.delete(vacunaDeBD.get());
        } else {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Vacuna con ID " + id + " no encontrada!");
        }
    }

    public List<RespuestaVacuna> buscarPorAnimalId(Long animalId) {
        return vacunaMapper.comoSalida(vacunaRepositorio.findByAnimalId(animalId));
    }

    public List<RespuestaVacuna> buscarPorFechaProteccion(LocalDate fechaInicio, LocalDate fechaFin) {
        return vacunaMapper.comoSalida(vacunaRepositorio.findByFechaFinProteccionBetween(fechaInicio, fechaFin));
    }
}