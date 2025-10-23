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
    public void editAuto(Long id, Auto autoModificado) {
        Auto auto = getAutoById(id);
        auto.setMarca(autoModificado.getMarca());
        auto.setModelo(autoModificado.getModelo());
        auto.setColor(autoModificado.getColor());
        auto.setPrecio(autoModificado.getPrecio());
        auto.setPatente(autoModificado.getPatente());
        auto.setNroChasis(autoModificado.getNroChasis());
        auto.setNroMotor(autoModificado.getNroMotor());
        saveAuto(auto);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepo.deleteById(id);
    }

}
