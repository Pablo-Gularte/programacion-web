package ar.edu.centro8.daw.tp3dawgularte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp3dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp3dawgularte.dto.AutoResponsetDTO;
import ar.edu.centro8.daw.tp3dawgularte.service.IAutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AutoController {
    @Autowired
    private IAutoService autoSvc;

    // Muestro eli listado completo de autos
    @GetMapping("/autos/listar")
    public ResponseEntity<List<AutoResponsetDTO>> listarTodosLosAutos() {
        try {
            List<AutoResponsetDTO> autos = autoSvc.getAllAutos();
            return ResponseEntity.ok(autos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Muestro el auto correspondiente al ID pasado por parámetro
    @GetMapping("/auto/listar/{id}")
    public ResponseEntity<?> mostrarAutoPorId(@PathVariable Long id) {
        try {
            AutoResponsetDTO auto = autoSvc.getAutoById(id);
            return ResponseEntity.ok(auto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Creo el auto recibodo por parámetro
    @PostMapping("/auto/crear/")
    public ResponseEntity<?> crearAuto(@RequestBody AutoRequestDTO autoNuevo) {
        try {
            AutoResponsetDTO autoCreado = autoSvc.saveAuto(autoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(autoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Modifico los datos del auto de ID recibidopor parámetro
    @PutMapping("/auto/editar/{id}")
    public ResponseEntity<?> editarAutoPorId(@PathVariable Long id, @RequestBody AutoRequestDTO autoModificado) {
        try {
            AutoResponsetDTO autoActualizado = autoSvc.editAuto(id, autoModificado);
            return ResponseEntity.ok(autoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Elimino el auto del ID recibido por parámetro
    @DeleteMapping("/auto/borrar/{id}")
    public ResponseEntity<String> borrarAutoPorId(@PathVariable Long id) {
        try {
            autoSvc.deleteAuto(id);
            return ResponseEntity.ok("El auto de id " + id + " se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }
}
