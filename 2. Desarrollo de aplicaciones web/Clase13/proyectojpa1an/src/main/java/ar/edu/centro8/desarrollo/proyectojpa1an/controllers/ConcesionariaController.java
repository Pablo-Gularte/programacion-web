package ar.edu.centro8.desarrollo.proyectojpa1an.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyectojpa1an.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1an.models.Concesionaria;
import ar.edu.centro8.desarrollo.proyectojpa1an.services.ConcesionariaService;


@RestController
@RequestMapping("/api/concesionarias")
public class ConcesionariaController {

    @Autowired
    private  ConcesionariaService concesionariaService;

    @GetMapping
    public List<Concesionaria> getAllConcesionaria() {
        return concesionariaService.obtenerConcesionarias();
    }

    @GetMapping("/{id}")
    public Concesionaria getConcesionariaById(@PathVariable Long id) {
        return concesionariaService.traerConcesionaria(id);
    }

    @PostMapping
    public void createConcesionaria(@RequestBody Concesionaria concesionaria) {
        concesionariaService.guardarConcesionaria(concesionaria);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchConcesionaria(@PathVariable Long id, @RequestBody Concesionaria concesionaria) {
        try {
            // Validar que el nombre esté presente
            if (concesionaria.getNombre() == null || concesionaria.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("Campo 'nombre' es requerido");
            }
            
            // Verificar que la concesionaria existe
            Concesionaria existing = concesionariaService.traerConcesionaria(id);
            if (existing == null) {
                return ResponseEntity.status(404)
                    .body("Concesionaria con ID " + id + " no encontrada");
            }
            
            // Actualizar solo el nombre (actualización parcial)
            Concesionaria updated = concesionariaService.editarConcesionaria(
                id, existing.getId(), concesionaria.getNombre()
            );
            
            return ResponseEntity.ok(updated);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al actualizar concesionaria: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteConcesionaria(@PathVariable Long id) {
        concesionariaService.eliminarConcesionaria(id);
    }

    
    // AGREGADO - Crear auto para una concesionaria específica
    @PostMapping("/{id}/autos")
    public ResponseEntity<?> createAutoForConcesionaria(
        @PathVariable Long id,
        @RequestBody Auto auto
    ) {
        try {
            Concesionaria concesionaria = concesionariaService.traerConcesionaria(id);
            if (concesionaria == null) {
                return ResponseEntity.status(404)
                    .body("Concesionaria con ID " + id + " no encontrada");
            }
            
            // Establecer la relación bidireccional correctamente
            auto.setConcesionaria(concesionaria);
            concesionaria.agregarAuto(auto); // Importante para mantener ambos lados
            
            Auto autoGuardado = concesionariaService.guardarAuto(auto);
            
            return ResponseEntity.status(201) // 201 Created más apropiado
                .body(autoGuardado); // Retornar el objeto creado
                
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al crear el auto: " + e.getMessage());
        }
    }

    
}
