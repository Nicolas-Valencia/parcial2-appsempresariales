package com.VetFinal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDate;

@Entity
@Table(name = "vacunas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vacuna extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_inicio_proteccion")
    private LocalDate fechaInicioProteccion;

    @Column(name = "fecha_fin_proteccion")
    private LocalDate fechaFinProteccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
}