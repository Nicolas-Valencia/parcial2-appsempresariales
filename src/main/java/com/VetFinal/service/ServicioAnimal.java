package com.VetFinal.service;


import com.VetFinal.core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.AnimalMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudAnimal;
import com.VetFinal. dto.response.RespuestaAnimal;
import com.VetFinal. entity.Animal;
import com.VetFinal. repository.AnimalRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioAnimal {
    private final AnimalRepositorio animalRepositorio;
    private final AnimalMapper animalMapper;

    public List<RespuestaAnimal> obtenerTodos() {
        return animalMapper.comoSalida(animalRepositorio.findAll());
    }

    public RespuestaAnimal obtenerPorId(Long id) {
        return animalMapper.comoSalida(animalRepositorio.findById(id).orElseThrow(() ->
                new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + "Animal con ID " + id)));
    }

    public RespuestaAnimal crear(SolicitudAnimal solicitud) {
        Optional<Animal> existeAnimal = animalRepositorio.findByNombreAndEspecieAndRaza(
                solicitud.getNombre(), solicitud.getEspecie(), solicitud.getRaza());

        if (existeAnimal.isEmpty()) {
            Animal animalGuardado = animalRepositorio.save(animalMapper.comoEntidad(solicitud));
            return animalMapper.comoSalida(animalGuardado);
        }
        throw new ExcepcionDuplicado("¡Este animal ya está registrado en el sistema!");
    }

    public RespuestaAnimal actualizar(Long id, SolicitudAnimal solicitud) {
        Optional<Animal> animalDeBD = animalRepositorio.findById(id);
        Optional<Animal> existeAnimal = animalRepositorio.findByClienteIdAndNombreAndEspecieAndRaza(
                solicitud.getCliente().getId(), solicitud.getNombre(), solicitud.getEspecie(), solicitud.getRaza());

        if (animalDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " El animal con ID " + id + " no se encontró!");
        }
        if (existeAnimal.isPresent() && !existeAnimal.get().getId().equals(id)) {
            throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Este animal ya está registrado!");
        }

        Animal animal = animalDeBD.get();
        animalMapper.actualizar(animal, solicitud);
        return animalMapper.comoSalida(animalRepositorio.save(animal));
    }

    public void eliminarPorId(Long id) {
        Optional<Animal> animalDeBD = animalRepositorio.findById(id);
        if (animalDeBD.isPresent()) {
            animalRepositorio.delete(animalDeBD.get());
        } else {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Animal con ID " + id + " no encontrado!");
        }
    }

    public List<RespuestaAnimal> buscarPorNombre(String nombre) {
        return animalMapper.comoSalida(animalRepositorio.findByNombre(nombre));
    }

    public List<RespuestaAnimal> buscarPorNombreCliente(String nombreCliente) {
        return animalMapper.comoSalida(animalRepositorio.findByClienteNombre(nombreCliente));
    }

    public List<RespuestaAnimal> buscarPorClienteId(Long clienteId) {
        return animalMapper.comoSalida(animalRepositorio.findByClienteId(clienteId));
    }
}