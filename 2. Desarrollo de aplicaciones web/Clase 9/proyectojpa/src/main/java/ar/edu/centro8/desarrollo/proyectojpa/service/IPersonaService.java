package ar.edu.centro8.desarrollo.proyectojpa.service;

import java.util.List;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaRequestDTO;
import ar.edu.centro8.desarrollo.proyectojpa.dto.PersonaResponseDTO;

public interface IPersonaService {
    //método para traer a todas las personas
    //lectura
    public List<PersonaResponseDTO> getPersonas();

    //alta
    public PersonaResponseDTO savePersona(PersonaRequestDTO personaDTO);

    //baja
    public void deletePersona(Long id);

    //lectura de un solo objeto
    public PersonaResponseDTO findPersona(Long id);

    //edición/modificación
    public PersonaResponseDTO editPersona(Long id, PersonaRequestDTO personaDTO);
}
