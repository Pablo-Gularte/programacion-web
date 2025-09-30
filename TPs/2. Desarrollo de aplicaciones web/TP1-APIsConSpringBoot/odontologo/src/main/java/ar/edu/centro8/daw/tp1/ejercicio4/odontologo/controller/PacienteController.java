package ar.edu.centro8.daw.tp1.ejercicio4.odontologo.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.daw.tp1.ejercicio4.odontologo.model.Paciente;
import ar.edu.centro8.daw.tp1.ejercicio4.odontologo.service.PacienteService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService ps) {
        this.pacienteService = ps;
    }

    @GetMapping("/")
    public String inicio() {
        String cadena;
        cadena = "Esta API recupera los datos de los pacientes.";
        cadena += "<br>";
        cadena += "<ul>";
        cadena += "<li>Ingrese <b style='color:red'>/pacientes</b> para obtener los datos de todos los pacientes</li>";
        cadena += "<li>Ingrese <b style='color:red'>/pacientes/menores</b> para obtener los datos de los pacientes menores de edad</li>";
        cadena += "</ul>";
        return cadena;
    }
    
    @GetMapping("/pacientes")
    public String mostrarTodosLosPacientes() {
        String cadena;
        cadena = "Listado completo de pacientes:";
        cadena += "<ul>"; 
        for (Paciente paciente : pacienteService.obtenerPacientes()) {
            cadena += "<li>ID: " + paciente.getId() + " - DNI: " + paciente.getDni() + " - Nombre: " + paciente.getNombre() + " - Apellido: " + paciente.getApellido() + " - Fecha de nacimiento: " + paciente.getFechaNacimiento() + "</li>"; 
        }
        cadena += "</ul>"; 
        return cadena;
    }

    @GetMapping("/pacientes/menores")
    public String mostrarPacientesMEnoresDeEdad() {
        String cadena;
        cadena = "Listado completo de pacientes menores de edad:";
        cadena += "<ul>"; 
        for (Paciente paciente : pacienteService.obtenerPacientes()) {
            if (Period.between(paciente.getFechaNacimiento(), LocalDate.now()).getYears() >= 18) {
                cadena += "<li>ID: " + paciente.getId() + " - DNI: " + paciente.getDni() + " - Nombre: " + paciente.getNombre() + " - Apellido: " + paciente.getApellido() + " - Fecha de nacimiento: " + paciente.getFechaNacimiento() + "</li>"; 
            }
        }
        cadena += "</ul>"; 
        return cadena;
    }
    
    
}
