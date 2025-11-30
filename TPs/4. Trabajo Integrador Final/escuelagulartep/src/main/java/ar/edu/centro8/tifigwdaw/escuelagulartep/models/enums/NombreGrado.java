package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * Enum que representa los nombres de los grados escolares junto con su ciclo asociado.
 * Cada grado tiene una leyenda para mostrar en la interfaz de usuario y un ciclo asociado (primero o segundo ciclo).
 * <pre>
 * Valores posibles:
 * PRIMERO: primer grado, primer ciclo.
 * SEGUNDO: segundo grado, primer ciclo.
 * TERCERO: tercer grado, primer ciclo.
 * CUARTO: cuarto grado, segundo ciclo.
 * QUINTO: quinto grado, segundo ciclo.
 * SEXTO: sexto grado, segundo ciclo.
 * SEPTIMO: séptimo grado, segundo ciclo.
 * </pre>
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT) // Agrego anotación para serializar ENUM y poder enviarlo como JSON desde controlador
public enum NombreGrado {
    PRIMERO("Primer Grado", Ciclo.PRIMERO),
    SEGUNDO("Segundo Grado", Ciclo.PRIMERO),
    TERCERO("Tercer Grado", Ciclo.PRIMERO),
    CUARTO("Cuarto Grado", Ciclo.SEGUNDO),
    QUINTO("Quinto Grado", Ciclo.SEGUNDO),
    SEXTO("Sexto Grado", Ciclo.SEGUNDO),
    SEPTIMO("Séptimo Grado", Ciclo.SEGUNDO);

    private String leyendaUI;
    private Ciclo cicloAsociado;

    NombreGrado(String leyenda, Ciclo ciclo) {
        this.leyendaUI = leyenda;
        this.cicloAsociado = ciclo;
    }

    public String getNombre() {
        return this.name();
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }

    public Ciclo getCicloAsociado() {
        return this.cicloAsociado;
    }
}
