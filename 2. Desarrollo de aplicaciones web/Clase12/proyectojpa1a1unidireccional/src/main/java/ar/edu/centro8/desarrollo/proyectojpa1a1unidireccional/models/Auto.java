package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auto")
@Getter @Setter
@NoArgsConstructor
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuto;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    //🔴 RELACION UNIDIRECCIONAL FK - PK
    //Solo Auto conoce a Motor
    //Motor NO tiene referencia de vuelta a Auto
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  // ⚡ EAGER - Simple y directo
    @JoinColumn(name = "id_motor", referencedColumnName = "idMotor")
    private Motor motor;

    public Auto(String marca, String modelo, BigDecimal precio, Motor motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.motor = motor;
    }

    // En relaciones unidireccionales NO es obligatorio implementar equals() y hashCode()
    // pero es buena práctica hacerlo por consistencia
    // @Override
    // public int hashCode() {
    //     return (idAuto == null) ? 0 : idAuto.hashCode();
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Auto other = (Auto) obj;
        
    //     // Solo comparamos por ID
    //     if (idAuto == null) {
    //         return other.idAuto == null;
    //     }
    //     return idAuto.equals(other.idAuto);
    // }

    @Override
    public String toString() {
        return "Auto{" +
                "idAuto=" + idAuto +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", motor=" + (motor != null ? motor.toString() : "null") +
                '}';
    }
}