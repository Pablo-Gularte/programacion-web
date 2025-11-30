    package ar.edu.centro8.tifigwdaw.escuelagulartep.models;

    import java.util.List;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.Ciclo;
    import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.NombreGrado;
    import ar.edu.centro8.tifigwdaw.escuelagulartep.models.enums.Turno;
    import jakarta.persistence.CascadeType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;
    import jakarta.persistence.Transient;
    import jakarta.persistence.UniqueConstraint;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @AllArgsConstructor @NoArgsConstructor @Data
    @Entity
    @Table( name="grados",
            uniqueConstraints = {
                // Valido que no haya ningún grado con el mismo nombre en el mismo turno
                @UniqueConstraint( columnNames = {"nombre", "turno"} )
            }
    )
    public class Grado {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_grado", nullable = false)
        private Long id;

        // Indico que el atributo "nombre" es de tipo ENUM, no puede ser nulo y debe ser 
        // uno de los valores predefinidos en el ENUM NombreGrado
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private NombreGrado nombre;

        // Indico que el atributo "turno" es de tipo ENUM, no puede ser nulo y debe ser 
        // uno de los valores predefinidos en el ENUM Turno
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Turno turno;
        
        private String docente;
        private Boolean activo;

        @OneToMany(mappedBy = "grado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JsonIgnoreProperties("grado")
        private List<Estudiante> estudiantes;

        // Se utiliza anotación @Transient para indicar que el ciclo es un campo calculado
        // a partir del atributo nombre, utilizando el método .getCicloAsociado().
        // De esta manera se controla que la relación entre ciclo y grado siempre sea correcta. 
        // La base de datos solo almacena el nombre (PRIMERO, SEGUNDO, etc.) y el ciclo se deduce
        // automáticamente en tiempo de ejecución sin necesidad de código de validación adicional
        // porque la estructura misma del ENUM impone la regla.
        // Si no se especifica nombre de grado (lo cual no debería suceder) se lanza una excepción.
        // Se incluye esta validación, a pesar de ser redundante, como una buena práctica para 
        // explicitar la relación de dependencia entre los atributos nombre y ciclo.
        @Transient
        public Ciclo getCiclo() {
            if (this.nombre != null) {
                return this.nombre.getCicloAsociado();
            }
            throw new IllegalArgumentException("No se especificó nombre de grado.");
        }
    }
