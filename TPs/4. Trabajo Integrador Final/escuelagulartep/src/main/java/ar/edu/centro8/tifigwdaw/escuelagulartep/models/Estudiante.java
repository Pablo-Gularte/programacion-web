package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
@Table(name="estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estudiante", nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="El nombre no puede estar vacío.")
    private String nombre;
    
    @Column(nullable = false)
    @NotBlank(message="El apellido no puede estar vacío.")
    private String apellido;
    
    @Column(nullable = false)
    @Min(value = 5, message = "La edad no puede ser menor a {value} años.")
    @Max(value = 14, message = "La edad no puede ser mayor a {value} años.")
    private int edad;
    
    @Column(unique = true, nullable = false)
    private String dni;

    private String direccion;
    private String nombreMadre;
    private String nombrePadre;
    private Boolean hnoEnEscuela = false;
    private Boolean regular = true;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonIgnoreProperties("estudiantes")
    private Grado grado;
}
