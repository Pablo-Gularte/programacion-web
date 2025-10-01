-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP4: Trabajo Practico sobre Índices
-- --------------------------
-- Ejercicio 1: Crea un índice compuesto en las columnas id_editorial y titulo de la tabla libros.
CREATE INDEX idx_libros_editorial_titulo ON libros (id_editorial, titulo);

-- Ejercicio 2: Crea un índice en la columna fecha_publicacion de la tabla libros.
CREATE INDEX idx_libros_fecha_publicacion ON libros (fecha_publicacion);

-- Ejercicio 3: Elimina el índice idx_libros_id_editorial_titulo de la tabla libros.
DROP INDEX idx_libros_editorial_titulo ON libros;

-- Ejercicio 4: Actualiza el índice idx_libros_id_editorial_titulo de la tabla libros para que sea un índice único en la columna id_editorial.
CREATE UNIQUE INDEX idx_libros_id_editorial_unique ON libros (id_editorial);

-- Ejercicio 5: ¿Se puede usar alter para resolver el ejercicio anterior?
ALTER TABLE libros ADD UNIQUE INDEX idx_libros_id_editorial_unique (id_editorial);

-- Ejercicio 6: Crea un índice único en la columna id_editorial de la tabla editoriales.
CREATE UNIQUE INDEX idx_editoriales_id_editorial_unique ON editoriales (id_editorial);

-- Ejercicio 7: Crea un índice primary en la columna id_libro de la tabla libros.
-- This would only be used if the PRIMARY KEY wasn't already defined in the CREATE TABLE statement.
ALTER TABLE libros ADD PRIMARY KEY (id_libro);