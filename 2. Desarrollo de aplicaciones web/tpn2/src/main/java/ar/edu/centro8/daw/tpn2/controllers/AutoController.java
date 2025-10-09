package ar.edu.centro8.daw.tpn2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tpn2.models.Auto;
import ar.edu.centro8.daw.tpn2.services.IAutoService;
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

    @GetMapping("/")
    public String inicio() {
        return "Pantalla de inicio del TP2 de JPA-Hibernate del curso DAW";
    }
    
    @GetMapping("/autos/listar")
    public List<Auto> listarAutos() {
        return autoSvc.getAutos();
    }

    @GetMapping("/autos/listar/{id}")
    public Auto listarAutoPorId(@PathVariable Long id) {
        return autoSvc.findAuto(id);
    }
    
    @PostMapping("/autos/crear")
    public String crearAuto(@RequestBody Auto nuevoAuto) {
        autoSvc.saveAuto(nuevoAuto);
        return "Se creo el nuevo auto " + nuevoAuto;
    }
    
    @PutMapping("/autos/editar/{id}")
    public Auto editarAuto(@PathVariable Long id, @RequestBody Auto auto) {
        System.out.println("--> METODO editarAuto del Servicio");
        System.out.println( "VALOR DEL OBJETO autoEditado");
        auto.setId(id);
        System.out.println(auto);
        autoSvc.editAuto(id, auto.getMarca(), auto.getPrecio());
        return auto;
    }

    @DeleteMapping("/autos/borrar/{id}")
    public String borrarAuto(@PathVariable Long id) {
        autoSvc.deleteAuto(id);
        return "Se eliminó correctamente el auto de id " + id;
    }
}
