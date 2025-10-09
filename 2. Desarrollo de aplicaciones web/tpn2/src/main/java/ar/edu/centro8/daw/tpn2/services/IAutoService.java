package ar.edu.centro8.daw.tpn2.services;

import java.util.List;

import ar.edu.centro8.daw.tpn2.models.Auto;

public interface IAutoService {
    public List<Auto> getAutos();

    //alta
    public void saveAuto(Auto perso);

    //baja
    public void deleteAuto(Long id);

    //lectura de un solo objeto
    public Auto findAuto(Long id);

    //edición/modificación
    public void editAuto(Long id, String nuevaMarca, double nuevoPrecio);

}
