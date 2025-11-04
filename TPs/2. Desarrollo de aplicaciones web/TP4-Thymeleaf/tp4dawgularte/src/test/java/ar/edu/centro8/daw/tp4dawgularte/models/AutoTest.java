package ar.edu.centro8.daw.tp4dawgularte.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutoTest {
    // Prueba del Flujo Normal (Setter de Precio)
    @Test
    void setPrecio_conValorPositivo_estableceCorrectamente() {
        // Arrange (Preparar)
        Auto auto = new Auto();
        double precioEsperado = 15000.50;

        // Act (Actuar)
        auto.setPrecio(precioEsperado);

        // Assert (Afirmar/Validar)
        // Verificamos que el getter de Lombok devuelve el valor que establecimos.
        assertEquals(precioEsperado, auto.getPrecio(), "El precio debería establecerse correctamente.");
    }

    // Prueba de la Lógica de Excepción
    @Test
    void setPrecio_conValorNegativo_lanzaExcepcion() {
        // Arrange (Preparar)
        Auto auto = new Auto();
        double precioNegativo = -100.0;

        // Act & Assert (Actuar y Validar en conjunto)
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            auto.setPrecio(precioNegativo);
        }, "Debería lanzarse una IllegalArgumentException para precio negativo.");

        // Opcional: Verificar el mensaje de la excepción
        String mensajeEsperado = "El precio no puede ser negativo.";
        assertTrue(excepcion.getMessage().contains(mensajeEsperado), "El mensaje de la excepción debe ser el correcto.");
    }

    // Prueba de Constructor con AllArgsConstructor
    @Test
    void constructorAllArgs_creaObjetoCorrectamente() {
        // Arrange (Preparar)
        Long idEsperado = 1L;
        String marcaEsperada = "Ford";
        double precioEsperado = 25000.00;

        // Act (Actuar)
        Auto auto = new Auto(idEsperado, marcaEsperada, precioEsperado);

        // Assert (Afirmar/Validar)
        assertEquals(idEsperado, auto.getId(), "El ID debe ser el esperado.");
        assertEquals(marcaEsperada, auto.getMarca(), "La marca debe ser la esperada.");
        assertEquals(precioEsperado, auto.getPrecio(), "El precio debe ser el esperado.");
    }
}
