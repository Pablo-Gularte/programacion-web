package ar.edu.centro8.daw.tp4dawgularte.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoMapper;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.models.Auto;
import ar.edu.centro8.daw.tp4dawgularte.repository.IAutoRepository;

@Service
public class AutoService implements IAutoService {
    @Autowired
    private IAutoRepository autoRepo;

    @Override
    public List<AutoResponseDTO> getAutos() {
        List<Auto> autos = autoRepo.findAll();
        return autos.stream()
            .map(AutoMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public AutoResponseDTO saveAuto(AutoRequestDTO autoDto) {
        autoDto.validarDTO();
        
        validarMarcaUnica(autoDto.getMarca());

        Auto auto = AutoMapper.toEntity(autoDto);
        Auto autoGuardado = autoRepo.save(auto);

        return AutoMapper.toResponseDTO(autoGuardado);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepo.deleteById(id);
    }

    @Override
    public AutoResponseDTO findAuto(Long id) {
        Optional<Auto> auto = autoRepo.findById(id);
        if (!auto.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún auto con id = " + id);
        }
        return AutoMapper.toResponseDTO(auto.get());
    }

    @Override
    public AutoResponseDTO editAuto(Long id, AutoRequestDTO autoDto) {
        autoDto.validarDTO();

        Optional<Auto> autoExistente = autoRepo.findById(id);
        if (!autoExistente.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún auto con id = " + id);
        }

        Auto auto = autoExistente.get();
        if (!auto.getMarca().equals(autoDto.getMarca())) {
            validarMarcaUnica(autoDto.getMarca());
        }

        AutoMapper.updateEntity(auto, autoDto);

        return AutoMapper.toResponseDTO(autoRepo.save(auto));
    }

    private void validarMarcaUnica(String marca) {
        Optional<Auto> autoExistente = autoRepo.findByMarca(marca);
        if (autoExistente.isPresent()) {
            throw new IllegalArgumentException("ya existe la marca: " + marca);
        }

    }
}
