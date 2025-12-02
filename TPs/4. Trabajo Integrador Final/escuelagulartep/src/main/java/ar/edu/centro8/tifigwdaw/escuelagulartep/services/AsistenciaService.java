package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Asistencia;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IAsistenciaRepository;
import jakarta.transaction.Transactional;

@Service
public class AsistenciaService {

    @Autowired
    IAsistenciaRepository asistenciaRepo;

    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepo.findAll();
    }

    public List<Asistencia> obtenerAsistenciasPorEstudiante(Long idEstudiante) {
        return asistenciaRepo.findByEstudianteId(idEstudiante);
    }

    @Transactional
    public void eliminarAsistenciaPorEstudiante(Long idEstudiante) {
        asistenciaRepo.deleteByEstudianteId(idEstudiante);
    }
}
