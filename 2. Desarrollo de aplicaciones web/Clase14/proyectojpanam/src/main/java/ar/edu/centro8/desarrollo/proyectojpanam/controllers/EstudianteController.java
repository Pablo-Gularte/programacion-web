package ar.edu.centro8.desarrollo.proyectojpanam.controllers;

import ar.edu.centro8.desarrollo.proyectojpanam.models.Estudiante;
import ar.edu.centro8.desarrollo.proyectojpanam.models.Curso;
import ar.edu.centro8.desarrollo.proyectojpanam.repositories.EstudianteRepository;
import ar.edu.centro8.desarrollo.proyectojpanam.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Estudiante>> traerEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> traerEstudiantePorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody Estudiante estudiante) {
        // Crear el estudiante básico sin cursos
        Estudiante estudianteGuardado = new Estudiante(estudiante.getNombre());
        estudianteGuardado = estudianteRepository.save(estudianteGuardado);
        
        // Si el estudiante viene con cursos, procesarlos después de guardar el estudiante
        if (estudiante.getCursos() != null && !estudiante.getCursos().isEmpty()) {
            for (Curso cursoInfo : estudiante.getCursos()) {
                Curso curso = null;
                
                if (cursoInfo.getId() != null) {
                    // Buscar curso existente por ID
                    Optional<Curso> cursoExistente = cursoRepository.findById(cursoInfo.getId());
                    if (cursoExistente.isPresent()) {
                        curso = cursoExistente.get();
                    } else {
                        // Si el ID no existe, crear un nuevo curso con el nombre proporcionado
                        if (cursoInfo.getNombre() != null && !cursoInfo.getNombre().trim().isEmpty()) {
                            curso = new Curso(cursoInfo.getNombre());
                            curso = cursoRepository.save(curso);
                        }
                    }
                } else if (cursoInfo.getNombre() != null && !cursoInfo.getNombre().trim().isEmpty()) {
                    // Crear nuevo curso si no tiene ID pero tiene nombre
                    curso = new Curso(cursoInfo.getNombre());
                    curso = cursoRepository.save(curso);
                }
                
                // Establecer la relación bidireccional usando el método helper
                if (curso != null) {
                    estudianteGuardado.agregarCurso(curso);
                }
            }
            
            // Guardar el estudiante con las relaciones establecidas
            estudianteGuardado = estudianteRepository.save(estudianteGuardado);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteGuardado);
    }

    @PutMapping("/editar/estudiante/{id_original}")
    public ResponseEntity<Estudiante> editarEstudiante(@PathVariable Long id_original,
            @RequestParam(required = false, name = "id") Long nuevaId,
            @RequestParam(required = false, name = "nombre") String nuevoNombre) {
        
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id_original);
        if (optionalEstudiante.isPresent()) {
            Estudiante existingEstudiante = optionalEstudiante.get();
            existingEstudiante.setNombre(nuevoNombre);
            Estudiante estudianteActualizado = estudianteRepository.save(existingEstudiante);
            return ResponseEntity.ok(estudianteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            
            // Primero, remover las relaciones con los cursos
            if (estudiante.getCursos() != null && !estudiante.getCursos().isEmpty()) {
                // Crear una copia de la lista para evitar ConcurrentModificationException
                List<Curso> cursosACopiar = new ArrayList<>(estudiante.getCursos());
                
                for (Curso curso : cursosACopiar) {
                    // Remover el estudiante de la lista de estudiantes del curso
                    curso.removerEstudiante(estudiante);
                    cursoRepository.save(curso);
                }
                
                // Limpiar la lista de cursos del estudiante
                estudiante.getCursos().clear();
                estudianteRepository.save(estudiante);
            }
            
            // Ahora eliminar el estudiante
            estudianteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
