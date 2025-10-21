package ar.edu.centro8.daw.tp3dawgularte.service;

import java.util.List;

import ar.edu.centro8.daw.tp3dawgularte.model.Auto;

public interface IAutoService {
    // Listar todos los autos
    public List<Auto> getAllAutos();

    // Listar un auto por ID
    public Auto getAutoById(Long id);

    // Guardar un auto
    public void saveAuto(Auto autoNuevo);

    // Modificar un auto vía ID
    public void editAuto(Long id, String nuevaMArca, double nuevoPrecio);

    // Eliminar un auto vía ID
    public void deleteAuto(Long id);
}
