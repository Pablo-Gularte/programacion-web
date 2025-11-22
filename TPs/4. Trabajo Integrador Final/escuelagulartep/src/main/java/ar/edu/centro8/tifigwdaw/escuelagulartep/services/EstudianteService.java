package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones.DniDuplicadoException;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IEstudianteRepository;

@Service
public class EstudianteService {

    @Autowired
    IEstudianteRepository estudianteRepo;

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepo.findAll();
    }

    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró ningún estudiante con ID " + id));
    }

    public Estudiante crearEstudiante(Estudiante nuevoEstudiante) {
        // Verifico si el DNI ya existe en la base de datos
        if (estudianteRepo.existsByDni(nuevoEstudiante.getDni())) {
            throw new DniDuplicadoException("El DNI " + nuevoEstudiante.getDni() + " ya está registrado.");
        }
        return estudianteRepo.save(nuevoEstudiante);
    }
}
