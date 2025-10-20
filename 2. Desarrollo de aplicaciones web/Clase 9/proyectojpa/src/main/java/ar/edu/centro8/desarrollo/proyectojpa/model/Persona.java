package ar.edu.centro8.desarrollo.proyectojpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Entidad Persona con reglas de dominio
 */
@Entity
@AllArgsConstructor
@Getter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private int edad;

    public Persona(){}

    // Setter personalizado para id
    public void setId(long id) {
        this.id = id;
    }

    // Setter personalizado para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * REGLA DE NEGOCIO EN MODELO:
     * La edad debe estar entre 0 y 120 años (regla de dominio)
     * Esta es una validación intrínseca del objeto Persona
     */
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (edad > 120) {
            throw new IllegalArgumentException("La edad no puede ser mayor a 120 años");
        }
        this.edad = edad;
    }
}
