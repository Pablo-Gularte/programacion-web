package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones.DocenteYaAsignadoException;
import ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones.GradoDuplicadoException;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;

@Service
public class GradoService {

    @Autowired IGradoRepository gradoRepo;

    public List<Grado> obtenerTodosLosGrados() {
        return gradoRepo.findAll();
    }

    public Grado obtenerGradoPorId(Long id) {
        Optional<Grado> grado = gradoRepo.findById(id);
        if(grado.isPresent()) {
            return grado.get();
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public Grado crearGrado(Grado nuevoGrado) {
        // Verifico que no se intente ingresar un grado y turno ya existente
        if (gradoRepo.existsByNombreAndTurno(nuevoGrado.getNombre(), nuevoGrado.getTurno())) {
            throw new GradoDuplicadoException("Ya existe " + nuevoGrado.getNombre().getLeyendaUI() + " en " + nuevoGrado.getTurno().getLeyendaUI());
        }

        // Verifico que el docente no esté activo en otro grado
        if (gradoRepo.existsByDocenteAndActivo(nuevoGrado.getDocente(), nuevoGrado.isActivo())) {
            throw new DocenteYaAsignadoException("El docente " + nuevoGrado.getDocente() + " ya está asignado a otro grado activo.");
        }

        // Si el grado incluye listado de estudiantes, establezco la relación para cada estudiante del listado
        if (nuevoGrado.getEstudiantes() != null) {
            for(Estudiante estudiante : nuevoGrado.getEstudiantes()) {
                // Verifico que el estudiante no esté asignado a otro grado
                if (estudiante.getGrado() != null) {
                    throw new IllegalArgumentException("El estudiante con ID " + estudiante.getId() + " ya está asignado a otro grado.");
                }
                estudiante.setGrado(nuevoGrado);
            }
        }
        return gradoRepo.save(nuevoGrado);
    }

    public Grado modificarGrado(Long id, Grado gradoDTO) {
        Optional<Grado> gradoEnBD = gradoRepo.findById(id);
        if(gradoEnBD.isPresent()) {
            return gradoRepo.save(gradoEnBD.get());
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public void eliminarGrado(Long id) {
        if(gradoRepo.existsById(id)) {
            gradoRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
        }
    }
}
