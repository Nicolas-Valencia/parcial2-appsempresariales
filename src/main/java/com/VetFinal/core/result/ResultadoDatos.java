package com.VetFinal.core.result;


import lombok.Getter;

@Getter
public class ResultadoDatos <T> extends Resultado {
    private T datos;

    public ResultadoDatos(boolean estado, String mensaje, String codigoHttp, T datos) {
        super(estado, mensaje, codigoHttp);
        this.datos = datos;
    }
}