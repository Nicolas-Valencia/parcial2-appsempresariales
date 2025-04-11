package com.VetFinal.dto.request;


import com.VetFinal.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudVacuna {
    private String nombre;
    private String codigo;
    private LocalDate fechaInicioProteccion;
    private LocalDate fechaFinProteccion;
    private Animal animal;
}