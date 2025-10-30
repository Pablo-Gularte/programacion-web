package ar.edu.centro8.daw.tp4dawgularte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.models.Auto;
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
    public String crearAuto(@RequestBody Auto nuevoAuto) {
        autoSvc.saveAuto(nuevoAuto);
        return "Se creo el nuevo auto " + nuevoAuto;
    }
    
    @PutMapping("/autos/{id}")
    public Auto editarAuto(@PathVariable Long id, @RequestBody Auto auto) {
        autoSvc.editAuto(id, auto.getMarca(), auto.getPrecio());
        return auto;
    }

    @DeleteMapping("/autos/{id}")
    public String borrarAuto(@PathVariable Long id) {
        autoSvc.deleteAuto(id);
        return "Se eliminó correctamente el auto de id " + id;
    }
}
