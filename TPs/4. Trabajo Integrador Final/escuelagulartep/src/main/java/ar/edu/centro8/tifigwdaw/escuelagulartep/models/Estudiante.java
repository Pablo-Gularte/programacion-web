package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity(name="estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estudiante", nullable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String nombreMadre;
    private String nombrePadre;
    private boolean hnoEnEscuela;
    private boolean esRegular;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;
}
