package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;

import ar.edu.centro8.daw.tp3dawgularte.model.Persona;

public interface IPersonaService {
    public List<Persona> getPersonas();

    public Persona findPersona(Long id);

    public void savePersona(Persona persona);

    public void deletePersona(Long id);

    public void editPersona(Persona nuevaPersona);
}
