package ar.edu.centro8.daw.dinamico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    
    //localhost:8080
    @GetMapping("/")
    public String saludar() {
        return "¡Hola Argentina!";
    }

    //localhost:8080/saludar/Juan/25
    @GetMapping("/saludar/{nombre}/{edad}")
    public String saludar2(@PathVariable String nombre, @PathVariable int edad) {
        return "¡Hola " + nombre + "! Tienes " + edad + " años.";
    }
}
