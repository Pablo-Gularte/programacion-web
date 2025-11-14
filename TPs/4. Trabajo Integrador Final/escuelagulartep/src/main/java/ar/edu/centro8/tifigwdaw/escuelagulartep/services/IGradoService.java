package ar.edu.centro8.tifigwdaw.escuelagulartep.services;

import java.util.List;

import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoRequestDTO;
import ar.edu.centro8.tifigwdaw.escuelagulartep.dto.GradoResponseDTO;

public interface IGradoService {
    public List<GradoResponseDTO> obtenerTodosLosGrados();

    public GradoResponseDTO obtenerGradoPorId(Long id);

    public GradoResponseDTO crearGrado(GradoRequestDTO gradoDTO);

    public GradoResponseDTO modificarGrado(Long id, GradoRequestDTO gradoDTO);

    public void eliminarGrado(Long id);
}
