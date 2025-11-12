package ar.edu.centro8.desarrollo.proyectojpa1an.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyectojpa1an.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1an.services.AutoService;

@RestController
@RequestMapping("/api/autos")
public class AutoController {
    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> getAllAutos() {
        return autoService.obtenerAutos();
    }

    @GetMapping("/{id}")
    public Auto getAutoById(@PathVariable Long id) {
        return autoService.traerAuto(id);
    }

    @PostMapping
    public void createAuto(@RequestBody Auto auto) {
        autoService.guardarAuto(auto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuto(@PathVariable Long id, @RequestBody Auto auto) {
        try {
            // Validar que se proporcionen datos para actualizar
            if (auto.getMarca() == null && auto.getPrecio() == 0.0) {
                return ResponseEntity.status(400).body("Debe proporcionar al menos un campo para actualizar");
            }
            
            // ✅ NO pasar auto.getId() - usar solo el id del path
            Auto autoActualizado = autoService.editarAuto(id, null, auto.getMarca(), auto.getPrecio());
            
            if (autoActualizado != null) {
                return ResponseEntity.ok(autoActualizado);
            } else {
                return ResponseEntity.status(404).body("Auto no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable Long id) {
        autoService.eliminarAuto(id);
    }
}
