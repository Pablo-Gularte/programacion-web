-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP4: Trabajo Practico sobre Índices
-- --------------------------
create database editoriales;
use editoriales;

CREATE TABLE editoriales (
id_editorial INT PRIMARY KEY,
nombre_editorial VARCHAR(255) NOT NULL
);

INSERT INTO editoriales (id_editorial, nombre_editorial)
VALUES
(1, 'Editorial Santillana'),
(2, 'Editorial Anagrama'),
(3, 'Editorial Planeta'),
(4, 'Editorial Alfaguara'),
(5, 'Editorial SM'),
(6, 'Editorial Penguin Random House'),
(7, 'Editorial Norma'),
(8, 'Editorial Ediciones B'),
(9, 'Editorial Aguilar'),
(10, 'Editorial Fondo de Cultura Económica');

CREATE TABLE libros (
id_libro INT PRIMARY KEY,
id_editorial INT,
titulo VARCHAR(255) NOT NULL,
fecha_publicacion DATE,
FOREIGN KEY (id_editorial) REFERENCES editoriales(id_editorial)
);

INSERT INTO libros (id_libro, id_editorial, titulo, fecha_publicacion)
VALUES
(1, 1, 'Cien años de soledad', '1967-05-30'),
(2, 2, 'Rayuela', '1963-07-23'),
(3, 3, 'La sombra del viento', '2001-04-27'),
(4, 4, 'Pedro Páramo', '1955-11-30'),
(5, 5, 'Don Quijote de la Mancha', '1605-01-16'),
(6, 6, 'Harry Potter y la piedra filosofal', '1997-06-26'),
(7, 7, 'Crimen y castigo', '1866-01-29'),
(8, 8, 'Los detectives salvajes', '1998-09-01'),
(9, 9, 'La casa de los espíritus', '1982-01-01'),
(10, 10, 'Ficciones', '1944-05-01');

select * from editoriales;
select * from libros;

show index from editoriales;
show index from libros;

-- Ejercicio 1: Crea un índice compuesto en las columnas id_editorial y titulo de la tabla libros.
CREATE INDEX idx_libros_id_editorial_titulo 
ON libros (id_editorial, titulo);

-- Ejercicio 2: Crea un índice en la columna fecha_publicacion de la tabla libros.
CREATE INDEX idx_libros_fecha_publicacion 
ON libros (fecha_publicacion);

-- Ejercicio 3: Elimina el índice idx_libros_id_editorial_titulo de la tabla libros.
-- Este ejercicio lanza un mensaje de error porque la columna "id_editorial" está definida como clave foránea de la tabla "editoriales" y también forma parte del índice compuesto
-- que se intenta eliminar. Para eliminar el índice compuesto se puede deshabilitar temporalmente la verificación de claves foráneas, eliminar el índice y luego volver a habilitarla 
-- con los siguientes comandos:
SET foreign_key_checks = 0;
DROP INDEX idx_libros_id_editorial_titulo ON libros;
SET foreign_key_checks = 1;

-- Ejercicio 4: Actualiza el índice idx_libros_id_editorial_titulo de la tabla libros para que sea un índice único en la columna id_editorial.
-- Este ejercicio no se puede realizar porque el índice "idx_libros_id_editorial_titulo" ya no existe, se eliminó en el ejercicio 3.

-- Ejercicio 5: ¿Se puede usar alter para resolver el ejercicio anterior?
-- Se puede utilizar el comando "ALTER TABLE" para agregar un nuevo índice a la tabla "libro" con las características pedidas. Una posible implementación sería la siguiente:
ALTER TABLE libros
ADD CONSTRAINT uq_libros_id_editorial 
UNIQUE (id_editorial);

-- Ejercicio 6: Crea un índice único en la columna id_editorial de la tabla editoriales.
-- Este ejercicio ya está resuelto porque la tabla "editoriales" ya posee una clave primaria en la columna "id_editorial", que fue definida en tiempo de creación de la tabla.

-- Ejercicio 7: Crea un índice primary en la columna id_libro de la tabla libros.
-- Este ejercicio ya está resuelto porque la tabla "libros" ya posee una clave primaria en la columna "id_libro", que fue definida en tiempo de creación de la tabla.
