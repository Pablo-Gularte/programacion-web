package ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ManejadorGlobalDeExcepciones {
    /**
     * Manejador de la excepción personalizada RecursoDuplicadoException
     * Devuelvo un código de errro HTTP 409: Conflict.
     * Esta excepción se lanza cuando se intenta crear un recurso que ya existe en la base de datos.
     * @param e Contiene el mensaje que indica cuál es el recurso que se intenta crear de manera duplicada.
     * @param request El request web que generó la excepción
     * @return ResponseEntity con el código de estado HTTP y el mensaje de error
     */
    @ExceptionHandler(RecursoDuplicadoExcepcion.class)
    public ResponseEntity<Object> manejadorRecursoDuplicadoException(RecursoDuplicadoExcepcion e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Manejador de la excepción IllegalArgumentException
     * Devuelvo un código de error HTTP 400: Bad Request.
     * Esta excepción se lanza cuando un método recibe un argumento ilegal o inapropiado.
     * @param e La excepción lanzada que contiene el mensaje de error
     * @param request El request web que generó la excepción
     * @return ResponseEntity con el código de estado HTTP y el mensaje de error
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> manejadorIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
