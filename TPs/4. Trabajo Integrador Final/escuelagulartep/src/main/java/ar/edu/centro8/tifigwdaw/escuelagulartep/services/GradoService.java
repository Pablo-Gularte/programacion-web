package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones.RecursoDuplicadoExcepcion;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.NombreGrado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.Turno;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;
import lombok.extern.slf4j.Slf4j;   // Importo el paquete que habilita el logging con lombok

@Service
@Slf4j  // Habilita el uso de logging con lombok
public class GradoService {

    @Autowired IGradoRepository gradoRepo;

    public List<Grado> obtenerTodosLosGrados() {
        log.info("METODO: obtenerTodosLosGrados() - INICIO");
        List<Grado> grados = gradoRepo.findAll();
        log.info("Cantidad de grados obtenidos: " + grados.size());
        return grados;
    }

    public Grado obtenerGradoPorId(Long id) {
        log.info("METODO: obtenerGradoPorId(" + id + ") - INICIO");
        Optional<Grado> grado = gradoRepo.findById(id);
        if(grado.isPresent()) {
            log.info("Grado encontrado");;
            return grado.get();
        }
        log.warn("No se encontró ningún grado con ID " + id);
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public Grado obtenerGradoPorTurnoYNombre(NombreGrado nombre, Turno turno) {
        log.info("METODO: obtenerGradoPorNombreYTurno(" + nombre + " " + turno.getLeyendaUI() + ") - INICIO");
        Grado grado = gradoRepo.findByTurnoAndNombre(turno, nombre);
        if(grado != null) {
            log.info("Grado encontrado");;
            return grado;
        }
        log.warn("No se encontró ningún grado con nombre " + nombre + " en el turno " + turno.getLeyendaUI());
        throw new IllegalArgumentException("No se encontró ningún grado con nombre " + nombre + " en el turno " + turno.getLeyendaUI());
    }

    public Grado crearGrado(Grado nuevoGrado) {
        log.info("METODO: crearGrado() - INICIO");
        // Aplico todas las validaciones de reglas de negocio antes de persistir el nuevo grado
        validarGrado(nuevoGrado);
        log.info("Validaciones de negocio superadas, procedo a crear el grado.");

        // Si el grado incluye listado de estudiantes, establezco la relación para cada estudiante del listado
        if (nuevoGrado.getEstudiantes() != null) {
            for(Estudiante estudiante : nuevoGrado.getEstudiantes()) {
                // Verifico que el estudiante no esté asignado a otro grado
                if (estudiante.getGrado() != null) {
                    log.error("El estudiante con ID " + estudiante.getId() + " ya está asignado a otro grado.");
                    throw new IllegalArgumentException("El estudiante con ID " + estudiante.getId() + " ya está asignado a otro grado.");
                }
                log.info("Asignando grado al estudiante con ID " + estudiante.getId());
                estudiante.setGrado(nuevoGrado);
            }
        }
        log.info("Persistiendo el nuevo grado en la base de datos.");
        return gradoRepo.save(nuevoGrado);
    }

    public Grado modificarGrado(Long id, Grado grado) {
        log.info("METODO: modificarGrado(" + id + ") - INICIO");
        // Se asume que el grado está activo si se envía algún atributo modificado
        if (grado.getNombre() != null || grado.getTurno() != null || grado.getDocente() != null) {
            grado.setActivo(true);
        } else {
            grado.setActivo(false);
        }
        // Aplico todas las validaciones de reglas de negocio antes de persistir el nuevo grado
        validarGrado(grado);
        log.info("Validaciones de negocio superadas, procedo a modificar el grado.");

        Optional<Grado> gradoEnBD = gradoRepo.findById(id);
        if(gradoEnBD.isPresent()) {
            log.info("Grado encontrado, procedo a modificar sus atributos.");
            // Verifico los atributos modificados y los actualizo en el objeto temporal
            Grado gradoTemp = gradoEnBD.get();
            if (grado.getNombre() != null) gradoTemp.setNombre(grado.getNombre());
            if (grado.getTurno() != null) gradoTemp.setTurno(grado.getTurno());
            if (grado.getDocente() != null) gradoTemp.setDocente(grado.getDocente());
            return gradoRepo.save(gradoTemp);
        }
        log.warn("No se encontró ningún grado con ID " + id);
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public void eliminarGrado(Long id) {
        log.info("METODO: eliminarGrado(" + id + ") - INICIO");
        if(gradoRepo.existsById(id)) {
            log.info("Grado encontrado, procedo a eliminarlo.");
            gradoRepo.deleteById(id);
        } else {
            log.warn("No se encontró ningún grado con ID " + id);
            throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
        }
    }

    /**
     * Aplica las reglas de negocio para validar un grado antes de ser creado o modificado. Si se intenta agregar
     * un recurso ya existente en la base de datos, se lanza una excepción RecursoDuplicadoExcepcion.
     * 
     * @param grado El grado a validar.
     */
    private void validarGrado(Grado grado) {
        log.info("Validando reglas de negocio para el grado: " + grado);
        // Verifico que no se intente ingresar un grado y turno ya existente
        if (gradoRepo.existsByNombreAndTurno(grado.getNombre(), grado.getTurno())) {
            log.error("Ya existe " + grado.getNombre().getLeyendaUI() + " en " + grado.getTurno().getLeyendaUI());
            throw new RecursoDuplicadoExcepcion("Ya existe " + grado.getNombre().getLeyendaUI() + " en " + grado.getTurno().getLeyendaUI());
        }

        // Verifico que el docente no esté activo en otro grado
        if (gradoRepo.existsByDocenteAndActivoTrue(grado.getDocente())) {
            log.error("El docente " + grado.getDocente() + " ya está asignado a otro grado activo.");
            throw new RecursoDuplicadoExcepcion("El docente " + grado.getDocente() + " ya está asignado a otro grado activo.");
        }
    }
}
