package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.BoletinService;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.EstudianteService;

@Controller
public class BoletinController {
    private final String urlObtenerTodosLosBoletines = "/boletines";
    private final String urlObtenerBoletinPorEstudiante = "/boletines/estudiante/{idEstudiante}";

    @Autowired BoletinService boletinSvc;;
    @Autowired EstudianteService estudianteSvc;

    @GetMapping(urlObtenerTodosLosBoletines)
    public String obtenerTodasLasAsistencias(Model model) {

        // 2. Inyectar los atributos para Thymeleaf
        model.addAttribute("tituloPagina", "Boletín de notas de Estudiantes");
        model.addAttribute("tituloVista", "Listado Completo de notas del boletín");
        model.addAttribute("listaNotas", boletinSvc.obtenerTodosLosBoletines());
        
        // 3. Especificar el fragmento de contenido a usar
        model.addAttribute("contenidoFragmento", "boletin/lista");
        
        return "plantilla-listados";
    }
    
    @GetMapping(urlObtenerBoletinPorEstudiante)
    public String obtenerAsistenciasPorEstudiante(Model model, @PathVariable Long idEstudiante) {
        Estudiante estudiante = estudianteSvc.obtenerEstudiantePorId(idEstudiante);

        model.addAttribute("tituloPagina", "Boletín de notas de Estudiantes");
        model.addAttribute("tituloVista", "Listado Completo de notas del boletín de");
        model.addAttribute("estudiante", estudiante.getNombre() + " " + estudiante.getApellido());
        model.addAttribute("grado", estudiante.getGrado().getNombre().getLeyendaUI());
        model.addAttribute("turno", estudiante.getGrado().getTurno().getLeyendaUI());
        model.addAttribute("listaNotas", boletinSvc.obtenerBoletinPorEstudiante(idEstudiante));
        
        // 3. Especificar el fragmento de contenido a usar
        model.addAttribute("contenidoFragmento", "boletin/lista-estudiante");
        return "plantilla-listados";
    }
}
