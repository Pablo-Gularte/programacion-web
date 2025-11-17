package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GradoController {
    private final String urlObtenerTodosLosGrados = "/grados";
    private final String urlObtenerGradoPorId = "/grados/{id}";
    private final String urlCrearGrado = "/grados/nuevo";
    private final String urlModificarGrado = "/grados/modificar/{id}";
    private final String urlEliminarGrado = "/grados/eliminar/{id}";

    @Autowired 
    private IGradoRepository gradoRepo;

    @GetMapping(urlObtenerTodosLosGrados)
    public ResponseEntity<List<Grado>> obtenerTodosLosGrados() {
        try {
            List<Grado> grados = gradoRepo.findAll();
            return ResponseEntity.ok(grados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(urlCrearGrado)
    public ResponseEntity<Grado> crearGrado(@RequestBody Grado grado) {
        try {
            // Verifico si el nuevo grado viene con lista de estudiantes y en ese caso asigno el grado correspondiente a cada estudiante
            if(grado.getEstudiantes() != null && !grado.getEstudiantes().isEmpty()) {
                for (Estudiante estudiante : grado.getEstudiantes()) {
                    estudiante.setGrado(grado);
                }
            }
            Grado nuevoGrado = gradoRepo.save(grado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(urlObtenerGradoPorId)
    public ResponseEntity<Grado> obtenerGradoPorId(@PathVariable Long id) {
        try {
            return gradoRepo.findById(id)
                    .map(grado -> ResponseEntity.ok(grado))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(urlModificarGrado)
    public ResponseEntity<?> modificarGrado(@PathVariable Long id, @RequestBody Grado gradoAModificar) {
        try {
            Optional<Grado> gradoEnBD = gradoRepo.findById(id);
            if(gradoEnBD.isPresent()) {
                // Obtengo el grado de la BD y lo guardo temporalmente en la variable grado para modificar los atributos que hayan cambiado
                Grado grado = gradoEnBD.get();
                // Valido qué atributos cambian de valor y los actualizo en la variable temporal grado
                if(gradoAModificar.getNombreGrado() != null && !gradoAModificar.getNombreGrado().equals(grado.getNombreGrado())) 
                    grado.setNombreGrado(gradoAModificar.getNombreGrado());
                if(gradoAModificar.getCiclo() != grado.getCiclo()) 
                    grado.setCiclo(gradoAModificar.getCiclo());
                if(gradoAModificar.getTurno() != null && !gradoAModificar.getTurno().equals(grado.getTurno())) 
                    grado.setTurno(gradoAModificar.getTurno());
                if(gradoAModificar.getDocente() != null && !gradoAModificar.getDocente().equals(grado.getDocente())) 
                    grado.setDocente(gradoAModificar.getDocente());
                if(gradoAModificar.isActivo() != grado.isActivo()) grado.setActivo(gradoAModificar.isActivo());
                
                // Si hay lista de estudiantes para actualizar, la guardo en la variable grado y le actualizo el grado a cada estudiante
                if(gradoAModificar.getEstudiantes() != null) {
                    grado.setEstudiantes(gradoAModificar.getEstudiantes());
                    for (Estudiante estudiante : grado.getEstudiantes()) {
                        estudiante.setGrado(grado);
                    }
                }
                gradoRepo.save(grado);
                return ResponseEntity.ok(grado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(urlEliminarGrado)
    public ResponseEntity<?> eliminarGrado(@PathVariable Long id) {
        try {
            Optional<Grado> gradoEnBD = gradoRepo.findById(id);
            if(gradoEnBD.isPresent()) {
                gradoRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
