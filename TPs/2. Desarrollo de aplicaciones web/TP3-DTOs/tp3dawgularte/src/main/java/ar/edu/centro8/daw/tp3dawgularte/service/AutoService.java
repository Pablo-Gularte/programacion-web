package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp3dawgularte.model.Auto;
import ar.edu.centro8.daw.tp3dawgularte.repository.IAutoRepository;

@Service
public class AutoService implements IAutoService {

    @Autowired
    private IAutoRepository autoRepo;

    @Override
    public List<Auto> getAllAutos() {
        return autoRepo.findAll();
    }

    @Override
    public Auto getAutoById(Long id) {
        return autoRepo.findById(id).orElse(null);
    }

    @Override
    public void saveAuto(Auto autoNuevo) {
        autoRepo.save(autoNuevo);
    }

    @Override
    public void editAuto(Long id, String nuevaMarca, double nuevoPrecio) {
        Auto autoModificado = getAutoById(id);
        autoModificado.setMarca(nuevaMarca);
        autoModificado.setPrecio(nuevoPrecio);
        saveAuto(autoModificado);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepo.deleteById(id);
    }

}
