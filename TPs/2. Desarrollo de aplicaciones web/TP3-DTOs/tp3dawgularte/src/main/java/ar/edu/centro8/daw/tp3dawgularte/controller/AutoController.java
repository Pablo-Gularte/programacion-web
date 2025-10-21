package ar.edu.centro8.daw.tp3dawgularte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp3dawgularte.model.Auto;
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
    public List<Auto> listarTodosLosAutos() {
        return autoSvc.getAllAutos();
    }

    // Muestro el auto correspondiente al ID pasado por parámetro
    @GetMapping("/auto/listar/{id}")
    public Auto mostrarAutoPorId(@PathVariable Long id) {
        return autoSvc.getAutoById(id);
    }

    // Creo el auto recibodo por parámetro
    @PostMapping("/auto/crear/")
    public String crearAuto(@RequestBody Auto autoNuevo) {
        System.out.println("*****************");
        System.out.println(autoNuevo);
        System.out.println("*****************");
        autoSvc.saveAuto(autoNuevo);
        return "Se creó el auto " + autoNuevo;
    }

    // Modifico los datos del auto de ID recibidopor parámetro
    @PutMapping("/auto/editar/{id}")
    public String editarAutoPorId(@PathVariable Long id, @RequestBody Auto autoModificado) {
        autoSvc.editAuto(id, autoModificado.getMarca(), autoModificado.getPrecio());
        return "Se modificó el auto " + autoModificado;
    }

    // Elimino el auto del ID recibido por parámetro
    @DeleteMapping("/auto/borrar/{id}")
    public String borrarAutoPorId(@PathVariable Long id) {
        autoSvc.deleteAuto(id);
        return "Se eliminó el auto de ID " + id;
    }
}
