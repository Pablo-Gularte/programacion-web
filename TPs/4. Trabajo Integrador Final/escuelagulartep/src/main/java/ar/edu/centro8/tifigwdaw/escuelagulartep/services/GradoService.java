package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoMapper;
import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoRequestDTO;
import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoResponseDTO;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.repositories.IGradoRepository;

@Service
public class GradoService implements IGradoService {

    @Autowired IGradoRepository gradoRepo;

    @Override
    public List<GradoResponseDTO> obtenerTodosLosGrados() {
        List<Grado> grados = gradoRepo.findAll();
        return grados.stream()
            .map(GradoMapper::toResponseDTO)
            .toList();
    }

    @Override
    public GradoResponseDTO obtenerGradoPorId(Long id) {
        Optional<Grado> grado = gradoRepo.findById(id);
        if(grado.isPresent()) {
            return GradoMapper.toResponseDTO(grado.get());
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    @Override
    public GradoResponseDTO crearGrado(GradoRequestDTO gradoDTO) {
        Grado nuevoGrado = GradoMapper.toEntity(gradoDTO);
        // Si el grado contiene listado de estudiantes, establecer la relación bidireccional
        if (nuevoGrado.getEstudiantes() != null) {
            for (var estudiante : nuevoGrado.getEstudiantes()) {
                estudiante.setGrado(nuevoGrado);
            }
        }
        nuevoGrado = gradoRepo.save(nuevoGrado);
        return GradoMapper.toResponseDTO(nuevoGrado);
    }

    @Override
    public GradoResponseDTO modificarGrado(Long id, GradoRequestDTO gradoDTO) {
        Optional<Grado> gradoOpt = gradoRepo.findById(id);
        if(gradoOpt.isPresent()) {
            Grado gradoExistente = gradoOpt.get();
            GradoMapper.updateEntity(gradoExistente, gradoDTO);
            // Si el grado contiene listado de estudiantes, establecer la relación bidireccional
            if (gradoExistente.getEstudiantes() != null) {
                for (var estudiante : gradoExistente.getEstudiantes()) {
                    estudiante.setGrado(gradoExistente);
                }
            }
            gradoExistente = gradoRepo.save(gradoExistente);
            return GradoMapper.toResponseDTO(gradoExistente);
        }
        throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
    }

    @Override
    public void eliminarGrado(Long id) {
        if(gradoRepo.existsById(id)) {
            gradoRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró ningún grado con ID " + id);
        }
    }
}
