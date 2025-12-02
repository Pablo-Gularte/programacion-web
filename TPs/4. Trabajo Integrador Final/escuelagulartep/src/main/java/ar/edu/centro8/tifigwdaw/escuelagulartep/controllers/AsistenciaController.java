package ar.edu.centro8.tifigwdaw.escuelagulartep.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.AsistenciaService;
import ar.edu.centro8.tifigwdaw.escuelagulartep.services.EstudianteService;

@Controller
public class AsistenciaController {
    private final String urlObtenerTodasLasAsistencias = "/asistencias";
    private final String urlObtenerAsistenciasPorEstudiante = "/asistencias/estudiante/{idEstudiante}";

    @Autowired AsistenciaService asistenciaSvc;
    @Autowired EstudianteService estudianteSvc;

    @GetMapping(urlObtenerTodasLasAsistencias)
    public String obtenerTodasLasAsistencias(Model model) {

        // 2. Inyectar los atributos para Thymeleaf
        model.addAttribute("tituloPagina", "Asistencias de Estudiantes");
        model.addAttribute("tituloVista", "Listado Completo de Asistencias");
        model.addAttribute("listaAsistencias", asistenciaSvc.obtenerTodasLasAsistencias());
        
        // 3. Especificar el fragmento de contenido a usar
        model.addAttribute("contenidoFragmento", "asistencia/lista");
        
        return "plantilla-listados";
    }
    
    @GetMapping(urlObtenerAsistenciasPorEstudiante)
    public String obtenerAsistenciasPorEstudiante(Model model, @PathVariable Long idEstudiante) {
        Estudiante estudiante = estudianteSvc.obtenerEstudiantePorId(idEstudiante);

        model.addAttribute("tituloPagina", "Asistencias de Estudiantes");
        model.addAttribute("tituloVista", "Listado Completo de Asistencias");
        model.addAttribute("estudiante", estudiante.getNombre() + " " + estudiante.getApellido());
        model.addAttribute("grado", estudiante.getGrado().getNombre().getLeyendaUI());
        model.addAttribute("turno", estudiante.getGrado().getTurno().getLeyendaUI());
        model.addAttribute("listaAsistencias", asistenciaSvc.obtenerAsistenciasPorEstudiante(idEstudiante));
        
        // 3. Especificar el fragmento de contenido a usar
        model.addAttribute("contenidoFragmento", "asistencia/lista-estudiante");
        return "plantilla-listados";
    }
}
