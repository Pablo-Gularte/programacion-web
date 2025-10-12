package ar.edu.centro8.desarrollo.proyectojpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaMapper;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaRequestDTO;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaResponseDTO;
import ar.edu.centro8.desarrollo.proyectojpa.model.Persona;
import ar.edu.centro8.desarrollo.proyectojpa.repository.IPersonaRepository;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersonaRepository persoRepo;
    
    @Override
    public List<PersonaResponseDTO> getPersonas() {
        List<Persona> listaPersonas = persoRepo.findAll();
        return listaPersonas.stream()
                .map(PersonaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonaResponseDTO savePersona(PersonaRequestDTO personaDTO) {
        // Validar DTO
        personaDTO.validar();
        
        /**
         * REGLA DE NEGOCIO EN SERVICIO:
         * No permitir personas duplicadas con el mismo nombre
         * Esta es lógica de negocio compleja que coordina múltiples elementos
         */
        validarNombreUnico(personaDTO.getNombre());
        
        // Convertir DTO a entidad
        Persona persona = PersonaMapper.toEntity(personaDTO);
        
        // Guardar en base de datos
        Persona personaGuardada = persoRepo.save(persona);
        
        // Retornar DTO de respuesta
        return PersonaMapper.toResponseDTO(personaGuardada);
    }

    @Override
    public void deletePersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public PersonaResponseDTO findPersona(Long id) {
        Optional<Persona> persona = persoRepo.findById(id);
        if (persona.isPresent()) {
            return PersonaMapper.toResponseDTO(persona.get());
        }
        throw new IllegalArgumentException("Persona no encontrada con ID: " + id);
    }

    @Override
    public PersonaResponseDTO editPersona(Long id, PersonaRequestDTO personaDTO) {
        // Validar DTO
        personaDTO.validar();
        
        // Buscar persona existente
        Optional<Persona> personaExistente = persoRepo.findById(id);
        if (!personaExistente.isPresent()) {
            throw new IllegalArgumentException("Persona no encontrada con ID: " + id);
        }
        
        Persona persona = personaExistente.get();
        
        // Validar nombre único (si cambió el nombre)
        if (!persona.getNombre().equals(personaDTO.getNombre())) {
            validarNombreUnico(personaDTO.getNombre());
        }
        
        // Actualizar entidad
        PersonaMapper.updateEntity(persona, personaDTO);
        
        // Guardar cambios
        Persona personaActualizada = persoRepo.save(persona);
        
        return PersonaMapper.toResponseDTO(personaActualizada);
    }

    /**
     * REGLA DE NEGOCIO PRIVADA:
     * Validar que no exista otra persona con el mismo nombre
     */
    private void validarNombreUnico(String nombre) {
        Optional<Persona> personaExistente = persoRepo.findByNombre(nombre);
        if (personaExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una persona con el nombre: " + nombre);
        }
    }
}
