package ar.edu.centro8.daw.tp3dawgularte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.daw.tp3dawgularte.model.Auto;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    /**
     * QUERY METHODS
     */
    //  Buscar autos por marca
    List<Auto> findByMarca(String marca);

    // Buscar autos por modelo
    List<Auto> findByModelo(String modelo);
    
    // Buscar autos por color
    List<Auto> findByColor(String color);

    // Buscar auto por patente
    Optional<Auto> findByPatente(String patente);

    // Contar autos por marca
    long countByMarca(String marca);

    // Buscar autos por precio entre dos valores
    List<Auto> findByPrecioBetween(double precioMinimo, double precioMaximo);

    // Verificar si existe unapatente dada
    boolean existsByPatente(String patente);
}
