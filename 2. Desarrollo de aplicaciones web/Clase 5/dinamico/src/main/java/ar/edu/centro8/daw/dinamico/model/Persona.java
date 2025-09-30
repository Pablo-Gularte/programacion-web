package ar.edu.centro8.daw.dinamico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// @Data
public class Persona {
    private String nombre;
    private int edad;
}
