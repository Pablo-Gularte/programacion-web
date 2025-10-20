package ar.edu.centro8.desarrollo.proyectojpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaRequestDTO;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaResponseDTO;
import ar.edu.centro8.desarrollo.proyectojpa.service.IPersonaService;

/**
 * Controlador REST para Persona
 * Ahora trabaja con DTOs para mejor separación de responsabilidades
 */
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class PersonaController {
    
    @Autowired
    private IPersonaService persoServ;
    
    /**
     * Obtener todas las personas
     */
    @GetMapping("/personas/traer")
    public ResponseEntity<List<PersonaResponseDTO>> getPersonas() {
        try {
            List<PersonaResponseDTO> personas = persoServ.getPersonas();
            return ResponseEntity.ok(personas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }   
    
    /**
     * Crear una nueva persona
     */
    @PostMapping("/personas/crear")
    public ResponseEntity<?> savePersona(@RequestBody PersonaRequestDTO personaDTO) {
        try {
            PersonaResponseDTO personaCreada = persoServ.savePersona(personaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }
    
    /**
     * Obtener una persona por ID
     */
    @GetMapping("/personas/{id}")
    public ResponseEntity<?> getPersona(@PathVariable Long id) {
        try {
            PersonaResponseDTO persona = persoServ.findPersona(id);
            return ResponseEntity.ok(persona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Eliminar una persona
     */
    @DeleteMapping("/personas/borrar/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id) {
        try {
            persoServ.deletePersona(id);
            return ResponseEntity.ok("La persona fue eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la persona");
        }
    }
    
    /**
     * Editar una persona
     */
    @PutMapping("/personas/editar/{id}")
    public ResponseEntity<?> editPersona(@PathVariable Long id, 
                                       @RequestBody PersonaRequestDTO personaDTO) {
        try {
            PersonaResponseDTO personaActualizada = persoServ.editPersona(id, personaDTO);
            return ResponseEntity.ok(personaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }
}
