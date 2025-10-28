package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;

import ar.edu.centro8.daw.tp3dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp3dawgularte.dto.AutoResponsetDTO;

public interface IAutoService {
    // Listar todos los autos
    public List<AutoResponsetDTO> getAllAutos();

    // Listar un auto por ID
    public AutoResponsetDTO getAutoById(Long id);

    // Guardar un auto
    public AutoResponsetDTO saveAuto(AutoRequestDTO autoNuevo);

    // Modificar un auto vía ID
    public AutoResponsetDTO editAuto(Long id, AutoRequestDTO autoModificado);

    // Eliminar un auto vía ID
    public void deleteAuto(Long id);
}
