package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Asistencia;

@Repository
public interface IAsistenciaRepository extends JpaRepository<Asistencia, Long> {

    public List<Asistencia> findByEstudianteId(Long idEstudiante);
}
