package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services.IAutoService;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services.IMotorService;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutoController {
    
    @Autowired
    private IAutoService autoService;
    
    @Autowired
    private IMotorService motorService;

    @GetMapping
    public List<Auto> getAllAutos() {
        return autoService.obtenerAutos();
    }

    @GetMapping("/{id}")
    public Auto getAutoById(@PathVariable Long id) {
        return autoService.traerAuto(id);
    }

    @PostMapping
    public Auto createAuto(@RequestBody Auto auto) {
        // Si el auto tiene un motor con solo ID, buscar el motor existente
        if (auto.getMotor() != null && auto.getMotor().getIdMotor() != null && 
            auto.getMotor().getCilindrada() == null) {
            
            // Buscar motor existente en BD
            Motor motorExistente = motorService.traerMotor(auto.getMotor().getIdMotor());
            if (motorExistente != null) {
                auto.setMotor(motorExistente);
            } else {
                throw new RuntimeException("Motor con ID " + auto.getMotor().getIdMotor() + " no encontrado");
            }
        }
        
        return autoService.guardarAuto(auto);
    }

    @PutMapping("/{id}")
    public Auto updateAuto(@PathVariable Long id, @RequestBody Auto auto) {
        return autoService.editarAuto(id, auto.getMarca(), auto.getModelo(), auto.getPrecio(), auto.getMotor());
    }

    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable Long id) {
        autoService.eliminarAuto(id);
    }
    
    @GetMapping("/test-eager/{id}")
    public Auto testEagerFetch(@PathVariable Long id) {
        return autoService.traerAuto(id);
    }
    
    @GetMapping("/por-marca-motor")
    public List<Auto> getAutosByMarcaMotor(@RequestParam String marcaMotor) {
        return autoService.buscarPorMarcaMotor(marcaMotor);
    }
    
    @GetMapping("/por-cilindrada") 
    public List<Auto> getAutosByCilindrada(@RequestParam Integer cilindrada) {
        return autoService.buscarPorCilindrada(cilindrada);
    }
}

