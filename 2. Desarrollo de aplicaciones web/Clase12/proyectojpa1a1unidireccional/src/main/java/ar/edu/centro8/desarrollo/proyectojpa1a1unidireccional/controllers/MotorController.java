package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import  ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;
import  ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services.MotorService;

import java.util.List;

@RestController
@RequestMapping("/api/motores")
public class MotorController {
    @Autowired
    private MotorService motorService;

    @GetMapping
    public List<Motor> getAllMotors() {
        List<Motor> motores = motorService.obtenerMotores();
        return motores;
    }

    @GetMapping("/{id}")
    public Motor getMotorById(@PathVariable Long id) {
        return motorService.traerMotor(id);
    }

    @PostMapping
    public void createMotor(@RequestBody Motor motor) {
        motorService.guardarMotor(motor);
    }

    @PutMapping("/{id}")
    public void updateMotor(@PathVariable Long id, @RequestBody Motor motor) {
         motorService.editarMotor(id, motor.getCilindrada(), motor.getMarcaMotor());
    }

    @DeleteMapping("/{id}")
    public void deleteMotor(@PathVariable Long id) {
        motorService.eliminarMotor(id);
    }
}
