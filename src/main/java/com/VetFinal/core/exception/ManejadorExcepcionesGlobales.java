package com.VetFinal.core.exception;

import com.VetFinal.core.result.Resultado;
import com.VetFinal.core.result.ResultadoDatos;
import com.VetFinal.core.result.AyudanteResultado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManejadorExcepcionesGlobales {

    @ExceptionHandler(ExcepcionCitaDoctor.class)
    public ResponseEntity<Resultado> excepcionCitaDoctor(ExcepcionCitaDoctor e) {
        return new ResponseEntity<>(AyudanteResultado.errorCitaDoctor(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionTipoHora.class)
    public ResponseEntity<Resultado> excepcionTipoHora(ExcepcionTipoHora e) {
        return new ResponseEntity<>(AyudanteResultado.errorTipoHora(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcepcionVacunacion.class)
    public ResponseEntity<Resultado> excepcionVacunacion(ExcepcionVacunacion e) {
        return new ResponseEntity<>(AyudanteResultado.errorVacunacion(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionDuplicado.class)
    public ResponseEntity<Resultado> excepcionDuplicado(ExcepcionDuplicado e) {
        return new ResponseEntity<>(AyudanteResultado.errorDuplicado(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionNoEncontrado.class) // Cambiado a tu propia excepción
    public ResponseEntity<Resultado> manejarNoEncontrado(ExcepcionNoEncontrado e) {
        return new ResponseEntity<>(AyudanteResultado.errorNoEncontrado(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultadoDatos<List<String>>> manejarErroresValidacion(MethodArgumentNotValidException e) {
        List<String> erroresValidacion = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(AyudanteResultado.errorValidacion(erroresValidacion), HttpStatus.BAD_REQUEST);
    }
}