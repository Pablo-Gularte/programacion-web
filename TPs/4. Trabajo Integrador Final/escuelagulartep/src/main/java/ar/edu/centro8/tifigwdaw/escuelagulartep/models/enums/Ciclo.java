package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

/**
 * Enum que representa los ciclos educativos de cada grado en la escuela.
 * Cada valor del enum tiene una leyenda asociada para mostrar en la interfaz de usuario.
 * <pre>
 * Valores posibles:
 * PRIMERO: agrupa los grados de primero a tercero.
 * SEGUNDO: agrupa los grados de cuarto a séptimo.
 * </pre>
 */
public enum Ciclo {
    PRIMERO("primer ciclo"),
    SEGUNDO("segundo ciclo");

    private final String leyendaUI;

    Ciclo(String leyenda) {
        this.leyendaUI = leyenda;
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }
}
