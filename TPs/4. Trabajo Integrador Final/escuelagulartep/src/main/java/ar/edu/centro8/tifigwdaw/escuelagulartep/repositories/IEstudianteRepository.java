package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {
    /**
     * Query Method para verificar si existe un estudiante con el DNI proporcionado
     * @param dni El DNI a verificar
     * @return true si existe un estudiante con ese DNI, false en caso contrario
     */
    public boolean existsByDni(String dni);
}
