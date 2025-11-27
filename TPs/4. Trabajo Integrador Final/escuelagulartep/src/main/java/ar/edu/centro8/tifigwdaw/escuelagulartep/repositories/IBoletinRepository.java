package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Boletin;

@Repository
public interface IBoletinRepository extends JpaRepository<Boletin, Long> {

    /**
     * Buscar el boletín asociado a un estudiante por su ID.
     * 
     * @param idEstudiante ID del estudiante.
     * @return El boletín del estudiante.
     */
    public Boletin findByEstudianteId(Long idEstudiante);
}
