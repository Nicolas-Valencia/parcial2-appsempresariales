package com.VetFinal.controller;


import com.VetFinal. dto.request.SolicitudVacuna;
import com.VetFinal. dto.response.RespuestaVacuna;
import com.VetFinal. service.ServicioVacuna;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vacunas")
@RequiredArgsConstructor
public class ControladorVacuna {
    private final ServicioVacuna servicioVacuna;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaVacuna> obtenerTodas() {
        return servicioVacuna.obtenerTodas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaVacuna obtenerPorId(@PathVariable("id") Long id) {
        return servicioVacuna.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaVacuna guardar(@RequestBody SolicitudVacuna vacuna) {
        return servicioVacuna.crear(vacuna);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaVacuna actualizar(@PathVariable("id") Long id, @RequestBody SolicitudVacuna solicitud) {
        return servicioVacuna.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioVacuna.eliminarPorId(id);
    }

    @GetMapping("/porAnimal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaVacuna> buscarPorAnimalId(@PathVariable("animalId") Long animalId) {
        return servicioVacuna.buscarPorAnimalId(animalId);
    }

    @GetMapping("/porFechaProteccion")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaVacuna> buscarPorFechaProteccion(
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin) {
        return servicioVacuna.buscarPorFechaProteccion(fechaInicio, fechaFin);
    }
}