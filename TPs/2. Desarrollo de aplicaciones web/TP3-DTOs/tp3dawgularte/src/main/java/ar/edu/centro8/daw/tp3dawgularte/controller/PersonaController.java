package ar.edu.centro8.daw.tp3dawgularte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp3dawgularte.model.Persona;
import ar.edu.centro8.daw.tp3dawgularte.service.IPersonaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PersonaController {

    @Autowired
    private IPersonaService personaSvc;

    @GetMapping("/personas/listar")
    public List<Persona> listarPersonas() {
        return personaSvc.getPersonas();
    }

    @GetMapping("/persona/listar/{id}")
    public Persona listrarPersonaPorId(@PathVariable Long id) {
        return personaSvc.findPersona(id);
    }
    
    @PostMapping("/persona/crear")
    public String guardarPersona(@RequestBody Persona personaNueva) {
        personaSvc.savePersona(personaNueva);
        return "Se creó la nueva persona " + personaNueva;
    }

    @PutMapping("persona/modificar/{id}")
    public String modificarPersona(@PathVariable Long id, @RequestBody Persona personaModificadPersona) {
        personaSvc.editPersona(personaModificadPersona);
        return "Se modificó la persona " + personaModificadPersona;
    }

    @DeleteMapping("/persona/borrar/{id}")
    public String borrarPersona(@PathVariable Long id) {
        personaSvc.deletePersona(id);
        return "Se borró con éxito la persona de id " + id;
    }
    
}
