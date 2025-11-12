package ar.edu.centro8.desarrollo.proyectojpa1an.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyectojpa1an.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1an.repositories.AutoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AutoService {
    @Autowired
    private AutoRepository autoRepository;

    
    public List<Auto> obtenerAutos() {
        return  autoRepository.findAll();
    }

    public Auto guardarAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public boolean eliminarAuto(Long id) {
        try {
            autoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Auto traerAuto(Long id) {
        return autoRepository.findById(id).orElse(null);
    }

    public Auto editarAuto(Long idOriginal, Long idNueva, String nuevaMarca, Double nuevoPrecio) {
        Auto auto = traerAuto(idOriginal);
        if (auto != null) {
            // ❌ NO cambiar el ID - causa el error de Hibernate
            // auto.setId(idNueva);  ← Esta línea causa el problema
            
            // ✅ Solo actualizar campos modificables
            if (nuevaMarca != null) {
                auto.setMarca(nuevaMarca);
            }
            if (nuevoPrecio != null) {
                auto.setPrecio(nuevoPrecio);
            }
            return guardarAuto(auto);
        }
        return null;
    }
}
