package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum que representa los ciclos educativos de cada grado en la escuela.
 * Cada valor del enum tiene una leyenda asociada para mostrar en la interfaz de usuario.
 * <pre>
 * Valores posibles:
 * PRIMERO: agrupa los grados de primero a tercero.
 * SEGUNDO: agrupa los grados de cuarto a séptimo.
 * </pre>
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)    // Agrego anotación para serializar ENUM y poder enviarlo como JSON desde controlador
public enum Ciclo {
    PRIMERO("Primer Ciclo"),
    SEGUNDO("Segundo Ciclo");

    private final String leyendaUI;

    Ciclo(String leyenda) {
        this.leyendaUI = leyenda;
    }

    public String getValor() {
        return this.name();
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }
}
