-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP9: Trabajo Práctico Sistema de Usuarios y Permisos
-- --------------------------

-- 1 - Crear un usuario sin privilegios específicos
CREATE USER IF NOT EXISTS 'editor'@'localhost' IDENTIFIED BY '123';

-- 2 - Crear un usuario con privilegios de lectura sobre la base pubs
CREATE USER IF NOT EXISTS 'auditor'@'localhost' IDENTIFIED BY '123';
GRANT SELECT ON pubs.* TO 'auditor'@'localhost';

-- 3 - Crear un usuario con privilegios de escritura sobre la base pubs
CREATE USER IF NOT EXISTS 'tareas-automatizadas'@'localhost' IDENTIFIED BY '123';
GRANT INSERT, UPDATE, DELETE ON pubs.* TO 'tareas-automatizadas'@'localhost';

-- 4 - Crear un usuario con todos los privilegios sobre la base pubs
CREATE USER IF NOT EXISTS 'administrador'@'localhost' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON pubs.* TO 'administrador'@'localhost' WITH GRANT OPTION;

-- 5 - Crear un usuario con privilegios de lectura sobre la tabla titles
CREATE USER IF NOT EXISTS 'titulos'@'localhost' IDENTIFIED BY '123';
GRANT SELECT ON pubs.titles TO 'titulos'@'localhost';

-- 6 - Eliminar al usuario que tiene todos los privilegios sobre la base pubs
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'administrador'@'localhost';
DROP USER 'administrador'@'localhost';

-- 7 - Eliminar a dos usuarios a la vez
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'editor'@'localhost' , 'auditor'@'localhost';
DROP USER 'editor'@'localhost' , 'auditor'@'localhost';

-- 8 - Eliminar un usuario y sus privilegios asociados
-- Ver punto 6

-- 9 - Revisar los privilegios de un usuario
SHOW GRANTS FOR 'tareas-automatizadas'@'localhost';
