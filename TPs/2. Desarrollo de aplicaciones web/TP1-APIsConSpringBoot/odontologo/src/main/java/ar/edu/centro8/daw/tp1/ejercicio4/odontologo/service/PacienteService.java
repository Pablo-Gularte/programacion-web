package ar.edu.centro8.daw.tp1.ejercicio4.odontologo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp1.ejercicio4.odontologo.model.Paciente;

@Service
public class PacienteService {
    private List<Paciente> pacientes;

    public PacienteService() {
        this.pacientes = new ArrayList<>();

        pacientes.add(new Paciente(1, "39110001", "Sofía", "Pérez", LocalDate.parse("2005-03-15")));
        pacientes.add(new Paciente(2, "39110002", "Martín", "Gómez", LocalDate.parse("2010-11-20")));
        pacientes.add(new Paciente(3, "39110003", "Valentina", "Rodríguez", LocalDate.parse("2015-01-08")));
        pacientes.add(new Paciente(4, "39110004", "Lucas", "Fernández", LocalDate.parse("2001-07-25")));
        pacientes.add(new Paciente(5, "39110005", "Emilia", "López", LocalDate.parse("2008-05-12")));
        pacientes.add(new Paciente(6, "39110006", "Javier", "Martínez", LocalDate.parse("1995-02-28")));
        pacientes.add(new Paciente(7, "39110007", "Camila", "Sánchez", LocalDate.parse("2012-09-03")));
        pacientes.add(new Paciente(8, "39110008", "Diego", "Díaz", LocalDate.parse("2019-04-17")));
        pacientes.add(new Paciente(9, "39110009", "Paula", "Flores", LocalDate.parse("2007-12-01")));
        pacientes.add(new Paciente(10, "39110010", "Andrés", "Benítez", LocalDate.parse("1988-10-10")));
        pacientes.add(new Paciente(11, "39110011", "Julieta", "Ruíz", LocalDate.parse("2014-06-22")));
        pacientes.add(new Paciente(12, "39110012", "Nicolás", "Castro", LocalDate.parse("2009-08-30")));
        pacientes.add(new Paciente(13, "39110013", "Marina", "Herrera", LocalDate.parse("2003-01-05")));
        pacientes.add(new Paciente(14, "39110014", "Matías", "Acosta", LocalDate.parse("2017-02-14")));
        pacientes.add(new Paciente(15, "39110015", "Lara", "Méndez", LocalDate.parse("2011-04-09")));
        pacientes.add(new Paciente(16, "39110016", "Facundo", "Silva", LocalDate.parse("2006-11-29")));
        pacientes.add(new Paciente(17, "39110017", "Belén", "Ortiz", LocalDate.parse("1975-04-04")));
        pacientes.add(new Paciente(18, "39110018", "Bruno", "Giménez", LocalDate.parse("2020-03-07")));
        pacientes.add(new Paciente(19, "39110019", "Clara", "Ponce", LocalDate.parse("2013-10-18")));
        pacientes.add(new Paciente(20, "39110020", "Gustavo", "Morales", LocalDate.parse("1999-06-16")));
        pacientes.add(new Paciente(21, "39110021", "Alma", "Núñez", LocalDate.parse("2008-01-27")));
        pacientes.add(new Paciente(22, "39110022", "Joaquín", "Ríos", LocalDate.parse("2016-12-05")));
        pacientes.add(new Paciente(23, "39110023", "Daniela", "Torres", LocalDate.parse("2004-09-19")));
        pacientes.add(new Paciente(24, "39110024", "Ricardo", "Rojas", LocalDate.parse("1965-11-01")));
        pacientes.add(new Paciente(25, "39110025", "Isabella", "Vargas", LocalDate.parse("2018-07-11")));
    }

    public List<Paciente> obtenerPacientes() {
        return pacientes;
    }
}
