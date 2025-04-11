package com.VetFinal.service;


import com.VetFinal. core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.ClienteMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudCliente;
import com.VetFinal. dto.response.RespuestaCliente;
import com.VetFinal. entity.Cliente;
import com.VetFinal. repository.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioCliente {
    private final ClienteRepositorio clienteRepositorio;
    private final ClienteMapper clienteMapper;

    public List<RespuestaCliente> obtenerTodos() {
        return clienteMapper.comoSalida(clienteRepositorio.findAll());
    }

    public RespuestaCliente obtenerPorId(Long id) {
        return clienteMapper.comoSalida(clienteRepositorio.findById(id).orElseThrow(() ->
                new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Dueño con ID " + id + " no encontrado!")));
    }

    public RespuestaCliente crear(SolicitudCliente solicitud) {
        Optional<Cliente> existeCliente = clienteRepositorio.findByCorreo(solicitud.getCorreo());
        if (existeCliente.isEmpty()) {
            Cliente clienteGuardado = clienteRepositorio.save(clienteMapper.comoEntidad(solicitud));
            return clienteMapper.comoSalida(clienteGuardado);
        }
        throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Este dueño ya está registrado!");
    }

    public RespuestaCliente actualizar(Long id, SolicitudCliente solicitud) {
        Optional<Cliente> clienteDeBD = clienteRepositorio.findById(id);
        Optional<Cliente> existeCliente = clienteRepositorio.findByCorreo(solicitud.getCorreo());

        if (clienteDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Dueño con ID " + id + " no encontrado!");
        }
        if (existeCliente.isPresent()) {
            throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Este correo ya está registrado!");
        }

        Cliente cliente = clienteDeBD.get();
        clienteMapper.actualizar(cliente, solicitud);
        return clienteMapper.comoSalida(clienteRepositorio.save(cliente));
    }

    public void eliminarPorId(Long id) {
        Optional<Cliente> clienteDeBD = clienteRepositorio.findById(id);
        if (clienteDeBD.isPresent()) {
            clienteRepositorio.delete(clienteDeBD.get());
        } else {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Dueño con ID " + id + " no encontrado!");
        }
    }

    public List<RespuestaCliente> buscarPorNombre(String nombre) {
        return clienteMapper.comoSalida(clienteRepositorio.findByNombre(nombre));
    }
}