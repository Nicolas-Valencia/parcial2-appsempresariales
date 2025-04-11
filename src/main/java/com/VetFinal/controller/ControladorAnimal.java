package com.VetFinal.controller;

import com.VetFinal.dto.request.SolicitudAnimal;
import com.VetFinal.dto.response.RespuestaAnimal;
import com.VetFinal.service.ServicioAnimal;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animales")  // Cambiado a plural en español
@RequiredArgsConstructor
public class ControladorAnimal {

    private final ServicioAnimal servicioAnimal;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaAnimal> obtenerTodos() {
        return servicioAnimal.obtenerTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaAnimal obtenerPorId(@PathVariable("id") Long id) {
        return servicioAnimal.obtenerPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaAnimal guardar(@RequestBody SolicitudAnimal animal) {
        return servicioAnimal.crear(animal);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaAnimal actualizar(@PathVariable Long id, @RequestBody SolicitudAnimal solicitud) {
        return servicioAnimal.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        servicioAnimal.eliminarPorId(id);
    }

    @GetMapping("/porNombre")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaAnimal> buscarPorNombre(@RequestParam String nombre) {
        return servicioAnimal.buscarPorNombre(nombre);
    }

    @GetMapping("/porNombreCliente")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaAnimal> buscarPorNombreCliente(@RequestParam String nombreCliente) {
        return servicioAnimal.buscarPorNombreCliente(nombreCliente);
    }
}