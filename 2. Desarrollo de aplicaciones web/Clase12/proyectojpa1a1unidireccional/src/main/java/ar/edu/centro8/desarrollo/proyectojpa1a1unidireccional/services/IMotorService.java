package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.services;

import java.util.List;

import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;

public interface IMotorService {
    // lectura
    public List<Motor> obtenerMotores();

    // alta
    public Motor guardarMotor(Motor motor);

    // baja
    public boolean eliminarMotor(Long idMotor);

    // lectura de un solo objeto
    public Motor traerMotor(Long idMotor);

    // edición/modificación
    public Motor editarMotor(Long idOriginal, Integer nuevaCilindrada, String nuevaMarca);
}

