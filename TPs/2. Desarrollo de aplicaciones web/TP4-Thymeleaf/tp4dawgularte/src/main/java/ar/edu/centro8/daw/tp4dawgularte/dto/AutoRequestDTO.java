package ar.edu.centro8.daw.tp4dawgularte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString

public class AutoRequestDTO {
    private String marca;
    private double precio;

    /**
     * :: Regla de negocio ::
     * Validar que la marca no esté vacía
     */
    public void validarMarca() {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        marca = marca.trim();
    }

    /**
     * Validaciones varias de reglas de negocio
     */
    public void validarDTO() {
        validarMarca();
    }
}
