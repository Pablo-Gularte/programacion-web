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
     * Validación para regla de negocio: cada docente sólo puede estar activo
     * en un grado por vez
     * @param docente Es el nombre del docente a verificar.
     * @return true si el docente ya está asignado a un grado activo, false en caso contrario.
     */
    boolean existsByDocenteAndActivoTrue(String docente);

    /**
     * Query Method para recuperar un grado puscando por nombre y turno.
     * @param nombre es el nombre del grado a buscar.
     * @param turno es el turno del grado a buscar.
     * @return Devuelve el grado de nombre "nombre" en el turno "turno".
     */
    Grado findByTurnoAndNombre(Turno turno, NombreGrado nombre);
}
