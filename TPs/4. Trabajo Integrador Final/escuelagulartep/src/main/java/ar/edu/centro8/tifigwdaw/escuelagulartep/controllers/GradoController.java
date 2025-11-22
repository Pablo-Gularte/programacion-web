package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.GradoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GradoController {
    private final String urlObtenerTodosLosGrados = "/grados";
    private final String urlObtenerGradoPorId = "/grados/{id}";
    private final String urlCrearGrado = "/grados/nuevo";
    private final String urlEditarGrado = "/grados/editar/{id}";
    private final String urlBorrarGrado = "/grados/borrar/{id}";

    @Autowired 
    private GradoService gradoSvc;

    @GetMapping(urlObtenerTodosLosGrados)
    public ResponseEntity<List<Grado>> obtenerTodosLosGrados() {
        List<Grado> grados = gradoSvc.obtenerTodosLosGrados();
        return ResponseEntity.ok(grados);
    }

    @GetMapping(urlObtenerGradoPorId)
    public ResponseEntity<Grado> obtenerGradoPorId(@PathVariable Long id) {
        Grado grado = gradoSvc.obtenerGradoPorId(id);
        return ResponseEntity.ok(grado);
    }

    @PostMapping(urlCrearGrado)
    public ResponseEntity<Grado> crearGrado(@RequestBody Grado gradoDTO) {
        Grado nuevoGrado = gradoSvc.crearGrado(gradoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrado);
    }

    @PutMapping(urlEditarGrado)
    public ResponseEntity<Grado> editarGrado(@PathVariable Long id, @RequestBody Grado gradoDTO) {
        Grado gradoActualizado = gradoSvc.modificarGrado(id, gradoDTO);
        return ResponseEntity.ok(gradoActualizado);
    }

    @DeleteMapping(urlBorrarGrado)
    public ResponseEntity<Void> borrarGrado(@PathVariable Long id) {
        gradoSvc.eliminarGrado(id);
        return ResponseEntity.noContent().build();
    }
}
