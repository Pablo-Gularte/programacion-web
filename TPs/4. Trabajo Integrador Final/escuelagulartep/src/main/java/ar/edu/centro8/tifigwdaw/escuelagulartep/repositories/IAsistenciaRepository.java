package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Asistencia;
import jakarta.transaction.Transactional;

@Repository
public interface IAsistenciaRepository extends JpaRepository<Asistencia, Long> {

    public List<Asistencia> findByEstudianteId(Long idEstudiante);

    @Modifying      // Añadido para que JPA/Hibernate procese correctamente la transacción masiva
    @Transactional  // Añadido para que JPA/Hibernate procese correctamente la transacción masiva
    public void deleteByEstudianteId(Long idEstudiante);
}
