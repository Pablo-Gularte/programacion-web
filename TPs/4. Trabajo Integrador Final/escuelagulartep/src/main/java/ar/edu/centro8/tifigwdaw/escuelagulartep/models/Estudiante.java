package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
@Table(
        name="estudiantes",
        uniqueConstraints = {
                // Valido que no haya ningún DNI duplicado
                @UniqueConstraint( columnNames = "dni" ),
                // Valido que no haya nigún estudiante con el mismo nombre, apellido, edad y dni
                @UniqueConstraint( columnNames = {"nombre", "apellido", "edad", "dni"} )
        }
    )
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
    
    @Column(nullable = false)
    private String dni;

    private String direccion;
    private String nombreMadre;
    private String nombrePadre;
    private boolean hnoEnEscuela = false;
    private boolean esRegular = true;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    @JsonIgnoreProperties("estudiantes")
    private Grado grado;
}
