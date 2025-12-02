package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.excepciones.RecursoDuplicadoExcepcion;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IEstudianteRepository;
import jakarta.transaction.Transactional;

@Service
public class EstudianteService {

    @Autowired IEstudianteRepository estudianteRepo;
    @Autowired BoletinService boletinSvc;
    @Autowired AsistenciaService asistenciaSvc;

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepo.findAll();
    }

    public List<Estudiante> obtenerEstudiantesPorGrado(Long idGrado) {
        return estudianteRepo.findByGradoId(idGrado);
    }

    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró ningún estudiante con ID " + id));
    }

    public List<Estudiante> obtenerEstudiantesSinGrado() {
        return estudianteRepo.findByGradoIsNull();
    }

    public Estudiante crearEstudiante(Estudiante nuevoEstudiante) {
        validarEstudiante(nuevoEstudiante);
        
        return estudianteRepo.save(nuevoEstudiante);
    }

    public Estudiante modificarEstudiante(Long id, Estudiante estudiante) {
        Estudiante estudianteExistente = obtenerEstudiantePorId(id);
        
        // Actualizo los campos del estudiante existente con los valores del estudiante proporcionado
        if (estudiante.getNombre() != null) estudianteExistente.setNombre(estudiante.getNombre());
        if (estudiante.getApellido() != null) estudianteExistente.setApellido(estudiante.getApellido());
        if (estudiante.getEdad() >= 5 && estudiante.getEdad() <= 14) estudianteExistente.setEdad(estudiante.getEdad());
        if (estudiante.getDireccion() != null) estudianteExistente.setDireccion(estudiante.getDireccion());
        if (estudiante.getNombreMadre() != null) estudianteExistente.setNombreMadre(estudiante.getNombreMadre());
        if (estudiante.getNombrePadre() != null) estudianteExistente.setNombrePadre(estudiante.getNombrePadre());
        if (estudiante.getHnoEnEscuela() != null) estudianteExistente.setHnoEnEscuela(estudiante.getHnoEnEscuela());
        if (estudiante.getRegular() != null) estudianteExistente.setRegular(estudiante.getRegular());
        if (estudiante.getGrado() != null) estudianteExistente.setGrado(estudiante.getGrado());
        
        return estudianteRepo.save(estudianteExistente);
    }

    @Transactional
    public void eliminarEstudiante(Long id) {
        Estudiante estudianteExistente = obtenerEstudiantePorId(id);
        boletinSvc.eliminarBoletinPorEstudiante(id);
        asistenciaSvc.eliminarAsistenciaPorEstudiante(id);
        estudianteRepo.delete(estudianteExistente);
    }
    
    /**
     * Valida las reglas de negocio para un estudiante antes de su creación.
     * 
     * @param estudiante El estudiante a validar.
     */
    private void validarEstudiante(Estudiante estudiante) {
        // Verifico si el DNI ya existe en la base de datos
        if (estudianteRepo.existsByDni(estudiante.getDni())) {
            throw new RecursoDuplicadoExcepcion("El DNI " + estudiante.getDni() + " ya está registrado.");
        }
    }


}
