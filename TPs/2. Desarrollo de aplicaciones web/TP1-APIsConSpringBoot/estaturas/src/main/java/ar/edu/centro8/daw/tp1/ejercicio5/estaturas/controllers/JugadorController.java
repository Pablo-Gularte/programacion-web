package ar.edu.centro8.daw.tp1.ejercicio5.estaturas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp1.ejercicio5.estaturas.model.Jugador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class JugadorController {
    List<Jugador> totalJugadores = new ArrayList<>();

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
    public String altaJugadores(@RequestBody List<Jugador> jugadores) {
        totalJugadores.addAll(jugadores);
        String cadena = "Se han ingresado " + jugadores.size() + " jugadores en este envío.";
        cadena += "<br>El total de jugadores ingresados hasta el momento es: " + totalJugadores.size();
        double sumaEstaturas = 0.0;
        int cantidadJugadores = jugadores.size();
        
        for (Jugador jugador : jugadores) {
            sumaEstaturas += jugador.getEstatura();
        }
        double promedioEstaturas = cantidadJugadores > 0 ? sumaEstaturas / cantidadJugadores : 0.0;
        cadena += "<br>El promedio de estaturas de los jugadores de este envío es: " + promedioEstaturas;
        
        // Calculo de promedio total de estaturas
        sumaEstaturas = 0.0;
        for (Jugador jugador : totalJugadores) {
            sumaEstaturas += jugador.getEstatura();
        }
        cantidadJugadores = totalJugadores.size();
        promedioEstaturas = cantidadJugadores > 0 ? sumaEstaturas / cantidadJugadores : 0.0;
        cadena += "<br>El promedio total de estaturas de los jugadores es: " + promedioEstaturas;
        return cadena;
    }

    @PostMapping("/json/jugadores/crear")
    @ResponseBody
    public List<Jugador> altaJugadoresJson(@RequestBody List<Jugador> jugadores) {
        totalJugadores.addAll(jugadores);
        return totalJugadores;
    }
    
}
