package ar.edu.centro8.daw.tp4dawgularte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class AutoResponseDTO {
    private long id;
    private String marca;
    private double precio;
}
