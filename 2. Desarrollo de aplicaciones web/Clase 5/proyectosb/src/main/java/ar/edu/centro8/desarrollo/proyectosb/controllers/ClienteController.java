package ar.edu.centro8.desarrollo.proyectosb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyectosb.entities.Cliente;



@RestController
public class ClienteController {

    //endpoints
    @GetMapping("/")
    public String saludar() {
        return "Hola Mundo!!";
    }

    @GetMapping("/saludo/{nombre}")
    public String saludar2(@PathVariable String nombre) {
        return "Hola "+nombre+" !!";
    }
    
    @GetMapping("/saludo/{nombre}/{edad}")
    public String saludar3  (   @PathVariable String nombre,
                                @PathVariable int edad
                            ) {
        return "Hola "+nombre+" !!, tenes "+edad+" años";
    }
    
    @GetMapping("/saludo")
    public String saludar4  (   @RequestParam String nombre,
                                @RequestParam int edad
                            ) {
        return "Hola "+nombre+"!, se que tenes "+edad+" años";
    }

    @PostMapping("/cliente/mostrar")
    public void mostrarCliente(@RequestBody Cliente c) {
        System.out.println("Nombre: "+c.getNombre()+" Edad: "+c.getEdad());
    }

    @GetMapping("/cliente/traer")
    @ResponseBody
    public List<Cliente> traerCliente() {
        List<Cliente> clientes = new ArrayList<>();
        
        clientes.add(new Cliente("Juan", 25));
        clientes.add(new Cliente("Maria", 30));
        clientes.add(new Cliente("Luis", 20));

        return clientes;
    }
    
    


    
}
