package com.VetFinal.controller;

import com.VetFinal. dto.request.SolicitudFechaDisponible;
import com.VetFinal. dto.response.RespuestaFechaDisponible;
import com.VetFinal. service.ServicioFechaDisponible;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fechas-disponibles")
@RequiredArgsConstructor
public class ControladorFechaDisponible {
    private final ServicioFechaDisponible servicioFechaDisponible;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaFechaDisponible> obtenerTodas() {
        return servicioFechaDisponible.obtenerTodas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaFechaDisponible obtenerPorId(@PathVariable("id") Long id) {
        return servicioFechaDisponible.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaFechaDisponible guardar(@RequestBody SolicitudFechaDisponible fechaDisponible) {
        return servicioFechaDisponible.crear(fechaDisponible);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaFechaDisponible actualizar(@PathVariable Long id, @RequestBody SolicitudFechaDisponible solicitud) {
        return servicioFechaDisponible.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioFechaDisponible.eliminarPorId(id);
    }
}