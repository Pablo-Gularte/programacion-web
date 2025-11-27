package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {
    /**
     * Query Method para verificar si existe un estudiante con el DNI proporcionado
     * 
     * @param dni El DNI a verificar
     * @return true si existe un estudiante con ese DNI, false en caso contrario
     */
    public boolean existsByDni(String dni);

    /**
     * Query Method que permite verificar si un estudiante (DNI) ya está asignado a un grado
     * (Grado is not null)
     * 
     * @param dni El DNI del estudiante a verificar
     * @return true si el estudiante con ese DNI ya está asignado a un grado, false en caso contrario
     */
    public boolean existsByDniAndGradoIsNotNull(String dni);

    /**
     * Query Method para obtener todos los estudiantes asignados a un grado específico
     * 
     * @param idGrado El ID del grado
     * @return Lista de estudiantes asignados al grado con el ID proporcionado
     */
    public List<Estudiante> findByGradoId(Long idGrado);

    /**
     * Query Method para obtener todos los estudiantes que no están asignados a ningún grado
     * 
     * @return Lista de estudiantes sin grado asignado
     */
    public List<Estudiante> findByGradoIsNull();
}
