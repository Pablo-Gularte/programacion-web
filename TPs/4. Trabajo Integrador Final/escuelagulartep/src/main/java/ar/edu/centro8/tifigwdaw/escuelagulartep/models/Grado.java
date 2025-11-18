package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity(name="grados")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado", nullable = false)
    private Long id;

    private String nombreGrado;
    private int ciclo;
    private String turno;
    private String docente;
    private boolean activo;

    @OneToMany(mappedBy = "grado", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("grado")
    private List<Estudiante> estudiantes = new ArrayList<>();
}
