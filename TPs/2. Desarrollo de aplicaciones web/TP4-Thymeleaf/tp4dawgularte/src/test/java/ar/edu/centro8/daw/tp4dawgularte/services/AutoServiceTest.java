package ar.edu.centro8.daw.tp4dawgularte.services;

import ar.edu.centro8.daw.tp4dawgularte.dto.AutoRequestDTO;
import ar.edu.centro8.daw.tp4dawgularte.dto.AutoResponseDTO;
import ar.edu.centro8.daw.tp4dawgularte.models.Auto;
import ar.edu.centro8.daw.tp4dawgularte.repository.IAutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Se utiliza @ExtendWith(MockitoExtension.class) para habilitar Mockito en JUnit 5,
 * lo cual permite usar las anotaciones @Mock e @InjectMocks.
 */
@ExtendWith(MockitoExtension.class)
public class AutoServiceTest {

    // 1. Inyecta el servicio que vamos a probar
    @InjectMocks
    private AutoService autoService;

    // 2. Simula (Mockea) el repositorio, que es la dependencia de AutoService
    @Mock
    private IAutoRepository autoRepo;

    // Objetos de prueba
    private Auto autoEntity;
    private AutoRequestDTO autoRequestDTO;

    @BeforeEach
    void setUp() {
        // Inicializa una entidad Auto simulada
        autoEntity = new Auto(1L, "Ford", 15000.00);
        
        // Inicializa un DTO de solicitud simulado
        autoRequestDTO = new AutoRequestDTO("Fiat", 8000.00);
    }

    // -------------------------------------------------------------------------
    // Pruebas para getAutos()
    // -------------------------------------------------------------------------

    @Test
    void getAutos_devuelveListaDeDTOResponse() {
        // Arrange
        Auto auto2 = new Auto(2L, "Chevrolet", 20000.00);
        List<Auto> listaEntidades = Arrays.asList(autoEntity, auto2);
        
        // Simular que autoRepo.findAll() devuelve nuestra lista
        when(autoRepo.findAll()).thenReturn(listaEntidades);

        // Act
        List<AutoResponseDTO> resultados = autoService.getAutos();

        // Assert
        assertNotNull(resultados, "La lista no debe ser nula.");
        assertEquals(2, resultados.size(), "Debe devolver 2 autos.");
        assertEquals("Ford", resultados.get(0).getMarca(), "El primer auto debe ser Ford.");

        // Verificar que el método findAll() del repo se llamó exactamente una vez
        verify(autoRepo, times(1)).findAll();
    }

    @Test
    void getAutos_devuelveListaVacia() {
        // Arrange
        when(autoRepo.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<AutoResponseDTO> resultados = autoService.getAutos();

        // Assert
        assertTrue(resultados.isEmpty(), "La lista debe estar vacía.");
    }

    // -------------------------------------------------------------------------
    // Pruebas para saveAuto(AutoRequestDTO autoDto)
    // -------------------------------------------------------------------------

    @Test
    void saveAuto_exito() {
        // Arrange
        // 1. Simular que la marca NO existe (findByMarca devuelve vacío)
        when(autoRepo.findByMarca(autoRequestDTO.getMarca())).thenReturn(Optional.empty());
        
        // 2. Simular que autoRepo.save() devuelve la entidad con ID
        when(autoRepo.save(any(Auto.class))).thenReturn(autoEntity);

        // Act
        AutoResponseDTO resultado = autoService.saveAuto(autoRequestDTO);

        // Assert
        assertNotNull(resultado, "El resultado no debe ser nulo.");
        assertEquals(1L, resultado.getId(), "El ID debe ser 1.");
        assertEquals("Ford", resultado.getMarca(), "La marca del auto guardado debe ser Ford (del mock de save).");

        // Verificar llamadas a mocks
        verify(autoRepo, times(1)).findByMarca(autoRequestDTO.getMarca());
        verify(autoRepo, times(1)).save(any(Auto.class));
    }

    @Test
    void saveAuto_fallaPorMarcaExistente() {
        // Arrange
        // Simular que la marca YA existe
        when(autoRepo.findByMarca(autoRequestDTO.getMarca())).thenReturn(Optional.of(autoEntity));

        // Act & Assert
        // Debe lanzar la excepción de la lógica de negocio (validarMarcaUnica)
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            autoService.saveAuto(autoRequestDTO);
        }, "Debería lanzar una excepción de argumento inválido.");

