package ar.edu.centro8.desarrollo.proyectojpa1an.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyectojpa1an.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1an.models.Concesionaria;
import ar.edu.centro8.desarrollo.proyectojpa1an.repositories.AutoRepository;
import ar.edu.centro8.desarrollo.proyectojpa1an.repositories.ConcesionariaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConcesionariaService {
    @Autowired
    private ConcesionariaRepository concesionariaRepository;

    @Autowired
    private AutoRepository autoRepository;

    
    public List<Concesionaria> obtenerConcesionarias() {
        return concesionariaRepository.findAll();
    }

    public Concesionaria guardarConcesionaria(Concesionaria concesionaria) {
        return concesionariaRepository.save(concesionaria);
    }

    public boolean eliminarConcesionaria(Long id) {
        try {
            concesionariaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Concesionaria traerConcesionaria(Long id) {
        return concesionariaRepository.findById(id).orElse(null);
    }

    public Concesionaria editarConcesionaria(Long idOriginal, Long idNueva, String nuevoNombre) {
        Concesionaria concesionaria = traerConcesionaria(idOriginal);
        if (concesionaria != null) {
            concesionaria.setId(idNueva);
            concesionaria.setNombre(nuevoNombre);
            return guardarConcesionaria(concesionaria);
        }
        return null;
    }

    public Auto guardarAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    
}
