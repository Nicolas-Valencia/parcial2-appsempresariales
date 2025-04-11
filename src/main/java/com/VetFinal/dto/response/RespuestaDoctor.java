package com.VetFinal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDoctor {
    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String ciudad;
}