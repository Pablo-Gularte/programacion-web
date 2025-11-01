package ar.edu.centro8.daw.tp4dawgularte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.services.IAutoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AutoController {

    @Autowired
    private IAutoService autoSvc;

    @GetMapping("/")
    public String inicio(Model modelo) {
        List<AutoResponseDTO> autos = autoSvc.getAutos();
        modelo.addAttribute("autos", autos);
        modelo.addAttribute("totalRegistros", autos.size());
        return "index"; // Spring Boot buscará 'src/main/resources/templates/index.html'
    }

    @GetMapping("/autos")
    public String listarAutos(Model modelo) {
        return "index";
    }
    

}
