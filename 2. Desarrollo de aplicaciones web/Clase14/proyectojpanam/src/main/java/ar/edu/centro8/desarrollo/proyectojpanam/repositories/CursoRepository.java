package ar.edu.centro8.desarrollo.proyectojpanam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.centro8.desarrollo.proyectojpanam.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long>{
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM estudiante_curso WHERE fk_curso NOT IN (SELECT id_curso FROM curso)", nativeQuery = true)
    void limpiarReferenciasHuerfanas();

}
