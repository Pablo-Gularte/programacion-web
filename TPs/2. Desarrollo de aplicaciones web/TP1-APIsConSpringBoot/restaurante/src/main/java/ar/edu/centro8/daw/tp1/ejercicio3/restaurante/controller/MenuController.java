package ar.edu.centro8.daw.tp1.ejercicio3.restaurante.controller;

import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp1.ejercicio3.restaurante.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class MenuController {
private final MenuService menuService;

    @Autowired
    public MenuController(MenuService ms) {
        this.menuService = ms;
    }

    @GetMapping("/")
    public String inicio() {
        String cadena;
        cadena = "Esta API recupera los datos del plato del menpú a partir del ID indicado.";
        cadena += "<br>";
        cadena += "<ul>";
        cadena += "<li>Ingrese <b style='color:red'>/plato/1</b> para obtener los datos del plato <b>1</b> del menú</li>";
        cadena += "<li>Ingrese <b style='color:red'>/plato/2</b> para obtener los datos del plato <b>2</b> del menú</li>";
        cadena += "<li>Ingrese <b style='color:red'>/plato/3</b> para obtener los datos del plato <b>3</b> del menú</li>";
        cadena += "<li>Ingrese <b style='color:red'>/plato/4</b> para obtener los datos del plato <b>4</b> del menú</li>";
        cadena += "<li>Ingrese <b style='color:red'>/plato/5</b> para obtener los datos del plato <b>5</b> del menú</li>";
        cadena += "</ul>";
        return cadena;
    }
 
    @GetMapping("plato/{id}")
    public String getMethodName(@PathVariable int id) {
        String cadena;
        if (id <    1 || id > 5) {
            cadena = "No hay registros de un plato con ID " + id + " en el menú.";
        } else {
            cadena = "El detalle del plato solicitado es el siguiente: ";
            cadena += "<ul>";
            cadena += "<li>Nombre: " + menuService.obtenerPlato(id).getNombre() + "</li>";
            cadena += "<li>Descripción: " + menuService.obtenerPlato(id).getDescripcion() + "</li>";
            cadena += "<li>Precio: " + menuService.obtenerPlato(id).getPrecio() + "</li>";
            cadena += "</ul>";
        }
        return cadena;
    }
    
}
