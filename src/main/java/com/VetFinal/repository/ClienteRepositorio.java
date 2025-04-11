package com.VetFinal.repository;



import com.VetFinal.entity.Cliente;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreo(String correo);

    List<Cliente> findByNombre(String nombre);
}