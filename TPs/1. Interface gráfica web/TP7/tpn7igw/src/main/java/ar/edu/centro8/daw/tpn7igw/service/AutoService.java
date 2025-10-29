package ar.edu.centro8.daw.tpn7igw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tpn7igw.model.Auto;
import ar.edu.centro8.daw.tpn7igw.repository.IAutoRepository;


@Service
public class AutoService implements IAutoService {
    @Autowired
    private IAutoRepository autoRepo;

    @Override
    public List<Auto> getAutos() {
        return autoRepo.findAll();
    }

    @Override
    public void saveAuto(Auto auto) {
        autoRepo.save(auto);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepo.deleteById(id);
    }

    @Override
    public Auto findAuto(Long id) {
        Auto auto = autoRepo.findById(id).orElse(null);
        if (auto == null) {
            throw new IllegalArgumentException("No se encontró ningún auto con id = " + id);
        }
        return auto;
    }

    @Override
    public void editAuto(Long id, Auto nuevoAuto) {
        Auto auto = this.findAuto(id);
        auto.setMarca(nuevoAuto.getMarca());
        auto.setPrecio(nuevoAuto.getPrecio());
        this.saveAuto(auto);
    }
}
