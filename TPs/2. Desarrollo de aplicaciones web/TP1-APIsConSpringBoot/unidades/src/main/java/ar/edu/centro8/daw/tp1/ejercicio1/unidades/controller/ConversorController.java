package ar.edu.centro8.daw.tp1.ejercicio1.unidades.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ConversorController {
    private final double FACTOR_CONVERSION = 3.78541;   /* 1 galón es equivalente a 3,78541 litros. */

    @GetMapping("/")
    public String inicio() {
        String cadena;
        cadena = "Esta API convierte unidades de medida de combustible, de galones a litros y de litros a galones.";
        cadena += "<br>";
        cadena += "<ul>";
        cadena += "<li>Ingrese <b style='color:red'>/galones/123</b> para convertir <b>123 galones a litos</b></li>";
        cadena += "<li>Ingrese <B style='color:red'>/litros/123</b> para convertir <b>123 litros a galones</b></li>";
        cadena += "</ul>";
        return cadena;
    }
    
    @GetMapping("/galones/{galones}")
    public String galonesLitros(@PathVariable double galones) {
        String cadena;
        double litros = galones * FACTOR_CONVERSION;

        cadena = "A " + galones + " galones le corresponden " + litros + " litros";
        return cadena;
    }
 
    @GetMapping("/litros/{litros}")
    public String litrosGalones(@PathVariable double litros) {
        String cadena;
        double galones = litros / FACTOR_CONVERSION;

        cadena = "A " + litros + " litros le corresponden " + galones + " galones";
        return cadena;
    }
}
    
