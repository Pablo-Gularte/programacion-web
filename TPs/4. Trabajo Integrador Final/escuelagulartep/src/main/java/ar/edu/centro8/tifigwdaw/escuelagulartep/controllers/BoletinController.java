package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Boletin;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.BoletinService;

@Controller
public class BoletinController {
    private final String urlObtenerTodosLosBoletines = "/boletines";
    // private final String urlObtenerBoletinPorId = "/boletines/{id}";
    // private final String urlObtenerBoletinPorEstudiante = "/boletines/estudiante/{id}";
    // private final String urlCrearBoletin = "/boletines/nuevo";
    // private final String urlEditarBoletin = "/boletines/editar/{id}";
    // private final String urlBorrarBoletin = "/boletines/borrar/{id}";

    @Autowired
    BoletinService boletinSvc;

    @GetMapping(urlObtenerTodosLosBoletines)
    public String obtenerTodosLosBoletines(Model modelo) {
        List<Boletin> boletines = boletinSvc.obtenerTodosLosBoletines();
        modelo.addAttribute("boletines", boletines);
        return "boletin";
    }
}
