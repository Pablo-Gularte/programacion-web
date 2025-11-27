package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.centro8.tifigwdaw.escuelagulartep.services.AsistenciaService;

@Controller
public class AsistenciaController {
    private final String urlObtenerTodasLasAsistencias = "/asistencias";

    @Autowired
    AsistenciaService asistenciaSvc;

    @GetMapping(urlObtenerTodasLasAsistencias)
    public String obtenerTodasLasAsistencias(Model model) {
        model.addAttribute("asistencias", asistenciaSvc.obtenerTodasLasAsistencias());
        return "asist";
    }
}
