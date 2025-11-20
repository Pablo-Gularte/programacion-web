package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IEstudianteRepository;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EstudianteService {
    private final String urlObtenerTodosLosEstudiantes = "/estudiantes";

    @Autowired
    IEstudianteRepository estudianteRepo;

    @GetMapping(urlObtenerTodosLosEstudiantes)
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepo.findAll();
    }
}
