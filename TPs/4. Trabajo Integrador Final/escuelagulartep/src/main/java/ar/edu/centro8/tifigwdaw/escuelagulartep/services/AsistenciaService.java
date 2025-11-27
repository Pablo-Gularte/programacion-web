package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Asistencia;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IAsistenciaRepository;

@Service
public class AsistenciaService {

    @Autowired
    IAsistenciaRepository asistenciaRepo;

    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepo.findAll();
    }

    public List<Asistencia> obtenerAsistenciasPorEstudiante(Long idestudiante) {
        return asistenciaRepo.findByEstudianteId(idestudiante);
    }

    public Asistencia crearAsistencia(Asistencia nuevaAsistencia) {
        return asistenciaRepo.save(nuevaAsistencia);
    }

    public Asistencia modificarAsistencia(Long id, Asistencia asistencia) {
        Optional<Asistencia> asistenciaGuardada = asistenciaRepo.findById(id);
        if (asistenciaGuardada.get() == null) {
            throw new IllegalArgumentException("No se encontró la asistencia de ID: " + id);
        } 

        Asistencia asistenciaModificada = asistenciaGuardada.get();
        if (asistencia.getFecha() != null) asistenciaModificada.setFecha(asistencia.getFecha());
        if (asistencia.getEstudiante() != null) asistenciaModificada.setEstudiante(asistencia.getEstudiante());
        if (asistencia.getTipoAsistencia() != null) asistenciaModificada.setTipoAsistencia(asistencia.getTipoAsistencia());
        
        return asistenciaRepo.save(asistenciaModificada);
    }

    public void borrarAsistencia(Long id) {
        if (asistenciaRepo.existsById(id)) {
            asistenciaRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró la asistencia de ID: " + id);
        }
    }
}
