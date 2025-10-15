-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP7: Trabajo Práctico sobre Triggers
-- --------------------------
-- [ CONSIGNAS ]
-- Crea una base de datos llamada testDisparador que contenga una tabla llamada alumnos con las siguientes columnas.
-- Tabla alumnos:
-- • id (entero sin signo)
-- • nombre (cadena de caracteres)
-- • apellido (cadena de caracteres)
-- • nota (número real)

-- Una vez creada la tabla escriba dos triggers con las siguientes características:

-- Trigger 1: trigger_check_nota_before_insert
-- Se ejecuta sobre la tabla alumnos.
-- Se ejecuta antes de una operación de inserción.
-- Si el nuevo valor de la nota que se quiere insertar es negativo, se guarda como 0.
-- Si el nuevo valor de la nota que se quiere insertar es mayor que 10, se guarda como 10.

-- Trigger2 : trigger_check_nota_before_update
-- Se ejecuta sobre la tabla alumnos.
-- Se ejecuta antes de una operación de actualización.
-- Si el nuevo valor de la nota que se quiere actualizar es negativo, se guarda como 0.
-- Si el nuevo valor de la nota que se quiere actualizar es mayor que 10, se guarda como 10.
-- Una vez creados los triggers escriba 3 sentencias de inserción y actualización sobre la tabla alumnos y verifica que los triggers se están ejecutando correctamente.

-- [ DESARROLLO ]
CREATE DATABASE IF NOT EXISTS testDisparador;
use testDisparador;

-- Creo la tabla "alumnos" 
CREATE TABLE alumnos (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nota DECIMAL(4, 2) -- Número real para la nota, con 2 decimales
);

-- Creo el trigger_check_nota_before_insert
delimiter //
CREATE TRIGGER  trigger_check_nota_before_insert
 BEFORE INSERT ON alumnos FOR EACH ROW 
    BEGIN 
        -- Si la nota es negativa, la establece en 0
		IF NEW.nota < 0 THEN
			SET NEW.nota = 0;
		-- Si la nota es mayor que 10, la establece en 10
		ELSEIF NEW.nota > 10 THEN
			SET NEW.nota = 10;
		END IF;
    END // 
delimiter ;

-- Creo el trigger_check_nota_before_update
delimiter //
CREATE TRIGGER  trigger_check_nota_before_update
 BEFORE UPDATE ON alumnos FOR EACH ROW 
    BEGIN 
		-- Si la nota es negativa, la establece en 0
		IF NEW.nota < 0 THEN
			SET NEW.nota = 0;
		-- Si la nota es mayor que 10, la establece en 10
		ELSEIF NEW.nota > 10 THEN
			SET NEW.nota = 10;
		END IF;
    END // 
delimiter ;

-- DMLs de prueba de los triggers mediante el uso de INSERT
-- Inserción 1: debería insertarse sin cambios
INSERT INTO alumnos (nombre, apellido, nota) VALUES ('Rosa', 'López', 5.5);

-- Inserción 2: debería insertarse con valor 0 en la nota
INSERT INTO alumnos (nombre, apellido, nota) VALUES ('Juan', 'Pérez', -2);

-- Inserción 3: debería insertarse con valor 10 en la nota
INSERT INTO alumnos (nombre, apellido, nota) VALUES ('Maria', 'Alonso', 10.1);

-- Visualizo los registros insertados
Select * from alumnos;

-- DMLs de prueba de los triggers mediante el uso de UPDATE
-- Actualización 1: debería actualizarse con valor 6 en la nota
UPDATE alumnos SET nota = 6.00 WHERE id = 1;

-- Actualización 2: debería actualizarse con valor 0 en la nota
UPDATE alumnos SET nota = -5.00 WHERE id = 2;

-- Actualización 3: debería actualizarse con valor 10 en la nota
UPDATE alumnos SET nota = 15.00 WHERE id = 3;

-- Visualizo los registros actualizados
Select * from alumnos;