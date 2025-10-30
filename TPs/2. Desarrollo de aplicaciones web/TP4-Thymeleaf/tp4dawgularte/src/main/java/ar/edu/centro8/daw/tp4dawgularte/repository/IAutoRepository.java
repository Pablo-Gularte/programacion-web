package ar.edu.centro8.daw.tp4dawgularte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.daw.tp4dawgularte.models.Auto;


@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    
    /**
     * :: Query Method ::
     * Permite obtener un listado de autos de una determinada marca.
     * 
     * @param marca es la marca por la que se quiere filtrar.
     * @return Devuelve un listado de autos de la marca especificada.
     */
    public Optional<Auto> findByMarca(String marca);

    /**
     * :: Query Method ::
     * Permite obtener un listado de autos en un rango de precios determinado.
     * 
     * @param precioMinimo es el menor precio de auto a considerar.
     * @param precioMaximo es el mayor precio de auto a considerar.
     * @return Devuelve un listado de autos con precios entre precioMinimo y precioMaximo.
     */
    public List<Auto> findByPrecioBetween(double precioMinimo, double precioMaximo);
}
