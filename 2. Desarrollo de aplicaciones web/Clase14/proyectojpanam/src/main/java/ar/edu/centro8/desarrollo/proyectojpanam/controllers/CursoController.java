package ar.edu.centro8.desarrollo.proyectojpanam.controllers;

import ar.edu.centro8.desarrollo.proyectojpanam.models.Curso;
import ar.edu.centro8.desarrollo.proyectojpanam.models.Estudiante;
import ar.edu.centro8.desarrollo.proyectojpanam.repositories.CursoRepository;
import ar.edu.centro8.desarrollo.proyectojpanam.repositories.EstudianteRepository;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> traerCursos() {
        // Limpiar referencias huérfanas antes de retornar
        limpiarReferenciasHuerfanas();
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }
    
    /**
     * Método para limpiar referencias huérfanas de cursos eliminados
     * que puedan estar en las listas de estudiantes
     */
    private void limpiarReferenciasHuerfanas() {
        try {
            // Primero limpiar la tabla intermedia con consulta nativa
            cursoRepository.limpiarReferenciasHuerfanas();
            
            // Luego actualizar las entidades en memoria
            List<Estudiante> todosLosEstudiantes = estudianteRepository.findAll();
            List<Long> idsCursosExistentes = cursoRepository.findAll()
                .stream()
                .map(Curso::getId)
                .toList();
            
            for (Estudiante estudiante : todosLosEstudiantes) {
                if (estudiante.getCursos() != null && !estudiante.getCursos().isEmpty()) {
                    // Crear nueva lista solo con cursos válidos
                    List<Curso> cursosValidos = new ArrayList<>();
                    
                    for (Curso curso : estudiante.getCursos()) {
                        if (curso != null && curso.getId() != null && 
                            idsCursosExistentes.contains(curso.getId())) {
                            cursosValidos.add(curso);
                        }
                    }
                    
                    // Si la lista cambió, actualizar el estudiante
                    if (cursosValidos.size() != estudiante.getCursos().size()) {
                        estudiante.setCursos(cursosValidos);
                        estudianteRepository.save(estudiante);
                    }
                }
            }
        } catch (Exception e) {
            // Log del error pero no interrumpir el flujo
            System.err.println("Error limpiando referencias huérfanas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> traerCursoPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        // Crear el curso básico
        Curso cursoGuardado = new Curso(curso.getNombre());
        
        // Si el curso viene con estudiantes, procesarlos antes de guardar
        if (curso.getEstudiantes() != null && !curso.getEstudiantes().isEmpty()) {
            List<Estudiante> estudiantesFinales = new ArrayList<>();
            
            for (Estudiante estudianteInfo : curso.getEstudiantes()) {
                Estudiante estudiante = null;
                
                if (estudianteInfo.getId() != null) {
                    // Buscar estudiante existente por ID
                    Optional<Estudiante> estudianteExistente = estudianteRepository.findById(estudianteInfo.getId());
                    if (estudianteExistente.isPresent()) {
                        estudiante = estudianteExistente.get();
                    }
                } else if (estudianteInfo.getNombre() != null && !estudianteInfo.getNombre().trim().isEmpty()) {
                    // Crear nuevo estudiante si no tiene ID pero tiene nombre
                    estudiante = new Estudiante(estudianteInfo.getNombre());
                    estudiante = estudianteRepository.save(estudiante);
                }
                
                if (estudiante != null) {
                    estudiantesFinales.add(estudiante);
                }
            }
            
            // Establecer las relaciones directamente en la lista
            cursoGuardado.setEstudiantes(estudiantesFinales);
        }
        
        // Guardar el curso con todas las relaciones
        cursoGuardado = cursoRepository.save(cursoGuardado);
        
        // IMPORTANTE: También necesitamos actualizar el lado del Estudiante (owner de la relación)
        if (cursoGuardado.getEstudiantes() != null) {
            for (Estudiante estudiante : cursoGuardado.getEstudiantes()) {
                if (estudiante.getCursos() == null) {
                    estudiante.setCursos(new ArrayList<>());
                }
                if (!estudiante.getCursos().contains(cursoGuardado)) {
                    estudiante.getCursos().add(cursoGuardado);
                    estudianteRepository.save(estudiante);
                }
            }
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoGuardado);
    }
    
    @PutMapping("/editar/curso/{id_original}")
    public ResponseEntity<Curso> editarCurso(@PathVariable Long id_original,
        @RequestParam(required = false, name = "id") Long nuevaId,
        @RequestParam(required = false, name = "nombre") String nuevoNombre) {
        
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Curso> optionalCurso = cursoRepository.findById(id_original);
        if (optionalCurso.isPresent()) {
            Curso existingCurso = optionalCurso.get();
            existingCurso.setNombre(nuevoNombre);
            Curso cursoActualizado = cursoRepository.save(existingCurso);
            return ResponseEntity.ok(cursoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/limpiar-referencias")
    @Transactional
    public ResponseEntity<String> limpiarTodasLasReferenciasHuerfanas() {
        try {
            limpiarReferenciasHuerfanas();
            return ResponseEntity.ok("Referencias huérfanas limpiadas correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al limpiar referencias: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            
            // Método más directo: obtener estudiantes directamente asociados al curso
            if (curso.getEstudiantes() != null) {
                // Crear una copia para evitar ConcurrentModificationException
                List<Estudiante> estudiantesAsociados = new ArrayList<>(curso.getEstudiantes());
                
                // Remover las relaciones bidireccionales
                for (Estudiante estudiante : estudiantesAsociados) {
                    if (estudiante.getCursos() != null) {
                        estudiante.getCursos().removeIf(c -> c.getId().equals(id));
                        estudianteRepository.save(estudiante);
                    }
                }
                
                // Limpiar la lista del curso
                curso.getEstudiantes().clear();
                cursoRepository.save(curso);
            }
            
            // También buscar cualquier otra referencia suelta
            List<Estudiante> todosLosEstudiantes = estudianteRepository.findAll();
            for (Estudiante estudiante : todosLosEstudiantes) {
                if (estudiante.getCursos() != null) {
                    boolean removed = estudiante.getCursos().removeIf(c -> 
                        c != null && c.getId() != null && c.getId().equals(id));
                    if (removed) {
                        estudianteRepository.save(estudiante);
                    }
                }
            }
            
            // Limpiar tabla intermedia directamente
            cursoRepository.limpiarReferenciasHuerfanas();
            
            // Finalmente eliminar el curso
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
