package ar.edu.centro8.daw.tp4dawgularte.services;

import java.util.List;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;

public interface IAutoService {
    public List<AutoResponseDTO> getAutos();

    //alta
    public AutoResponseDTO saveAuto(AutoRequestDTO autoDto);

    //baja
    public void deleteAuto(Long id);

    //lectura de un solo objeto
    public AutoResponseDTO findAuto(Long id);

    //edición/modificación
    public AutoResponseDTO editAuto(Long id, AutoRequestDTO autoDto);

}
