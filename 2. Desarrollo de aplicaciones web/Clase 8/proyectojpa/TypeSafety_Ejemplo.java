// ===== EJEMPLO DE TYPE SAFETY EN QUERY METHODS =====

public interface IPersonaRepository extends JpaRepository<Persona, Long> {
    
    // ✅ TYPE SAFE: El compilador sabe exactamente qué esperar
    Optional<Persona> findByNombre(String nombre);           // String → Optional<Persona>
    List<Persona> findByEdad(int edad);                      // int → List<Persona>  
    boolean existsByNombre(String nombre);                   // String → boolean
    long countByEdad(int edad);                              // int → long
    
    // ✅ El compilador verifica AUTOMÁTICAMENTE:
    // - Que 'nombre' existe en la entidad Persona
    // - Que el parámetro sea del tipo correcto (String)
    // - Que el retorno sea del tipo esperado
}

// ===== USO EN EL SERVICIO =====

@Service  
public class PersonaService {
    
    @Autowired
    private IPersonaRepository repository;
    
    public PersonaResponseDTO buscarPorNombre(String nombre) {
        // ✅ COMPILADOR VERIFICA:
        // - El método existe
        // - Pasas String (correcto)
        // - Recibes Optional<Persona> (correcto)
        Optional<Persona> persona = repository.findByNombre(nombre);
        
        // ✅ COMPILADOR VERIFICA que persona.isPresent() devuelve boolean
        if (persona.isPresent()) {
            return PersonaMapper.toResponseDTO(persona.get());
        }
        throw new PersonaNotFoundException("Persona no encontrada");
    }
    
    public List<PersonaResponseDTO> buscarMayoresDe(int edad) {
        // ✅ COMPILADOR VERIFICA todo el flujo de tipos
        List<Persona> personas = repository.findByEdadGreaterThan(edad);
        return personas.stream()  // Stream<Persona>
                .map(PersonaMapper::toResponseDTO)  // Stream<PersonaResponseDTO>  
                .collect(Collectors.toList());      // List<PersonaResponseDTO>
    }
    
    // ❌ ERRORES QUE EL COMPILADOR DETECTA ANTES DE EJECUTAR:
    /*
    public void ejemplosDeErrores() {
        // Error: Método no existe
        repository.findByNombreCompleto("Juan");  // ❌ No existe esta propiedad
        
        // Error: Tipo incorrecto
        repository.findByEdad("25");              // ❌ Esperaba int, pasaste String
        
        // Error: Asignación incorrecta  
        String resultado = repository.findByNombre("Juan");  // ❌ Esperaba Optional<Persona>
        
        // Error: Llamada a método inexistente
        Optional<Persona> p = repository.findByNombre("Juan");
        p.getPersona();  // ❌ Optional no tiene método getPersona()
    }
    */
}

// ===== VENTAJAS DEL TYPE SAFETY =====

/*
1. 🛡️ DETECCIÓN TEMPRANA DE ERRORES
   - Errores encontrados al compilar, no al ejecutar
   - Evita fallos en producción

2. 🔧 REFACTORING SEGURO  
   - Si cambias el nombre de una propiedad en Persona
   - El compilador te dice dónde actualizar los Query Methods

3. 📚 AUTOCOMPLETADO INTELIGENTE
   - El IDE sugiere métodos válidos
   - Muestra tipos de parámetros y retorno

4. 📖 CÓDIGO AUTODOCUMENTADO
   - Los tipos explican qué hace cada método
   - Menos documentación necesaria

5. 🧪 TESTING MÁS FÁCIL
   - Mocks type-safe
   - Verificaciones automáticas de tipos
*/