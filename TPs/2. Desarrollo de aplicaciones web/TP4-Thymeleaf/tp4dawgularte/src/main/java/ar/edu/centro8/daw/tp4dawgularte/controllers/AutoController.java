package ar.edu.centro8.daw.tp4dawgularte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.services.IAutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AutoController {

    @Autowired
    private IAutoService autoSvc;

    @GetMapping("/autos")
    public ResponseEntity<List<AutoResponseDTO>> listarAutos() {
        try {
            List<AutoResponseDTO> autosDto = autoSvc.getAutos();
            return ResponseEntity.ok(autosDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/autos/{id}")
    public ResponseEntity<?> listarAutoPorId(@PathVariable Long id) {
        try {
            AutoResponseDTO autoDto = autoSvc.findAuto(id);
            return ResponseEntity.ok(autoDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/autos")
    public ResponseEntity<?> crearAuto(@RequestBody AutoRequestDTO autoDTO) {
        try {
            AutoResponseDTO autoCreado = autoSvc.saveAuto(autoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(autoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    
    @PutMapping("/autos/{id}")
    public ResponseEntity<?> editarAuto(@PathVariable Long id, @RequestBody AutoRequestDTO autoDTO) {
        try {
            AutoResponseDTO autoEditado = autoSvc.editAuto(id, autoDTO);
            return ResponseEntity.ok().body(autoEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @DeleteMapping("/autos/{id}")
    public ResponseEntity<String> borrarAuto(@PathVariable Long id) {
        try {
            autoSvc.deleteAuto(id);
            return ResponseEntity.ok().body("Se eliminó correctamente el auto de ID = " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
}
