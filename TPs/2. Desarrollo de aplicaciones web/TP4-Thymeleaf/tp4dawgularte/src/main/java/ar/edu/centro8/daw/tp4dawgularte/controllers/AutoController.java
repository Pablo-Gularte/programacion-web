package ar.edu.centro8.daw.tp4dawgularte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.services.IAutoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AutoController {

    @Autowired
    private IAutoService autoSvc;

    // *********************************************
    // Métodos para cargar información en plantillas
    // *********************************************
    @GetMapping("/")
    public String tablaAutos(Model modelo) {
        modelo.addAttribute("operacion", "listar"); // Atributo para insertar fragmento
        List<AutoResponseDTO> autos = autoSvc.getAutos();
        modelo.addAttribute("autos", autos);
        modelo.addAttribute("totalRegistros", autos.size());
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
        return "index";
    }
    
    @GetMapping("/autos/nuevo")
    public String formularioCrearAuto(Model modelo) {
        modelo.addAttribute("operacion", "crear");  // Atributo para insertar fragmento
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
        return "index";
    }

    @GetMapping("/autos/editar/{id}")
    public String formularioEditarAuto(@PathVariable long id, Model modelo, RedirectAttributes raMensaje) {
        try {
            AutoResponseDTO autoGuardado = autoSvc.findAuto(id);
            modelo.addAttribute("operacion", "editar");
            modelo.addAttribute("id", id);
            modelo.addAttribute("autoDTO", autoGuardado);
            return "index";
        } catch (IllegalArgumentException e) {
            raMensaje.addFlashAttribute("tipoMensaje", "error");
            raMensaje.addFlashAttribute("mensaje", "Error de argumento: " + e.getMessage());
            return "redirect:/";
        } catch (Exception e) {
            raMensaje.addFlashAttribute("tipoMensaje", "error");
            raMensaje.addFlashAttribute("mensaje", "Error inesperado: " + e.getMessage());
            return "redirect:/";
        }
    }
    

    // **********************************
    // Métodos para manipular información
    // **********************************
    @PostMapping("/autos/guardar")
    public String guardarAutoNuevo(@ModelAttribute AutoRequestDTO autoDTO, Model modelo, RedirectAttributes mensajeRedireccionado) {
        try {
            AutoResponseDTO autoNuevo = autoSvc.saveAuto(autoDTO);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha creado correctamente el nuevo auto con el ID = " + autoNuevo.getId());
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación (e.g., marca vacía o duplicada)
            modelo.addAttribute("operacion", "crear");
            modelo.addAttribute("autoDTO", autoDTO);
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            return "index";
        } catch (Exception e) {
            // Manejo de errores inesperados
            modelo.addAttribute("operacion", "crear");
            modelo.addAttribute("autoDTO", autoDTO);
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            return "index";
        }

    }

    @PostMapping("/autos/guardar/{id}")
    public String guardarAutoEditado(@PathVariable Long id, @ModelAttribute AutoRequestDTO autoDTO, Model modelo, RedirectAttributes mensajeRedireccionado ) {
        try {
            autoSvc.editAuto(id, autoDTO);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha modificado correctamente el nuevo auto con el ID = " + id);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación (e.g., marca vacía o duplicada)
            modelo.addAttribute("operacion", "editar");
            modelo.addAttribute("autoDTO", autoDTO);
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            return "index";
        } catch (Exception e) {
            // Manejo de errores inesperados
            modelo.addAttribute("operacion", "editar");
            modelo.addAttribute("autoDTO", autoDTO);
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            return "index";
        }
    }
    
    @PostMapping("/autos/borrar/{id}")
    public String borrarAuto(@PathVariable long id, RedirectAttributes mensajeRedireccionado) {
        try {
            autoSvc.deleteAuto(id);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha eliminado correctamente el auto de ID " + id);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error de argumento - " + e.getMessage());
            return "redirect:/";
        } catch (Exception e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error inesperado - " + e.getMessage());
            return "redirect:/";
        }
    }
}
