package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

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
public enum NombreGrado {
    PRIMERO("primer grado", Ciclo.PRIMERO),
    SEGUNDO("segundo grado", Ciclo.PRIMERO),
    TERCERO("tercer grado", Ciclo.PRIMERO),
    CUARTO("cUarto grado", Ciclo.SEGUNDO),
    QUINTO("quinto grado", Ciclo.SEGUNDO),
    SEXTO("sexto grado", Ciclo.SEGUNDO),
    SEPTIMO("séptimo grado", Ciclo.SEGUNDO);

    private String leyendaUI;
    private Ciclo cicloAsociado;

    NombreGrado(String leyenda, Ciclo ciclo) {
        this.leyendaUI = leyenda;
        this.cicloAsociado = ciclo;
    }

    public String getLeyendaUI() {
        return this.leyendaUI;
    }

    public Ciclo getCicloAsociado() {
        return this.cicloAsociado;
    }
}
