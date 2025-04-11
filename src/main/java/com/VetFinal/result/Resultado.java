package com.VetFinal.result;



import lombok.Getter;

@Getter
public class Resultado {
    private boolean estado;
    private String mensaje;
    private String codigo;

    public Resultado(boolean estado, String mensaje, String codigo) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.codigo = codigo;
    }
}
