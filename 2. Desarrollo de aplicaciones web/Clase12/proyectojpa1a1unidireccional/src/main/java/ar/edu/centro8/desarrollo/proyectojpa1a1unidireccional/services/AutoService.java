package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.repositories.IAutoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class AutoService implements IAutoService {

    @Autowired
    private IAutoRepository autoRepo;
    
    @Override
    public List<Auto> obtenerAutos() {
        List<Auto> listaPersonas = autoRepo.findAll();
        return listaPersonas;
    }

    
    @Override
    public Auto guardarAuto(Auto auto) {
        autoRepo.save(auto);
        return auto;
    }

    @Override
    public boolean eliminarAuto(Long id) {
        try {
            autoRepo.deleteById(id);
            return true; // El auto se eliminó con éxito
        } catch (Exception e) {
            return false; // No se pudo eliminar el auto
        }
    }

    @Override
    public Auto traerAuto(Long id) {
        Auto perso = autoRepo.findById(id).orElse(null);
        return perso;
    }

    @Override
    public Auto editarAuto(Long idOriginal, String nuevaMarca, String nuevoModelo, BigDecimal nuevoPrecio, Motor nuevoMotor) {
        // Busco el objeto original
        Auto auto = this.traerAuto(idOriginal);
        
        if (auto != null) {
            // Proceso de modificación a nivel lógico (NO cambiar el ID)
            auto.setMarca(nuevaMarca);
            auto.setModelo(nuevoModelo);
            auto.setPrecio(nuevoPrecio);
            auto.setMotor(nuevoMotor);
            
            // Guardar los cambios
            this.guardarAuto(auto);
        }

        return auto;
    }
    
    @Override
    public List<Auto> buscarPorMarcaMotor(String marcaMotor) {
        return autoRepo.findByMarcaMotor(marcaMotor);
    }
    
    @Override
    public List<Auto> buscarPorCilindrada(Integer cilindrada) {
        return autoRepo.findByCilindrada(cilindrada);
    }
    
}

