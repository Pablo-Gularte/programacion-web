package ar.edu.centro8.daw.tp3dawgularte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString

public class AutoResponsetDTO {
    private Long id;
    private String marca;
    private String modelo;
    private double precio;
    private String color;
    private String patente;
}
