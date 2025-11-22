package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

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
public enum Turno {
    MAÑANA("turno mañana"),
    TARDE("turno tarde"),
    JORNADA_COMPLETA("jornada completa");

    private final String leyendaUI;

    Turno(String leyenda) {
        this.leyendaUI = leyenda;
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }
}
