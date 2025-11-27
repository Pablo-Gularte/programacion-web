package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.EstudianteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EstudianteController {
    private final String urlObtenerTodosLosEstudiantes = "/estudiantes";
    private final String urlObtenerTodosLosEstudiantesPorGrado = "/estudiantes/grado/{idGrado}";
    private final String urlObtenerTodosLosEstudiantesSinGrado = "/estudiantes/sin-grado";
    private final String urlObtenerEstudiantePorId = "/estudiantes/{id}";
    private final String urlCrearEstudiante = "/estudiantes/nuevo";
    private final String urlEditarEstudiante = "/estudiantes/editar/{id}";
    private final String urlBorrarEstudiante = "/estudiantes/borrar/{id}";

    @Autowired
    private EstudianteService estudianteSvc;

    @GetMapping(urlObtenerTodosLosEstudiantes)
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteSvc.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping(urlObtenerTodosLosEstudiantesPorGrado)
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantesPorGrado(@PathVariable Long idGrado) {
        List<Estudiante> estudiantes = estudianteSvc.obtenerEstudiantesPorGrado(idGrado);
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping(urlObtenerTodosLosEstudiantesSinGrado)
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantesSinGrado() {
        List<Estudiante> estudiantes = estudianteSvc.obtenerEstudiantesSinGrado();
        return ResponseEntity.ok(estudiantes);
    }
    
    @GetMapping(urlObtenerEstudiantePorId)
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteSvc.obtenerEstudiantePorId(id);
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping(urlCrearEstudiante)
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante nuevoEstudiante) {
        return ResponseEntity.ok(estudianteSvc.crearEstudiante(nuevoEstudiante));
    }

    @PatchMapping(urlEditarEstudiante)
    public ResponseEntity<Estudiante> modificarEstudiante(@PathVariable Long id, Estudiante estudiante) {
        return ResponseEntity.ok(estudianteSvc.modificarEstudiante(id, estudiante));
    }

    @DeleteMapping(urlBorrarEstudiante)
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteSvc.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}
