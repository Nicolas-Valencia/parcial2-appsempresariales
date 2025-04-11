package com.VetFinal.repository;


import com.VetFinal.entity.Animal ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNombreAndEspecieAndRaza(String nombre, String especie, String raza);

    Optional<Animal> findByClienteIdAndNombreAndEspecieAndRaza(Long clienteId, String nombre, String especie, String raza);

    List<Animal> findByNombre(String nombre);

    List<Animal> findByClienteId(Long clienteId);

    List<Animal> findByClienteNombre(String clienteNombre);


}