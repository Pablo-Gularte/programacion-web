package ar.edu.centro8.desarrollo.proyectojpanam.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @ManyToMany(mappedBy = "cursos", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("cursos")
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Curso(String nombre, List<Estudiante> estudiantes) {
        this.nombre = nombre;
        this.estudiantes = estudiantes;
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            // Sincronizar el otro lado de la relación
            if (estudiante.getCursos() == null) {
                estudiante.setCursos(new ArrayList<>());
            }
            if (!estudiante.getCursos().contains(this)) {
                estudiante.getCursos().add(this);
            }
        }
    }

    public void removerEstudiante(Estudiante estudiante) {
        if (estudiantes != null) {
            estudiantes.remove(estudiante);
            // Sincronizar el otro lado de la relación
            if (estudiante.getCursos() != null) {
                estudiante.getCursos().remove(this);
            }
        }
    }

    public void removerTodosLosEstudiantes() {
        if (estudiantes != null) {
            // Crear una copia para evitar ConcurrentModificationException
            List<Estudiante> estudiantesACopiar = new ArrayList<>(estudiantes);
            for (Estudiante estudiante : estudiantesACopiar) {
                removerEstudiante(estudiante);
            }
        }
    }
    
}
