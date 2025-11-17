package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IEstudianteRepository;

@RestController
public class EstudianteController {
    private final String urlObtenerTodosLosEstudiantes = "/estudiantes";
    private final String urlObtenerEstudiantesPorGrado = "/estudiantes/grado/{id}";
    private final String urlObtenerEstudiantePorId = "/estudiantes/{id}";
    private final String urlCrearEstudiante = "/estudiantes/nuevo";
    private final String urlModificarEstudiante = "/estudiantes/modificar/{id}";
    private final String urlEliminarEstudiante = "/estudiantes/eliminar/{id}";

    @Autowired
    private IEstudianteRepository estudanteRepo;

    @GetMapping(urlObtenerTodosLosEstudiantes)
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantes() {
        try {
            List<Estudiante> estudiantes = estudanteRepo.findAll();
            return ResponseEntity.ok(estudiantes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(urlObtenerEstudiantesPorGrado)
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorGrado(@PathVariable Long id) {
        try {
            List<Estudiante> estudiantes = estudanteRepo.findByGradoId(id);
            return ResponseEntity.ok(estudiantes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(urlObtenerEstudiantePorId)
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable Long id) {
        try {
            return estudanteRepo.findById(id)
                    .map(estudiante -> ResponseEntity.ok(estudiante))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(urlCrearEstudiante)
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        try {
            if(estudiante.getGrado() != null) {
                // Asigno el grado al estudiante
                estudiante.getGrado().getEstudiantes().add(estudiante);
            }
            Estudiante nuevoEstudiante = estudanteRepo.save(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
