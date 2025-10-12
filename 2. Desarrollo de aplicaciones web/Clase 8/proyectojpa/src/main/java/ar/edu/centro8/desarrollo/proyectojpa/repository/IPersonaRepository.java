package ar.edu.centro8.desarrollo.proyectojpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.centro8.desarrollo.proyectojpa.model.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long>{
    
    /**
     * QUERY METHODS (Métodos de Consulta Derivados)
     * Spring Data JPA genera automáticamente las consultas SQL
     * basándose en el nombre del método
     */
    
    // Buscar persona por nombre (para validar duplicados)
    Optional<Persona> findByNombre(String nombre);
    
    // Otros ejemplos de Query Methods:
    
    // Buscar por edad
    List<Persona> findByEdad(int edad);
    
    // Buscar por edad mayor que
    List<Persona> findByEdadGreaterThan(int edad);
    
    // Buscar por nombre que contenga una cadena (LIKE)
    List<Persona> findByNombreContaining(String nombre);
    
    // Buscar por nombre ignorando mayúsculas/minúsculas
    Optional<Persona> findByNombreIgnoreCase(String nombre);
    
    // Buscar por edad entre dos valores
    List<Persona> findByEdadBetween(int edadMin, int edadMax);
    
    // Verificar si existe por nombre
    boolean existsByNombre(String nombre);
    
    // Contar personas por edad
    long countByEdad(int edad);
    
    // Eliminar por nombre
    void deleteByNombre(String nombre);
}
