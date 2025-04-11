package com.VetFinal.dto.response;


import com.VetFinal.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaAnimal {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String genero;
    private String color;
    private LocalDate fechaNacimiento;
    private Cliente cliente;
}