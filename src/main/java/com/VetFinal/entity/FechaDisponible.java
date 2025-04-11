package com.VetFinal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fechas_disponibles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FechaDisponible extends EntidadBase  {

    @JoinColumn(name = "fecha_disponible")
    private LocalDate fechaDisponible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
}