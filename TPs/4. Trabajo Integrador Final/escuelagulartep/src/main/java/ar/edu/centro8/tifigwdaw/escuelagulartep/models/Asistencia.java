package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import java.time.LocalDate;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.TipoAsistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data

@Entity
@Table(name = "asistencias")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAsistencia tipoAsistencia;
}
