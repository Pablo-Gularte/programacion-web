package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp3dawgularte.dto.AutoMapper;
import ar.edu.centro8.daw.tp3dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp3dawgularte.dto.AutoResponsetDTO;
import ar.edu.centro8.daw.tp3dawgularte.model.Auto;
import ar.edu.centro8.daw.tp3dawgularte.repository.IAutoRepository;

@Service
public class AutoService implements IAutoService {

    @Autowired
    private IAutoRepository autoRepo;

    @Override
    public List<AutoResponsetDTO> getAllAutos() {
        List<Auto> listaAutos = autoRepo.findAll();
        return listaAutos.stream()
            .map(AutoMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public AutoResponsetDTO getAutoById(Long id) {
        Optional<Auto> auto = autoRepo.findById(id);
        if(auto.isPresent()) {
            return AutoMapper.toResponseDto(auto.get()); 
        }
        throw new IllegalArgumentException("No se encontró nungún auto con id = " + id);
    }

    @Override
    public AutoResponsetDTO saveAuto(AutoRequestDTO autoNuevo) {
        autoNuevo.validar();

        /**
         * REGLA DE NEGOCIO
         * No se permiten patentes duplicadas
         */
        validarPatenteUnica(autoNuevo.getPatente());

        // Persisto en base de datos el nuevo auto validado, lo convierto a DTO
        // y lo devuelvo como respuesta
        Auto auto = AutoMapper.toEntity(autoNuevo);
        Auto autoGuardado = autoRepo.save(auto);
        return AutoMapper.toResponseDto(autoGuardado);
    }

    @Override
    public AutoResponsetDTO editAuto(Long id, AutoRequestDTO autoModificadoDto) {
        // Aplico validaciones al DTO entrante
        autoModificadoDto.validar();
        
        // Controlo que el id recibido corresponda con el de un auto que exista en la base de datos
        Optional<Auto> autoExistente = autoRepo.findById(id);
        if(!autoExistente.isPresent()){
            throw new IllegalArgumentException("No se encontró ningún auto con id = " + id);
        }

        // Recupero el auto de la base de datos asociado al ID y controlo situación de patente
        Auto auto = autoExistente.get();
        if(!auto.getPatente().equals(autoModificadoDto.getPatente())) {
            validarPatenteUnica(autoModificadoDto.getPatente());
        }

        // Luego de validar actualizo la entidad a partir del DTO y la persisto en la BD
        AutoMapper.updateEntity(auto, autoModificadoDto);
        Auto autoModificado = autoRepo.save(auto);
        return AutoMapper.toResponseDto(autoModificado);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepo.deleteById(id);
    }

    private void validarPatenteUnica(String patente) {
        Optional<Auto> autoExistente = autoRepo.findByPatente(patente);
        if(autoExistente.isPresent()) {
            throw new IllegalArgumentException("La patente " + patente + " ya se encuentra en uso");
        }
    }
}
