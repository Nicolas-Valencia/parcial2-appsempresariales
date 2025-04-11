package com.VetFinal.service;


import com.VetFinal. core.exception.ExcepcionDuplicado;
import com.VetFinal. core.exception.ExcepcionNoEncontrado;
import com.VetFinal. core.mapper.DoctorMapper;
import com.VetFinal. core.utilities.Mensaje;
import com.VetFinal. dto.request.SolicitudDoctor;
import com.VetFinal. dto.response.RespuestaDoctor;
import com.VetFinal. entity.Doctor;
import com.VetFinal. repository.DoctorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioDoctor {
    private final DoctorRepositorio doctorRepositorio;
    private final DoctorMapper doctorMapper;

    public List<RespuestaDoctor> obtenerTodos() {
        return doctorMapper.comoSalida(doctorRepositorio.findAll());
    }

    public RespuestaDoctor obtenerPorId(Long id) {
        return doctorMapper.comoSalida(doctorRepositorio.findById(id).orElseThrow(() ->
                new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Doctor con ID " + id + " no encontrado!")));
    }

    public RespuestaDoctor crear(SolicitudDoctor solicitud) {
        Optional<Doctor> existeDoctor = doctorRepositorio.findByCorreo(solicitud.getCorreo());
        if (existeDoctor.isEmpty()) {
            Doctor doctorGuardado = doctorRepositorio.save(doctorMapper.comoEntidad(solicitud));
            return doctorMapper.comoSalida(doctorGuardado);
        }
        throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Este doctor ya está registrado!");
    }

    public RespuestaDoctor actualizar(Long id, SolicitudDoctor solicitud) {
        Optional<Doctor> doctorDeBD = doctorRepositorio.findById(id);
        Optional<Doctor> existeDoctor = doctorRepositorio.findByCorreo(solicitud.getCorreo());

        if (doctorDeBD.isEmpty()) {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Doctor con ID " + id + " no encontrado!");
        }
        if (existeDoctor.isPresent()) {
            throw new ExcepcionDuplicado(Mensaje.YA_EXISTE + " ¡Este correo ya está registrado!");
        }

        Doctor doctor = doctorDeBD.get();
        doctorMapper.actualizar(doctor, solicitud);
        return doctorMapper.comoSalida(doctorRepositorio.save(doctor));
    }

    public void eliminarPorId(Long id) {
        Optional<Doctor> doctorDeBD = doctorRepositorio.findById(id);
        if (doctorDeBD.isPresent()) {
            doctorRepositorio.delete(doctorDeBD.get());
        } else {
            throw new ExcepcionNoEncontrado(Mensaje.NO_ENCONTRADO + " Doctor con ID " + id + " no encontrado!");
        }
    }
}