package ar.edu.centro8.daw.tp1.ejercicio5.estaturas.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp1.ejercicio5.estaturas.model.Jugador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class JugadorController {
    @GetMapping("/")
    public String inicio() {
        String cadena;
        cadena = "Esta API da de alta los datos de jugadores de básquet en una bases de datos lógica representada por una lista de tipo ArrayList y muestra como respuesta el promedio de estatura de todos los jugadores ingresados.";
        cadena += "<br>";
        cadena += "<strong>ATENCION</strong>: enviar los datos de los jugadores con método <strong style='color: red'>POST</strong> utilizando recursos como <i style='color: blue'>Postman</i>";
        return cadena;
    }

    @PostMapping("/jugadores/crear")
    @ResponseBody
    public List<Jugador> altaJugadores(@RequestBody List<Jugador> jugadores) {
        double sumaEstaturas = 0.0;
        int cantidadJugadores = jugadores.size();
        for (Jugador jugador : jugadores) {
            sumaEstaturas += jugador.getEstatura();
        }
        double promedioEstaturas = cantidadJugadores > 0 ? sumaEstaturas / cantidadJugadores : 0.0;
        System.out.println("Promedio de estaturas: " + promedioEstaturas);
        return jugadores;
    }
    
}
