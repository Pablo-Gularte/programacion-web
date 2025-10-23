package ar.edu.centro8.daw.tp3dawgularte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString

public class AutoRequestDTO {
    private Long id;
    private String marca;
    private String modelo;
    private double precio;
    private String color;
    private String patente;

    public void validarMarca() {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }
        this.marca = marca.trim();
    }

    public void validar() {
        validarMarca();
    }
}
