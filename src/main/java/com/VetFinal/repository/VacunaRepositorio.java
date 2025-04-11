package com.VetFinal.repository;

import com.VetFinal.entity.Vacuna;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacunaRepositorio extends JpaRepository<Vacuna, Long> {

    Optional<Vacuna> findByNombreAndCodigo(String nombre, String codigo);

    List<Vacuna> findByAnimalId(Long animalId);

    List<Vacuna> findByAnimalIdAndCodigoAndFechaFinProteccionAfter(
            Long animalId, String codigo, LocalDate fechaInicioProteccion);

    List<Vacuna> findByFechaFinProteccionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Optional<Vacuna> findByAnimalIdAndNombreAndCodigo(Long animalId, String nombre, String codigo);
}