package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Boletin;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IBoletinRepository;
import jakarta.transaction.Transactional;

@Service
public class BoletinService {

    @Autowired
    IBoletinRepository boletinRepo;

    public List<Boletin> obtenerTodosLosBoletines() {
        return boletinRepo.findAll();
    }

    public List<Boletin> obtenerBoletinPorEstudiante(Long idEstudiante) {
        return boletinRepo.findByEstudianteId(idEstudiante);
    }

    @Transactional
    public void eliminarBoletinPorEstudiante(Long idEstudiante) {
        boletinRepo.deleteByEstudianteId(idEstudiante);
    }
}
