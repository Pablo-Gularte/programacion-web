package ar.edu.centro8.desarrollo.proyectojpa.service;

import java.util.List;
import ar.edu.centro8.desarrollo.proyectojpa.model.Persona;

public interface IPersonaService {
    //método para traer a todas las personas
    //lectura
    public List<Persona> getPersonas();

    //alta
    public void savePersona(Persona perso);

    //baja
    public void deletePersona(Long id);

    //lectura de un solo objeto
    public Persona findPersona(Long id);

    //edición/modificación
    public void editPersona(Long idOriginal, Long idNueva,
                            String nuevoNombre,
                            int nuevaEdad);
}
