package com.VetFinal.controller;


import com.VetFinal. dto.request.SolicitudCita;
import com.VetFinal. dto.response.RespuestaCita;
import com.VetFinal. service.ServicioCita;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@RequiredArgsConstructor
public class ControladorCita {
    private final ServicioCita servicioCita;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaCita> obtenerTodas() {
        return servicioCita.obtenerTodas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaCita obtenerPorId(@PathVariable("id") Long id) {
        return servicioCita.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaCita guardar(@RequestBody SolicitudCita cita) {
        return servicioCita.crear(cita);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaCita actualizar(@PathVariable Long id, @RequestBody SolicitudCita solicitud) {
        return servicioCita.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioCita.eliminarPorId(id);
    }

    @GetMapping("/porAnimal")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaCita> buscarPorFechaYAnimal(
            @RequestParam("fechaInicio") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") LocalDateTime fechaFin,
            @RequestParam("animalId") Long animalId) {
        return servicioCita.buscarPorFechaYAnimal(fechaInicio, fechaFin, animalId);
    }

    @GetMapping("/porDoctor")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaCita> buscarPorFechaYDoctor(
            @RequestParam("fechaInicio") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") LocalDateTime fechaFin,
            @RequestParam("doctorId") Long doctorId) {
        return servicioCita.buscarPorFechaYDoctor(fechaInicio, fechaFin, doctorId);
    }
}