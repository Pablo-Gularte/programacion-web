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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearGrado'");
    }

    @Override
    public GradoResponseDTO modificarGrado(Long id, GradoRequestDTO gradoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarGrado'");
    }

    @Override
    public void eliminarGrado(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarGrado'");
    }
}
