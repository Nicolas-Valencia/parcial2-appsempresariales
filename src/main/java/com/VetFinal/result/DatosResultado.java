package com.VetFinal.result;



import lombok.Getter;

@Getter
public class DatosResultado <T> extends Resultado {
    private T datos;

    public DatosResultado(boolean estado, String mensaje, String codigo, T datos) {
        super(estado, mensaje, codigo);
        this.datos = datos;
    }
}