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

    @GetMapping("/")
    /**
     * Método para listar los datos en la tabla de presentación de la página principal
     * 
     * @param modelo Objeto utilizado para enviar atributos a la plantilla Thymeleaf
     * @return Página principal (index.html)
     */
    public String listarAutos(Model modelo) {
        cargarAtributosListado(modelo);
        return "index";
    }
    
    @GetMapping("/autos/editar/{id}")
    public String listarAutoPorId(@PathVariable long id, Model modelo) {
        try {
            AutoResponseDTO autoGuardado = autoSvc.findAuto(id);
            modelo.addAttribute("auto", autoGuardado);
            cargarAtributosListado(modelo);
            return "index";
        } catch (IllegalArgumentException e) {
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            cargarAtributosListado(modelo);
            // Vuelve a la vista 'index'
            return "index";
        } catch (Exception e) {
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            cargarAtributosListado(modelo);
            // Vuelve a la vista 'index'
            return "index";
        }
    }
    
    @GetMapping("/autos/nuevo")
    public String pantallaNuevoAuto(Model modelo) {
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
        modelo.addAttribute("idAuto", 0);
        return "index";
    }

    @PostMapping("/autos/guardar/")
    public String guardarAuto(@ModelAttribute AutoRequestDTO autoDTO, Model modelo) {
        try {
            AutoResponseDTO autoNuevo = autoSvc.saveAuto(autoDTO);
            modelo.addAttribute("tipoMensaje", "ok");
            modelo.addAttribute("mensaje", "Se ha creado correctamente el nuevo auto con el ID = " + autoNuevo.getId());
            cargarAtributosListado(modelo);
            return "index";
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación (e.g., marca vacía o duplicada)
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            // Vuelve a la vista 'index'
            return "index";
        } catch (Exception e) {
            // MAnejo de errores inesperados
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            return "index";
        }

    }

    @PostMapping("/autos/editar/{id}")
    public String guardarAuto(@PathVariable Long id, @ModelAttribute AutoRequestDTO autoDTO, Model modelo, RedirectAttributes mensajeRedireccionado ) {
        try {
            autoSvc.editAuto(id, autoDTO);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha modificado correctamente el nuevo auto con el ID = " + id);
            cargarAtributosListado(modelo);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación (e.g., marca vacía o duplicada)
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error de argumento: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            cargarAtributosListado(modelo);
            // Vuelve a la vista 'index'
            return "index";
        } catch (Exception e) {
            // MAnejo de errores inesperados
            modelo.addAttribute("tipoMensaje", "error");
            modelo.addAttribute("mensaje", "Error inesperado: " + e.getMessage());
            // Mantener la lista y el DTO para que el usuario pueda corregir
            cargarAtributosListado(modelo);
            return "index";
        }
    }
    
    @PostMapping("/autos/borrar/{id}")
    public String borrarAuto(@PathVariable long id, Model modelo, RedirectAttributes mensajeRedireccionado) {
        try {
            autoSvc.deleteAuto(id);
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "ok");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Se ha eliminado correctamente el auto de ID " + id);
            cargarAtributosListado(modelo);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error de argumento - " + e.getMessage());
            cargarAtributosListado(modelo);
            return "redirect:/";
        } catch (Exception e) {
            mensajeRedireccionado.addFlashAttribute("tipoMensaje", "error");
            mensajeRedireccionado.addFlashAttribute("mensaje", "Error inesperado - " + e.getMessage());
            cargarAtributosListado(modelo);
            return "redirect:/";
        }
    }

    private void cargarAtributosListado(Model modelo) {
        List<AutoResponseDTO> autos = autoSvc.getAutos();
        modelo.addAttribute("autos", autos);
        modelo.addAttribute("totalRegistros", autos.size());
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
    }
    
    @GetMapping("/prueba")
    public String pruebaFragmentos(Model modelo) {
        List<AutoResponseDTO> autos = autoSvc.getAutos();
        modelo.addAttribute("autos", autos);
        modelo.addAttribute("operacion", "listar");
        return "prueba";
    }
    
    @GetMapping("/prueba/crear")
    public String pruebaCrear(Model modelo) {
        modelo.addAttribute("autoDTO", new AutoRequestDTO());
        modelo.addAttribute("operacion", "crear");
        return "prueba";
    }

    @GetMapping("/prueba/editar/{id}")
    public String pruebaEditar(@PathVariable long id, Model modelo) {
        AutoResponseDTO autoDTO = autoSvc.findAuto(id);
        modelo.addAttribute("id", id);
        modelo.addAttribute("operacion", "editar");
        modelo.addAttribute("autoDTO", autoDTO);
        return "prueba";
    }
    
}
