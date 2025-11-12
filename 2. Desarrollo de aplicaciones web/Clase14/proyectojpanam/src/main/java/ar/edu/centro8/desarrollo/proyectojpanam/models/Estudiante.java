package ar.edu.centro8.desarrollo.proyectojpanam.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre del estudiante no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("estudiantes")
    @JoinTable(
        name = "estudiante_curso", 
        joinColumns = @JoinColumn(name = "fk_estudiante", referencedColumnName = "id_estudiante"),
        inverseJoinColumns = @JoinColumn(name = "fk_curso", referencedColumnName = "id_curso")    
    )
    private List<Curso> cursos = new ArrayList<>();

    public Estudiante(String nombre, List<Curso> cursos) {
        this.nombre = nombre;
        this.cursos = cursos;
    }

    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCurso(Curso curso) {
        if (cursos == null) {
            cursos = new ArrayList<>();
        }
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            // Sincronizar el otro lado de la relación
            if (curso.getEstudiantes() == null) {
                curso.setEstudiantes(new ArrayList<>());
            }
            if (!curso.getEstudiantes().contains(this)) {
                curso.getEstudiantes().add(this);
            }
        }
    }

    public void removerCurso(Curso curso) {
        if (cursos != null) {
            cursos.remove(curso);
            // Sincronizar el otro lado de la relación
            if (curso.getEstudiantes() != null) {
                curso.getEstudiantes().remove(this);
            }
        }
    }

    public void removerTodosLosCursos() {
        if (cursos != null) {
            // Crear una copia para evitar ConcurrentModificationException
            List<Curso> cursosACopiar = new ArrayList<>(cursos);
            for (Curso curso : cursosACopiar) {
                removerCurso(curso);
            }
        }
    }
}
