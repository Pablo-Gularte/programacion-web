package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services;

import java.util.List;
import java.math.BigDecimal;

import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Auto;
import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;

public interface IAutoService {
    //método para traer a todas las personas
    //lectura
    public List<Auto> obtenerAutos();

    //alta
    public Auto guardarAuto(Auto auto);

    //baja
    public boolean eliminarAuto(Long id);

    //lectura de un solo objeto
    public Auto traerAuto(Long id);

    //edición/modificación
    public Auto editarAuto(Long idOriginal, String nuevaMarca, String nuevoModelo, 
                          BigDecimal nuevoPrecio, Motor nuevoMotor);
    
    // Consultas específicas
    public List<Auto> buscarPorMarcaMotor(String marcaMotor);
    public List<Auto> buscarPorCilindrada(Integer cilindrada);
}
