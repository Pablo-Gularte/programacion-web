package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "motor")
@Getter @Setter
@NoArgsConstructor
public class Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMotor;

    @Column(name = "cilindrada", nullable = false)  
    private Integer cilindrada;

    @Column(name = "marca_motor", nullable = false)
    private String marcaMotor;

    public Motor(Integer cilindrada, String marcaMotor) {
        this.cilindrada = cilindrada;
        this.marcaMotor = marcaMotor;
    }

    //🔴 RELACION UNIDIRECCIONAL: 
    //Motor NO tiene referencia a Auto
    //Solo Auto conoce a Motor, Motor NO conoce a Auto

    // En relaciones unidireccionales NO es obligatorio implementar equals() y hashCode()
    // pero es buena práctica hacerlo por consistencia
    // @Override
    // public int hashCode() {
    //     return (idMotor == null) ? 0 : idMotor.hashCode();
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Motor other = (Motor) obj;
        
    //     // Solo comparamos por ID
    //     if (idMotor == null) {
    //         return other.idMotor == null;
    //     }
    //     return idMotor.equals(other.idMotor);
    // }

    @Override
    public String toString() {
        return "Motor{" +
                "idMotor=" + idMotor +
                ", cilindrada=" + cilindrada +
                ", marcaMotor='" + marcaMotor + '\'' +
                '}';
    }
}