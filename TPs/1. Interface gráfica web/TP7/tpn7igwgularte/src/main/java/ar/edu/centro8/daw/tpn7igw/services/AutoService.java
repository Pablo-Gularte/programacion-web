package ar.edu.centro8.daw.tpn7igw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tpn7igw.models.Auto;
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
        return autoRepo.findById(id).orElse(null);
    }

    @Override
    public void editAuto(Long id, String nuevaMarca, double nuevoPrecio) {
        Auto auto = this.findAuto(id);
        auto.setMarca(nuevaMarca);
        auto.setPrecio(nuevoPrecio);
        this.saveAuto(auto);
    }
}
