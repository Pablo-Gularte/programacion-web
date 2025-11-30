package ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)    // Agrego anotación para serializar ENUM y poder enviarlo como JSON desde controlador
public enum TipoAsistencia {
    PRESENTE("Presente"),
    AUSENTE("Ausente"),
    TARDE("Llegada tarde");

    private String leyenda;

    TipoAsistencia(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getNombre() {
        return this.name();
    }

    public String getLeyenda() {
        return this.leyenda;
    }
}
