package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data

@Entity
@Table(name="boletines")
public class Boletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boletin", nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "El año no puede ser nulo.")
    private Integer anio;

    
    @Column(nullable = false)
    @Min(value = 1, message = "El bimestre no puede ser menor a {value}.")
    @Max(value = 4, message = "El bimestre no puede ser mayor a {value}.")
    private Integer bimestre;

    @Column(nullable = false)
    @Min(value = 1, message = "La nota no puede ser menor a {value}.")
    @Max(value = 10, message = "La nota no puede ser mayor a {value}.")
    private Integer nota;
    
    @Column(nullable = false)
    @NotNull(message = "La asignatura no puede ser nula.")
    private String asignatura;
    
    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    @NotNull(message = "El estudiante no puede ser nulo.")
    private Estudiante estudiante;
}
