package com.VetFinal.repository;

import com.VetFinal.entity.FechaDisponible;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FechaDisponibleRepositorio extends JpaRepository<FechaDisponible, Long> {

    Optional<FechaDisponible> findByFechaDisponible(LocalDate fechaDisponible);

    List<FechaDisponible> findByDoctorId(Long doctorId);

    List<FechaDisponible> findByDoctorIdAndFechaDisponible(Long doctorId, LocalDate fechaDisponible);

    boolean existsByDoctorIdAndFechaDisponible(Long doctorId, LocalDate fechaDisponible);
}