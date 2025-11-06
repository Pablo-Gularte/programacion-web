package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.repositories.IMotorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MotorService implements IMotorService {
    @Autowired
    private IMotorRepository motorRepo;
    
    @Override
    public List<Motor> obtenerMotores() {
        return motorRepo.findAll();
    }

    
    @Override
    public Motor guardarMotor(Motor motor) {
        return motorRepo.save(motor);
    }

    @Override
    public boolean eliminarMotor(Long idMotor) {
        try {
            motorRepo.deleteById(idMotor);
            return true; // El motor se eliminó con éxito
        } catch (Exception e) {
            return false; // No se pudo eliminar el motor
        }
    }

    @Override
    public Motor traerMotor(Long idMotor) {
        return motorRepo.findById(idMotor).orElse(null);
    }

    @Override
    public Motor editarMotor(Long idOriginal, Integer nuevaCilindrada, String nuevaMarca) {
        // Busco el objeto original
        Motor motor = this.traerMotor(idOriginal);
        
        if (motor != null) {
            // Proceso de modificación a nivel lógico (NO cambiar el ID)
            motor.setCilindrada(nuevaCilindrada);
            motor.setMarcaMotor(nuevaMarca);
            
            // Guardar los cambios
            this.guardarMotor(motor);
        }

        return motor;
    }
}
