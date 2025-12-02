package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Boletin;
import jakarta.transaction.Transactional;

@Repository
public interface IBoletinRepository extends JpaRepository<Boletin, Long> {

    /**
     * Buscar el boletín asociado a un estudiante por su ID y devuelve las notas de las asignaturas.
     * 
     * @param idEstudiante ID del estudiante.
     * @return El boletín del estudiante.
     */
    public List<Boletin> findByEstudianteId(Long idEstudiante);

    /**
     * Elimina todas las notas asociadas al ID de estudiante indicado.
     * 
     * @param idEstudiante ID del estudiante.
     */
    @Modifying      // Añadido para que JPA/Hibernate procese correctamente la transacción masiva
    @Transactional  // Añadido para que JPA/Hibernate procese correctamente la transacción masiva
    public void deleteByEstudianteId(Long idEstudiante);
}
