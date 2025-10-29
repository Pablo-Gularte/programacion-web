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
    private final String urlListarTodosLosAutos = "/autos/listar/";
    private final String urlMostrarAutoPorId = "/auto/listar/{id}";
    private final String urlCrearAuto = "/auto/crear/";
    private final String urlEditarAutoPorId = "/auto/editar/{id}";
    private final String urlBorrarAutoPorId = "/auto/borrar/{id}";

    @Autowired
    private IAutoService autoSvc;

    // Muestro eli listado completo de autos
    @GetMapping(urlListarTodosLosAutos)
    public ResponseEntity<List<AutoResponsetDTO>> listarTodosLosAutos() {
        try {
            List<AutoResponsetDTO> autos = autoSvc.getAllAutos();
            return ResponseEntity.ok(autos);
        } catch (Exception e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Muestro el auto correspondiente al ID pasado por parámetro
    @GetMapping(urlMostrarAutoPorId)
    public ResponseEntity<?> mostrarAutoPorId(@PathVariable Long id) {
        try {
            AutoResponsetDTO auto = autoSvc.getAutoById(id);
            return ResponseEntity.ok(auto);
        } catch (IllegalArgumentException e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Creo el auto recibodo por parámetro
    @PostMapping(urlCrearAuto)
    public ResponseEntity<?> crearAuto(@RequestBody AutoRequestDTO autoNuevo) {
        try {
            AutoResponsetDTO autoCreado = autoSvc.saveAuto(autoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(autoCreado);
        } catch (IllegalArgumentException e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Modifico los datos del auto de ID recibidopor parámetro
    @PutMapping(urlEditarAutoPorId)
    public ResponseEntity<?> editarAutoPorId(@PathVariable Long id, @RequestBody AutoRequestDTO autoModificado) {
        try {
            AutoResponsetDTO autoActualizado = autoSvc.editAuto(id, autoModificado);
            return ResponseEntity.ok(autoActualizado);
        } catch (IllegalArgumentException e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Elimino el auto del ID recibido por parámetro
    @DeleteMapping(urlBorrarAutoPorId)
    public ResponseEntity<String> borrarAutoPorId(@PathVariable Long id) {
        try {
            autoSvc.deleteAuto(id);
            return ResponseEntity.ok("El auto de id " + id + " se ha eliminado correctamente");
        } catch (IllegalArgumentException e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ENDPOINT: " + urlListarTodosLosAutos);
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }
}
