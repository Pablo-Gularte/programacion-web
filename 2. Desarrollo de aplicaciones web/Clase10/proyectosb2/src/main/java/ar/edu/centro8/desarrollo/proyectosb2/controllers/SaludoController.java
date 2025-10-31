package ar.edu.centro8.desarrollo.proyectosb2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.centro8.desarrollo.proyectosb2.entities.Cliente;


@Controller
public class SaludoController {
    @GetMapping("/saludo")
    public String saludar(Model model) {
        model.addAttribute("saludo", "Hola desde thymeleaf");
        return "hola";
    }
    
    @GetMapping("/saludo2")
    public String saludar2(Model model, @RequestParam String nombre) {
        model.addAttribute("saludo2", "Te llamas "+nombre);
        return "hola2";
    }

    @GetMapping("/cliente/traer")
    public String traerCliente(Model model) {
    List<Cliente> clientes = new ArrayList<>();
    clientes.add(new Cliente("Juan", 25));
    clientes.add(new Cliente("Maria", 30));
    clientes.add(new Cliente("Luis", 20));

    model.addAttribute("clientes", clientes);
    return "listado";
}


    
}
