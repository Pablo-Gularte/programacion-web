package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString
public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String nombreMadre;
    private String nombrePadre;
    private boolean hnoEnEscuela;
    private boolean esRegular;
    private Grado gardo;
}
