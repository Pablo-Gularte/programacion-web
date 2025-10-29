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

    /**
     * REGLA DE NEGOCIO EN DTO:
     * Validar que la marca no esté vacía
     */
    public void validarMarca() {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }
        this.marca = marca.trim();
    }

    /**
     * Validación completa de DTO
     */
    public void validar() {
        validarMarca();
    }
}
