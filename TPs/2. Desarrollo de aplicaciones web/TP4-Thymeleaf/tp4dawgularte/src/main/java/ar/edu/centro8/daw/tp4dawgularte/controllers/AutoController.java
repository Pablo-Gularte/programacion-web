package ar.edu.centro8.daw.tp4dawgularte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.services.IAutoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class AutoController {

    @Autowired
    private IAutoService autoSvc;

    @GetMapping("/")
    public String listarAutos(Model modelo) {
        List<AutoResponseDTO> autos = autoSvc.getAutos();
        modelo.addAttribute("autos", autos);
        modelo.addAttribute("totalRegistros", autos.size());
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
        return "index";
    }
    
    @PostMapping("/autos/guardar")
    public String guardarAuto(@ModelAttribute AutoRequestDTO autoDTO, Model modelo, RedirectAttributes mensajeRedireccionado ) {
        try {
            autoSvc.saveAuto(autoDTO);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha creado correctamente el nuevo auto.");
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación (e.g., marca vacía o duplicada)
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            modelo.addAttribute("autos", autoSvc.getAutos());
            modelo.addAttribute("totalRegistros", autoSvc.getAutos().size());
            modelo.addAttribute("autoDTO", autoDTO); 
            // Vuelve a la vista 'index'
            return "index";
        } catch (Exception e) {
            // MAnejo de errores inesperados
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            modelo.addAttribute("autos", autoSvc.getAutos());
            modelo.addAttribute("totalRegistros", autoSvc.getAutos().size());
            modelo.addAttribute("autoDTO", autoDTO); 
            return "index";
        }
    }
    
    @DeleteMapping("/autos/borrar/{id}")
    public String borrarAuto(@PathVariable long id, Model modelo, RedirectAttributes mensajeRedireccionado) {
        try {
            autoSvc.deleteAuto(id);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha eliminado correctamente el auto de ID " + id);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error de argumemto al intentar eliminar el auto de ID " + id);
            return "index";
        } catch (Exception e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error inesperado al intentar eliminar el auto de ID " + id);
            return "index";
        }
    }
}
