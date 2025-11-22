package ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ManejadorGlobalDeExcepciones {
    /**
     * Manejador de la excepción DniDuplicadoException
     * Devuelvo un código de errro HTTP 409: Conflict.
     * En este caso, el conflicto implica que se detectó el intento de registrar un DNI que ya existe.
     * @param e Es el mensaje indica que se detectó el intento de registrar un DNI que ya existe.
     * @param request El request web que generó la excepción
     * @return ResponseEntity con el código de estado HTTP y el mensaje de error
     */
    @ExceptionHandler(DniDuplicadoException.class)
    public ResponseEntity<Object> manejadorDniDuplicadoException(DniDuplicadoException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Manejador de la excepción GradoDuplicadoException
     * Devuelvo un código de errro HTTP 409: Conflict.
     * En este caso, el conflicto implica que se detectó el intento de registrar un Grado que ya existe.
     * @param e Es el mensaje que indica que se detectó el intento de registrar un Grado que ya existe.
     * @param request El request web que generó la excepción
     * @return ResponseEntity con el código de estado HTTP y el mensaje de error
     */
    @ExceptionHandler(GradoDuplicadoException.class)
    public ResponseEntity<Object> manejadorGradoDuplicado(GradoDuplicadoException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Manejador de la excepción DocenteYaAsignadoException
     * Devuelvo un código de errro HTTP 409: Conflict.
     * En este caso, el conflicto implica que se detectó el intento de asignar un docente que ya está asignado a otro grado activo.
     * 
     * @param e Es el mensaje que indica que se detectó el intento de asignar un docente que ya está asignado a otro grado activo.
     * @param request El request web que generó la excepción
     * @return ResponseEntity con el código de estado HTTP y el mensaje de error
     */
    @ExceptionHandler(DocenteYaAsignadoException.class)
    public ResponseEntity<Object> manejadorDocenteYaAsignado(DocenteYaAsignadoException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
