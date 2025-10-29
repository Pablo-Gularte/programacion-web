package ar.edu.centro8.daw.tpn7igw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tpn7igw.models.Auto;
import ar.edu.centro8.daw.tpn7igw.services.IAutoService;
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
    public List<Auto> listarAutos() {
        return autoSvc.getAutos();
    }

    @GetMapping("/auto/{id}")
    public Auto listarAutoPorId(@PathVariable Long id) {
        return autoSvc.findAuto(id);
    }
    
    @PostMapping("/autos")
    public String crearAuto(@RequestBody Auto nuevoAuto) {
        autoSvc.saveAuto(nuevoAuto);
        return "Se creo el nuevo auto " + nuevoAuto;
    }
    
    @PutMapping("/auto/{id}")
    public Auto editarAuto(@PathVariable Long id, @RequestBody Auto auto) {
        autoSvc.editAuto(id, auto.getMarca(), auto.getPrecio());
        return auto;
    }

    @DeleteMapping("/auto/{id}")
    public ResponseEntity<Void> borrarAuto(@PathVariable Long id) {
        System.out.println("*************************************");
        System.out.println("Invoco método borrarAuto para id: " + id);
        System.out.println("*************************************");
        autoSvc.deleteAuto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
