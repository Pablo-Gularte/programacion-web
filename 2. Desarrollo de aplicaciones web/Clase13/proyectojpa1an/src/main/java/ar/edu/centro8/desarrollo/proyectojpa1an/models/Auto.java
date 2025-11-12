package ar.edu.centro8.desarrollo.proyectojpa1an.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auto", nullable = false)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "precio", nullable = false)
    private double precio;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_concesionaria")
    private Concesionaria concesionaria;

    public Auto(String marca, double precio, Concesionaria concesionaria) {
        this.marca = marca;
        this.precio = precio;
        this.concesionaria = concesionaria;
    }

    //AGREGADO
    public void setConcesionaria(Concesionaria concesionaria) {
        this.concesionaria = concesionaria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((concesionaria == null) ? 0 : concesionaria.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Auto other = (Auto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        if (concesionaria == null) {
            if (other.concesionaria != null)
                return false;
        } else if (!concesionaria.equals(other.concesionaria))
            return false;
        return true;
    }

    

}
