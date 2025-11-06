# Proyecto JPA OneToOne Unidireccional

Este proyecto demuestra la implementación de una relación **OneToOne Unidireccional** entre las entidades `Auto` y `Motor` usando Spring Boot y JPA.

## 🔴 Características de la Relación Unidireccional

- **Solo Auto conoce a Motor**
- **Motor NO conoce a Auto**
- **Navegación en una sola dirección: Auto → Motor**
- **Se crea una Foreign Key `id_motor` en la tabla `auto`**

## 📋 Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Configuración

1. **Crear base de datos:**
   ```sql
   CREATE DATABASE jpa_relaciones_unidireccional;
   ```

2. **Configurar `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/jpa_relaciones_unidireccional
   spring.datasource.username=root
   spring.datasource.password=1234
   ```

3. **Ejecutar el proyecto:**
   ```bash
   mvn spring-boot:run
   ```

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional/
│   │       ├── models/
│   │       │   ├── Auto.java      ✅ (Conoce a Motor)
│   │       │   └── Motor.java     ❌ (NO conoce a Auto)
│   │       ├── repositories/
│   │       ├── services/
│   │       └── controllers/
│   └── resources/
│       └── application.properties
```

## 📊 Esquema de Base de Datos

```sql
-- Tabla motor (independiente)
CREATE TABLE motor (
    id_motor BIGINT AUTO_INCREMENT PRIMARY KEY,
    cilindrada INT NOT NULL,
    marca_motor VARCHAR(255) NOT NULL
);

-- Tabla auto (con FK hacia motor)
CREATE TABLE auto (
    id_auto BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_motor BIGINT,
    FOREIGN KEY (id_motor) REFERENCES motor(id_motor)
);
```

## 🔧 Endpoints API

### Motores
- `GET /api/motores` - Listar todos los motores
- `GET /api/motores/{id}` - Obtener motor por ID
- `POST /api/motores` - Crear motor
- `PUT /api/motores/{id}` - Actualizar motor
- `DELETE /api/motores/{id}` - Eliminar motor

### Autos
- `GET /api/autos` - Listar todos los autos
- `GET /api/autos/{id}` - Obtener auto por ID
- `GET /api/autos/por-marca-motor?marcaMotor=Honda` - Buscar autos por marca de motor
- `GET /api/autos/por-cilindrada?cilindrada=1600` - Buscar autos por cilindrada
- `POST /api/autos` - Crear auto
- `PUT /api/autos/{id}` - Actualizar auto
- `DELETE /api/autos/{id}` - Eliminar auto

## 🧪 Ejemplos de Uso

### Crear un Motor:
```bash
curl -X POST http://localhost:8080/api/motores \
  -H "Content-Type: application/json" \
  -d '{
    "cilindrada": 1600,
    "marcaMotor": "Honda"
  }'
```

### Crear un Auto con Motor:
```bash
curl -X POST http://localhost:8080/api/autos \
  -H "Content-Type: application/json" \
  -d '{
    "marca": "Honda",
    "modelo": "Civic",
    "precio": 25000.00,
    "motor": {
      "idMotor": 1
    }
  }'
```

### Buscar Autos por Marca de Motor:
```bash
curl "http://localhost:8080/api/autos/por-marca-motor?marcaMotor=Honda"
```

## ⚠️ Limitaciones de la Relación Unidireccional

1. **No puedes navegar desde Motor a Auto**
2. **Todas las consultas deben partir desde Auto**
3. **No puedes hacer queries como: "Dame todos los autos de este motor"**
4. **Motor es independiente y no sabe que existe Auto**

## 🆚 Diferencias con Relación Bidireccional

| Aspecto | Unidireccional | Bidireccional |
|---------|----------------|---------------|
| Navegación | Solo Auto → Motor | Auto ↔ Motor |
| Consultas | Limitadas desde Auto | Flexibles desde ambos |
| Complejidad | Baja | Media |
| equals/hashCode | Opcional | Obligatorio |

## 🎯 Cuándo usar Relación Unidireccional

- ✅ Relaciones simples de consulta
- ✅ Cuando solo necesitas navegar en una dirección
- ✅ Entidades independientes
- ✅ Menos complejidad de código

## 📝 Notas Técnicas

- **FetchType.EAGER**: Motor se carga automáticamente con Auto
- **CascadeType.PERSIST**: Al guardar Auto, se guarda Motor automáticamente
- **equals() y hashCode()**: Implementados por buenas prácticas, no obligatorios