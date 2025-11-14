package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoResponseDTO;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.GradoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GradoController {
    private final String urlObtenerTodosLosGrados = "/grados";
    private final String urlObtenerEstudiantesPorGradoId = "/grados/{id}/estudiantes";

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
}
