package ar.edu.centro8.daw.tp4dawgularte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.centro8.daw.tp4dawgularte.services.AutoService;
import ar.edu.centro8.daw.tp4dawgularte.services.IAutoService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoController {

    @Autowired
    IAutoService autoSvc = new AutoService();

    @GetMapping("/inicio")
    public String inicio() {
        return "index"; // Spring Boot buscará 'src/main/resources/templates/index.html'
    }

}
