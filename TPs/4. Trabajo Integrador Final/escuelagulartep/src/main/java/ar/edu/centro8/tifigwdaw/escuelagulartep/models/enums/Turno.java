package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum que representa los posibles turnos de un grado en la escuela.
 * Cada valor del enum tiene una leyenda asociada para mostrar en la interfaz de usuario.
 * <pre>
 * Valores posibles:
 * MAÑANA: turno de la mañana.
 * TARDE: turno de la tarde.
 * JORNADA_COMPLETA: doble turno.
 * </pre>
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT) // Agrego anotación para serializar ENUM y poder enviarlo como JSON desde controlador
public enum Turno {
    MAÑANA("Turno Mañana"),
    TARDE("Turno Tarde"),
    JORNADA_COMPLETA("Jornada Completa");

    private final String leyendaUI;

    Turno(String leyenda) {
        this.leyendaUI = leyenda;
    }

    public String getNombre() {
        return this.name();
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }
}
