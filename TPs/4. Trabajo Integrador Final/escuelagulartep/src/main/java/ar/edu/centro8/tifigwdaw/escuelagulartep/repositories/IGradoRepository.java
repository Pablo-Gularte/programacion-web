package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.NombreGrado;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.Turno;

@Repository
public interface IGradoRepository extends JpaRepository<Grado, Long> {
    /**
     * Query Method para verificar si existe un grado con el mismo nombre y turno
     * @param nombre Es el nombre del grado a verificar.
     * @param turno Es el turno del grado a verificar.
     * @return true si existe un grado con el mismo nombre y turno, false en caso contrario.
     */
    boolean existsByNombreAndTurno(NombreGrado nombre, Turno turno);

    /**
     * Query Method para verificar si ya existe el docente en un grado activo.
     * @param docente Es el nombre del docente a verificar.
     * @param activo Indica si el grado está activo o no.
     * @return true si el docente ya está asignado a un grado activo, false en caso contrario.
     */
    boolean existsByDocenteAndActivo(String docente, boolean activo);
}
