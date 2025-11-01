package ar.edu.centro8.daw.tp4dawgularte.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AutoResponseDTO {
    private long id;
    private String marca;
    private double precio;

    public String precioConFormato() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        // Define el patrón para dos decimales y separador de miles
        DecimalFormat formato = new DecimalFormat("$#,##0.00", simbolos);

        // Formatea el número
        return formato.format(precio);
    }
}
