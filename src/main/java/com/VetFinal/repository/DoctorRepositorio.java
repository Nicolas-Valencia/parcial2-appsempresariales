package com.VetFinal.repository;


import com.VetFinal.entity.Doctor;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepositorio extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByCorreo(String correo);
}