package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString
public class GradoRequestDTO {
    private String nombreGrado;
    private int ciclo;
    private String turno;
    private String docente;
    private boolean activo;
    private List<EstudianteRequestDTO> estudiantes;
}
