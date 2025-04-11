package com.VetFinal.core.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Resultado {
    private boolean estado;
    private String mensaje;
    private String codigoHttp;
}