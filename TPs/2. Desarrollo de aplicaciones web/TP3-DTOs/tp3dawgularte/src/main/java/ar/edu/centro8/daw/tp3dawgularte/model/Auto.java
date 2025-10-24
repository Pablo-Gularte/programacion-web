package ar.edu.centro8.daw.tp3dawgularte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString

@Entity
@Table(name="autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private double precio;
    private String color;
    private String patente;
    private String nroChasis;
    private String nroMotor;

    /**
     * REGLA DE NOGOCIO: el número de chasis debe contener exactamente 17 caracteres.
     * Si se recibe un nro de chasis con menos o más caracteres se lanza una excepción de tipo
     * argumento ilegal
     * 
     * @param nroChasis Es el número de chasis a asignar al auto.
     * 
     */
    public void setNroChasis(String nroChasis) {
        if(nroChasis.length() == 0 || nroChasis.length() > 17) {
            throw new IllegalArgumentException("El número de chasis debe constar de 17 caracteres exactamente.");
        }
        this.nroChasis = nroChasis;
    }

    /**
     * REGLA DE NOGOCIO: el número de motor debe contener exactamente 17 caracteres.
     * Si se recibe un nro de motor con menos o más caracteres se lanza una excepción de tipo
     * argumento ilegal
     * 
     * @param nroMotor Es el número de motor a asignar al auto.
     * 
     */
    public void setNroMotor(String nroMotor) {
        if(nroMotor.length() == 0 || nroMotor.length() > 17) {
            throw new IllegalArgumentException("El número de motor debe constar de 17 caracteres exactamente.");
        }
        this.nroMotor = nroMotor;
    }

    /**
     * REGLA DE NOGOCIO: La patente debe contener exactamente 8 caracteres y debe respetar
     * el formato "XXNNNXXX" donde "X" se corresponde con una letra mayúscula entre A y Z y "N"
     * se corresponde con un número entre 0 y 9
     * 
     * @param patente el el valor de patente ingresado que será validado
     */
    public void setPatente(String patente) {
        // Se aplica patrón de la expresión regular para validar formato
        // de patente ingresado. La expresión regular sigue el siguiente 
        // patrón:
        // [A-Z]{2} -> Dos caracteres alfabéticos mayúsculos (A-Z)
        // [0-9]{3} -> Tres caracteres numéricos (0-9)
        // [A-Z]{3} -> Tres caracteres alfanuméricos mayúsculos (A-Z)
        String regex = "^[A-Z]{2}[0-9]{3}[A-Z]{3}$";
        String patenteNormalizada = patente.trim().toUpperCase();

        if(patenteNormalizada.length() == 0 || patenteNormalizada.length() > 8) {
            throw new IllegalArgumentException("La patente debe constar de 8 caracteres exactamente.");
        }

        if (!patenteNormalizada.matches(regex)) {
            throw new IllegalArgumentException("La patente debe tener el formato \"XXNNNXXX\" (dos letras, tres números, tres letras).");
        }
        
        this.patente = patenteNormalizada;
    }
}
