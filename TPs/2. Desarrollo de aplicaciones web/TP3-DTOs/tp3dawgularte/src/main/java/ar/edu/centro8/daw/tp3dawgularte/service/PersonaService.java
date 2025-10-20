package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.daw.tp3dawgularte.model.Persona;
import ar.edu.centro8.daw.tp3dawgularte.repository.IPersonaRepository;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersonaRepository personaRepo;

    @Override
    public List<Persona> getPersonas() {
        return personaRepo.findAll();
    }

    @Override
    public Persona findPersona(Long id) {
        return personaRepo.findById(id).orElse(null);
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepo.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepo.deleteById(id);
    }

    @Override
    public void editPersona(Persona nuevaPersona) {
        Persona personaModificada = personaRepo.findById(nuevaPersona.getId()).orElse(null);
        personaModificada.setNombre(nuevaPersona.getNombre());
        personaModificada.setEdad(nuevaPersona.getEdad());
        savePersona(nuevaPersona);
    }

}
