package com.VetFinal.controller;


import com.VetFinal. dto.request.SolicitudCliente;
import com.VetFinal. dto.response.RespuestaCliente;
import com.VetFinal. service.ServicioCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ControladorCliente {
    private final ServicioCliente servicioCliente;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaCliente> obtenerTodos() {
        return servicioCliente.obtenerTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaCliente obtenerPorId(@PathVariable("id") Long id) {
        return servicioCliente.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaCliente guardar(@RequestBody SolicitudCliente cliente) {
        return servicioCliente.crear(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaCliente actualizar(@PathVariable Long id, @RequestBody SolicitudCliente solicitud) {
        return servicioCliente.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioCliente.eliminarPorId(id);
    }

    @GetMapping("/porNombre")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaCliente> buscarPorNombre(@RequestParam String nombre) {
        return servicioCliente.buscarPorNombre(nombre);
    }
}