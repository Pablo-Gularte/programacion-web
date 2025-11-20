package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;

@Service
public class GradoService {

    @Autowired IGradoRepository gradoRepo;

    public List<Grado> obtenerTodosLosGrados() {
        return gradoRepo.findAll();
    }

    public Grado obtenerGradoPorId(Long id) {
        Optional<Grado> grado = gradoRepo.findById(id);
        if(grado.isPresent()) {
            return grado.get();
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public Grado crearGrado(Grado nuevoGrado) {
        return gradoRepo.save(nuevoGrado);
    }

    public Grado modificarGrado(Long id, Grado gradoDTO) {
        Optional<Grado> gradoEnBD = gradoRepo.findById(id);
        if(gradoEnBD.isPresent()) {
            return gradoRepo.save(gradoEnBD.get());
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    public void eliminarGrado(Long id) {
        if(gradoRepo.existsById(id)) {
            gradoRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
        }
    }
}
