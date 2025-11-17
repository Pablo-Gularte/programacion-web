package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            Optional<Grado> gradoGuardado = gradoRepo.findById(id);
            if(gradoGuardado.isPresent()) {
                Grado grado = gradoGuardado.get();
                grado.setNombreGrado(gradoAModificar.getNombreGrado());
                grado.setCiclo(gradoAModificar.getCiclo());
                grado.setTurno(gradoAModificar.getTurno());
                grado.setDocente(gradoAModificar.getDocente());
                grado.setActivo(gradoAModificar.isActivo());
                grado.setEstudiantes(gradoAModificar.getEstudiantes());
                gradoRepo.save(grado);
                return ResponseEntity.ok(grado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