        assertTrue(thrown.getMessage().contains("ya existe la marca 'Fiat'"), "El mensaje de error debe indicar la marca duplicada.");

        // Verificar que no se llamó a save
        verify(autoRepo, times(1)).findByMarca(autoRequestDTO.getMarca());
        verify(autoRepo, never()).save(any(Auto.class));
    }
    
    @Test
    void saveAuto_fallaPorMarcaVacia() {
        // Arrange
        AutoRequestDTO dtoVacio = new AutoRequestDTO("", 10000.0);

        // Act & Assert
        // La validación está en el DTO y se llama en el Service
        assertThrows(IllegalArgumentException.class, () -> {
            autoService.saveAuto(dtoVacio);
        }, "Debería fallar por marca vacía/nula.");
        
        // Verificar que no se llamó al repositorio
        verify(autoRepo, never()).findByMarca(anyString());
        verify(autoRepo, never()).save(any(Auto.class));
    }
    
    // El setPrecio en Auto.java ya contiene la validación de precio negativo

    // -------------------------------------------------------------------------
    // Pruebas para deleteAuto(Long id)
    // -------------------------------------------------------------------------

    @Test
    void deleteAuto_exito() {
        // Arrange
        // Simular que el auto existe
        when(autoRepo.findById(1L)).thenReturn(Optional.of(autoEntity));
        // Simular que la operación de delete es exitosa
        doNothing().when(autoRepo).deleteById(1L);

        // Act & Assert
        assertDoesNotThrow(() -> autoService.deleteAuto(1L), "No debería lanzar excepción al eliminar un ID existente.");

        // Verificar que se llamó a findById y deleteById
        verify(autoRepo, times(1)).findById(1L);
        verify(autoRepo, times(1)).deleteById(1L);
    }

    @Test
    void deleteAuto_fallaPorIdNoExistente() {
        // Arrange
        // Simular que el auto NO existe
        when(autoRepo.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            autoService.deleteAuto(99L);
        }, "Debería lanzar una excepción al eliminar un ID inexistente.");

        assertTrue(thrown.getMessage().contains("No se encontró auto para el ID 99"), "El mensaje de error debe indicar el ID no encontrado.");

        // Verificar que no se llamó a deleteById
        verify(autoRepo, times(1)).findById(99L);
        verify(autoRepo, never()).deleteById(anyLong());
    }

    // -------------------------------------------------------------------------
    // Pruebas para findAuto(Long id)
    // -------------------------------------------------------------------------

    @Test
    void findAuto_exito() {
        // Arrange
        when(autoRepo.findById(1L)).thenReturn(Optional.of(autoEntity));

        // Act
        AutoResponseDTO resultado = autoService.findAuto(1L);

        // Assert
        assertNotNull(resultado, "El DTO de respuesta no debe ser nulo.");
        assertEquals(1L, resultado.getId(), "El ID debe coincidir.");
        assertEquals(15000.00, resultado.getPrecio(), 0.001, "El precio debe coincidir.");
    }

    @Test
    void findAuto_fallaPorIdNoExistente() {
        // Arrange
        when(autoRepo.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            autoService.findAuto(99L);
        }, "Debería lanzar una excepción.");

        verify(autoRepo, times(1)).findById(99L);
    }
    
    // -------------------------------------------------------------------------
    // Pruebas para editAuto(Long id, AutoRequestDTO autoDto)
    // -------------------------------------------------------------------------
    
    @Test
    void editAuto_exito_mismaMarca() {
        // Arrange
        AutoRequestDTO dtoEditado = new AutoRequestDTO("Ford", 18000.00); // Mismo nombre, nuevo precio
        
        // Simular que el auto existe
        when(autoRepo.findById(1L)).thenReturn(Optional.of(autoEntity));
        
        // Simular que el repo guarda la entidad actualizada
        when(autoRepo.save(any(Auto.class))).thenReturn(new Auto(1L, "Ford", 18000.00));
        
        // Act
        AutoResponseDTO resultado = autoService.editAuto(1L, dtoEditado);
        
        // Assert
        assertNotNull(resultado, "El resultado no debe ser nulo.");
        assertEquals(18000.00, resultado.getPrecio(), 0.001, "El precio debe haberse actualizado.");
        
        // Verificar que NO se intentó validar la unicidad de marca (pues la marca no cambió)
        verify(autoRepo, never()).findByMarca(anyString());
        // Verificar que se llamó a findById y save
        verify(autoRepo, times(1)).findById(1L);
        verify(autoRepo, times(1)).save(any(Auto.class));
    }

    @Test
    void editAuto_exito_cambioMarca() {
        // Arrange
        AutoRequestDTO dtoEditado = new AutoRequestDTO("Renault", 15000.00); // Nueva marca
        
        // Simular que el auto existe
        when(autoRepo.findById(1L)).thenReturn(Optional.of(autoEntity));
        
        // Simular que la nueva marca es única
        when(autoRepo.findByMarca("Renault")).thenReturn(Optional.empty());
        
        // Simular que el repo guarda la entidad actualizada
        when(autoRepo.save(any(Auto.class))).thenReturn(new Auto(1L, "Renault", 15000.00));
        
        // Act
        AutoResponseDTO resultado = autoService.editAuto(1L, dtoEditado);
        
        // Assert
        assertNotNull(resultado, "El resultado no debe ser nulo.");
        assertEquals("Renault", resultado.getMarca(), "La marca debe haberse actualizado.");
        
        // Verificar que SÍ se validó la unicidad de marca
        verify(autoRepo, times(1)).findByMarca("Renault");
        verify(autoRepo, times(1)).save(any(Auto.class));
    }
    
    @Test
    void editAuto_fallaPorMarcaEditadaExistente() {
        // Arrange
        AutoRequestDTO dtoEditado = new AutoRequestDTO("Chevrolet", 15000.00); 
        Auto autoExistenteChevrolet = new Auto(2L, "Chevrolet", 25000.00); // Simula otro auto ya existente

        // 1. Simular que el auto a editar existe (ID 1)
        when(autoRepo.findById(1L)).thenReturn(Optional.of(autoEntity)); 
        
        // 2. Simular que la NUEVA marca ("Chevrolet") ya existe para otro ID
        when(autoRepo.findByMarca("Chevrolet")).thenReturn(Optional.of(autoExistenteChevrolet));
        
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            autoService.editAuto(1L, dtoEditado);
        }, "Debería fallar al intentar cambiar a una marca ya existente.");

        assertTrue(thrown.getMessage().contains("ya existe la marca 'Chevrolet'"), "El mensaje de error debe ser la validación de unicidad.");

        // Verificar llamadas a mocks
        verify(autoRepo, times(1)).findById(1L);
        verify(autoRepo, times(1)).findByMarca("Chevrolet");
        verify(autoRepo, never()).save(any(Auto.class));
    }

    @Test
    void editAuto_fallaPorIdNoExistente() {
        // Arrange
        when(autoRepo.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            autoService.editAuto(99L, autoRequestDTO);
        }, "Debería lanzar una excepción si el ID no existe.");

        verify(autoRepo, times(1)).findById(99L);
        verify(autoRepo, never()).save(any(Auto.class));
    }
}