package ar.edu.centro8.daw.tp4dawgularte.repository;

import ar.edu.centro8.daw.tp4dawgularte.models.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AutoRepositoryTest {

    // 1. Inyección del Repositorio a Probar
    @Autowired
    private IAutoRepository autoRepository;

    // 2. TestEntityManager para insertar datos de prueba directamente
    @Autowired
    private TestEntityManager entityManager;

    // Datos de prueba
    private Auto autoFord;
    private Auto autoFiat;
    private Auto autoChevrolet;

    // Ejecutado antes de cada prueba para asegurar un estado inicial conocido
    @BeforeEach
    void setUp() {
        // Limpiar para asegurar idempotencia (aunque @DataJpaTest hace rollback,
        // usar TestEntityManager es el método preferido para inicializar el estado)
        
        // Persistir datos para las pruebas
        autoFord = entityManager.persist(new Auto(null, "Ford", 15000.00));
        autoFiat = entityManager.persist(new Auto(null, "Fiat", 8000.00));
        autoChevrolet = entityManager.persist(new Auto(null, "Chevrolet", 25000.00));
        
        // Es necesario hacer un flush para asegurar que las operaciones se han ejecutado en la BD
        entityManager.flush();
    }

    // ---

    // ## 🔍 Pruebas de Query Methods Personalizados

    // ### 1. Prueba de `findByMarca(String marca)`

    @Test
    void findByMarca_existente_devuelveAuto() {
        // Act
        // Usamos la marca del auto que persistimos
        Optional<Auto> resultado = autoRepository.findByMarca(autoFord.getMarca());

        // Assert
        assertTrue(resultado.isPresent(), "Debería encontrar un auto con la marca Ford.");
        assertEquals("Ford", resultado.get().getMarca(), "La marca del auto encontrado debe ser Ford.");
        assertEquals(15000.00, resultado.get().getPrecio(), 0.001, "El precio debe coincidir.");
    }

    @Test
    void findByMarca_noExistente_devuelveOptionalVacio() {
        // Act
        Optional<Auto> resultado = autoRepository.findByMarca("Toyota");

        // Assert
        assertFalse(resultado.isPresent(), "No debería encontrar un auto con la marca Toyota.");
    }

    // ### 2. Prueba de `findByPrecioBetween(double precioMinimo, double precioMaximo)`

    @Test
    void findByPrecioBetween_rangoIntermedio_devuelveDosAutos() {
        // Arrange
        // Rango que incluye a Ford (15000.00) y Fiat (8000.00) pero excluye a Chevrolet (25000.00)
        double min = 5000.00;
        double max = 20000.00;

        // Act
        List<Auto> resultados = autoRepository.findByPrecioBetween(min, max);

        // Assert
        assertEquals(2, resultados.size(), "Debería encontrar exactamente 2 autos en el rango.");
        
        // Verificamos que los autos correctos están en la lista
        boolean tieneFord = resultados.stream().anyMatch(a -> a.getMarca().equals("Ford"));
        boolean tieneFiat = resultados.stream().anyMatch(a -> a.getMarca().equals("Fiat"));
        assertTrue(tieneFord, "La lista debe contener el auto Ford.");
        assertTrue(tieneFiat, "La lista debe contener el auto Fiat.");
    }
    
    @Test
    void findByPrecioBetween_rangoVacio_devuelveListaVacia() {
        // Arrange
        // Rango que no incluye a ninguno de los autos persistidos
        double min = 100000.00;
        double max = 200000.00;

        // Act
        List<Auto> resultados = autoRepository.findByPrecioBetween(min, max);

        // Assert
        assertTrue(resultados.isEmpty(), "No debería encontrar autos en un rango fuera de los datos.");
    }

    // 3. Prueba de un método heredado de JpaRepository (Opcional, pero recomendado)
    @Test
    void findById_encuentraAuto() {
        // Act
        Optional<Auto> resultado = autoRepository.findById(autoFord.getId());

        // Assert
        assertTrue(resultado.isPresent(), "Debería encontrar el auto por su ID.");
    }
}