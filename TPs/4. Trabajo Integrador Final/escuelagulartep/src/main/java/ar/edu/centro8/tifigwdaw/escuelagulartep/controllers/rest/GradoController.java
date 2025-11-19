package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers.rest;

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

import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoRequestDTO;
import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoResponseDTO;
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
    public ResponseEntity<List<GradoResponseDTO>> obtenerTodosLosGrados() {
        try {
            List<GradoResponseDTO> grados = gradoSvc.obtenerTodosLosGrados();
            return ResponseEntity.ok(grados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(urlObtenerGradoPorId)
    public ResponseEntity<GradoResponseDTO> obtenerGradoPorId(@PathVariable Long id) {
        try {
            GradoResponseDTO grado = gradoSvc.obtenerGradoPorId(id);
            return ResponseEntity.ok(grado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(urlCrearGrado)
    public ResponseEntity<GradoResponseDTO> crearGrado(@RequestBody GradoRequestDTO gradoDTO) {
        try {
            GradoResponseDTO nuevoGrado = gradoSvc.crearGrado(gradoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(urlEditarGrado)
    public ResponseEntity<GradoResponseDTO> editarGrado(@PathVariable Long id, @RequestBody GradoRequestDTO gradoDTO) {
        try {
            GradoResponseDTO gradoActualizado = gradoSvc.modificarGrado(id, gradoDTO);
            return ResponseEntity.ok(gradoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(urlBorrarGrado)
    public ResponseEntity<Void> borrarGrado(@PathVariable Long id) {
        try {
            gradoSvc.eliminarGrado(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
