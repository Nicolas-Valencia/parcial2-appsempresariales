package com.VetFinal.dto.request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCliente {
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String ciudad;
}