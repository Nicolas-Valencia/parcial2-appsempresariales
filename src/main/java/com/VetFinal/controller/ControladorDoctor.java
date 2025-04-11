package com.VetFinal.controller;


import com.VetFinal. dto.request.SolicitudDoctor;
import com.VetFinal. dto.response.RespuestaDoctor;
import com.VetFinal. service.ServicioDoctor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctores")
public class ControladorDoctor {
    private final ServicioDoctor servicioDoctor;

    public ControladorDoctor(ServicioDoctor servicioDoctor) {
        this.servicioDoctor = servicioDoctor;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaDoctor> obtenerTodos() {
        return servicioDoctor.obtenerTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaDoctor obtenerPorId(@PathVariable("id") Long id) {
        return servicioDoctor.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaDoctor guardar(@RequestBody SolicitudDoctor doctor) {
        return servicioDoctor.crear(doctor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaDoctor actualizar(@PathVariable Long id, @RequestBody SolicitudDoctor solicitud) {
        return servicioDoctor.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioDoctor.eliminarPorId(id);
    }
}