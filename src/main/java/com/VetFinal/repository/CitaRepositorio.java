package com.VetFinal.repository;

import com.VetFinal.entity.Cita;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {

    Optional<Cita> findByFechaCita(LocalDateTime fechaCita);

    boolean existsByDoctorIdAndFechaCita(Long doctorId, LocalDateTime fechaCita);

    List<Cita> findByFechaCitaBetweenAndAnimalId(
            LocalDateTime fechaInicio, LocalDateTime fechaFin, Long animalId);

    List<Cita> findByFechaCitaBetweenAndDoctorId(
            LocalDateTime fechaInicio, LocalDateTime fechaFin, Long doctorId);
}