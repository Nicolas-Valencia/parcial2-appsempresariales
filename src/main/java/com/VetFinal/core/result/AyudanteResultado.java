package com.VetFinal.core.result;

import com.VetFinal.core.utilities.Mensaje;






public class AyudanteResultado {
    public static <T> ResultadoDatos<T> creado(T datos) {
        return new ResultadoDatos<>(true, Mensaje.CREADO, "201", datos);
    }

    public static <T> ResultadoDatos<T> exito(T datos) {
        return new ResultadoDatos<>(true, Mensaje.EXITO, "200", datos);
    }

    public static <T> ResultadoDatos<T> errorValidacion(T datos) {
        return new ResultadoDatos<>(false, Mensaje.ERROR_VALIDACION, "400", datos);
    }

    public static Resultado errorNoEncontrado(String mensaje) {
        return new Resultado(false, mensaje, "404");
    }

    public static Resultado errorDuplicado(String mensaje) {
        return new Resultado(false, mensaje, "500");
    }

    public static Resultado errorVacunacion(String mensaje) {
        return new Resultado(false, mensaje, "500");
    }

    public static Resultado errorTipoHora(String mensaje) {
        return new Resultado(false, mensaje, "400");
    }

    public static Resultado errorCitaDoctor(String mensaje) {
        return new Resultado(false, mensaje, "500");
    }
}