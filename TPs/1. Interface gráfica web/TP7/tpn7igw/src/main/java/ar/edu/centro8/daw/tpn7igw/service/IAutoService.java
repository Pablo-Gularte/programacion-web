package ar.edu.centro8.daw.tpn7igw.service;

import java.util.List;

import ar.edu.centro8.daw.tpn7igw.model.Auto;

public interface IAutoService {
    public List<Auto> getAutos();

    //alta
    public void saveAuto(Auto perso);

    //baja
    public void deleteAuto(Long id);

    //lectura de un solo objeto
    public Auto findAuto(Long id);

    //edición/modificación
    public void editAuto(Long id, Auto nuevoAuto);

}
