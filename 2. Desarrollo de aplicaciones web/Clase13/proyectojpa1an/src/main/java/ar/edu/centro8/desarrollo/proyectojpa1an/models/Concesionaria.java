// CascadeType.ALL significa que todas las operaciones (crear, actualizar, eliminar) se propagarán a todos los elementos relacionados.

// orphanRemoval = true
// Esta opción activa la eliminación de huérfanos. Cuando una entidad padre (Concesionaria) es eliminada, sus hijos (Autos) serán automáticamente eliminados de la base de datos.

package ar.edu.centro8.desarrollo.proyectojpa1an.models;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Concesionaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concesionaria", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "concesionaria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // private HashSet<Auto> autos;
    private List<Auto> autos;

    // public Concesionaria(String nombre, HashSet<Auto> autos) {
        public Concesionaria(String nombre, List<Auto> autos) {
        this.nombre = nombre;
        this.autos = autos;
    }

    //AGREGADO
    public Auto agregarAuto(Auto auto) {
        this.autos.add(auto);
        return auto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // Solo usamos campos inmutables, NO la colección autos
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        // Usar instanceof para manejar proxies de Hibernate
        if (!(obj instanceof Concesionaria))
            return false;
        Concesionaria other = (Concesionaria) obj;
        
        // Si ambos tienen ID, comparar por ID (entidades persistidas)
        if (id != null && other.id != null) {
            return id.equals(other.id);
        }
        
        // Si no tienen ID, comparar por campos de negocio
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        
        return true;
    }

    

}
