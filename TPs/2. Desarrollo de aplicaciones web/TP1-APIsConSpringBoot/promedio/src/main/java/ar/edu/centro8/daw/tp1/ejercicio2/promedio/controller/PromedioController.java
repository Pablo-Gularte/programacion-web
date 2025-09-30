package ar.edu.centro8.daw.tp1.ejercicio2.promedio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PromedioController {
    @GetMapping("/")
    public String inicio() {
        String cadena;
        cadena = "Esta API calcula el promedio de las tres notas enviadas por estudiante.";
        cadena += "<br>";
        cadena += "<ul>";
        cadena += "<li>Ingrese <b style='color:red'>/notas?nota1=7&amp;nota2=5&amp;nota3=10</b> para calcular el promedio de las notas <b>7, 5 y 10</b></li>";
        cadena += "<li>Ingrese <b style='color:red'>/notas?nota1=9&amp;nota2=8&amp;nota3=8</b> para calcular el promedio de las notas <b>9, 8 y 8</b></li>";
        cadena += "<li>etc.</li>";
        cadena += "</ul>";
        return cadena;
    }

    @GetMapping("notas")
    public String getMethodName(
        @RequestParam(name="nota1") Integer nota1,
        @RequestParam(name="nota2") Integer nota2,
        @RequestParam(name="nota3") Integer nota3
        ) {
            String cadena;

            if (nota1 != null && nota2 != null && nota3 != null) {
                double promedio = (nota1 + nota2 + nota3) / 3;
                cadena = "El promedio de notas es: " + promedio;
            } else {
               cadena = "Es necesario proporcionar los tres valores de notas para poder calcular el promedio."; 
            }
        return cadena;
    }
    
    
}
