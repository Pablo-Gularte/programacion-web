package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IEstudianteRepository;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;

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
    @Autowired
    private IGradoRepository gradoRepo;

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
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante) {
        try {
            if(estudiante.getGrado() != null) {
                // Asigno el grado al estudiante
                Grado grado = gradoRepo.findById(estudiante.getGrado().getId()).orElse(null);
                if( grado != null) {
                    estudiante.setGrado(grado);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el grado al que se quiere agregar el estudiante");
                }
            }
            Estudiante nuevoEstudiante = estudanteRepo.save(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(urlModificarEstudiante)
    public ResponseEntity<?> modificarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            return estudanteRepo.findById(id)
                    .map(estudianteExistente -> {
                        if(estudiante.getNombre() != null && !estudiante.getNombre().isBlank()) estudianteExistente.setNombre(estudiante.getNombre());
                        if(estudiante.getApellido() != null && !estudiante.getApellido().isBlank()) estudianteExistente.setApellido(estudiante.getApellido());
                        if(estudiante.getEdad() > 0) estudianteExistente.setEdad(estudiante.getEdad());
                        if(estudiante.getDireccion() != null && !estudiante.getDireccion().isBlank()) estudianteExistente.setDireccion(estudiante.getDireccion());
                        if(estudiante.getNombreMadre() != null && !estudiante.getNombreMadre().isBlank()) estudianteExistente.setNombreMadre(estudiante.getNombreMadre());
                        if(estudiante.getNombrePadre() != null && !estudiante.getNombrePadre().isBlank()) estudianteExistente.setNombrePadre(estudiante.getNombrePadre());
                        estudianteExistente.setHnoEnEscuela(estudiante.isHnoEnEscuela());
                        estudianteExistente.setEsRegular(estudiante.isEsRegular());
                        // Si se proporciona un grado, lo actualizo
                        if(estudiante.getGrado() != null) {
                            Grado grado = gradoRepo.findById(estudiante.getGrado().getId()).orElse(null);
                            if( grado != null) {
                                estudianteExistente.setGrado(grado);
                            } else {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el grado al que se quiere asignar el estudiante");
                            }
                        } else {
                            estudianteExistente.setGrado(null); // Si no se proporciona grado, lo dejo en null
                        }
                        Estudiante estudianteModificado = estudanteRepo.save(estudianteExistente);
                        return ResponseEntity.ok(estudianteModificado);
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(urlEliminarEstudiante)
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        try {
            if (estudanteRepo.existsById(id)) {
                estudanteRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
