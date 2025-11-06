package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Auto;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    
    // Consulta personalizada para buscar autos por marca de motor
    @Query("SELECT a FROM Auto a WHERE a.motor.marcaMotor = ?1")
    List<Auto> findByMarcaMotor(String marcaMotor);
    
    // Consulta para buscar autos por cilindrada del motor
    @Query("SELECT a FROM Auto a WHERE a.motor.cilindrada = ?1")
    List<Auto> findByCilindrada(Integer cilindrada);
}