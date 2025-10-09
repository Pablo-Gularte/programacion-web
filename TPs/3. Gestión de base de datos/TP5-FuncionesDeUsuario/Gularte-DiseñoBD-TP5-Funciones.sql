-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP5: Trabajo Práctico sobre Funciones de Usuario
-- --------------------------
USE pubs;

-- Ejercicio 1: Crear una función para calcular la regalía total de cada autor.
DELIMITER //

CREATE FUNCTION CalcularRegaliaTotalAutor (autor_id INT)
RETURNS DECIMAL(10, 2)
READS SQL DATA
BEGIN
    DECLARE regalia_total DECIMAL(10, 2);

    SELECT SUM(
        t.ytd_sales * t.price * (t.royalty / 100.0) * (ta.royaltyper / 100.0)
    ) INTO regalia_total
    FROM titles t
    JOIN titleauthor ta ON t.title_id = ta.title_id
    WHERE ta.au_id = autor_id
      AND t.price IS NOT NULL
      AND t.ytd_sales IS NOT NULL;

    -- Si no hay ventas o el autor no existe, la regalía es 0.00
    RETURN IFNULL(regalia_total, 0.00);
END //

DELIMITER ;

-- Ejemplo de uso:
SELECT CalcularRegaliaTotalAutor(172) regalias;

-- Ejercicio 2: Crear una función para obtener el precio máximo de cada tipo de libro.
DELIMITER //

CREATE FUNCTION PrecioMaximoTipoLibro (tipo_libro CHAR(12))
RETURNS FLOAT
READS SQL DATA
BEGIN
    DECLARE precio_max FLOAT;

    SELECT MAX(price) INTO precio_max
    FROM titles
    WHERE type = tipo_libro;

    RETURN precio_max;
END //

DELIMITER ;

-- Ejemplo de uso:
SELECT PrecioMaximoTipoLibro('psychology') precio_maximo;

-- Ejercicio 3: Crear una función para calcular el ingreso (cantidad vendida multiplicada por el precio) de cada título.
DELIMITER //

CREATE FUNCTION ingresoPorTitulo (titulo_id INT)
RETURNS DECIMAL(10, 2)
READS SQL DATA
BEGIN
    DECLARE ingreso_total DECIMAL(10, 2);
    DECLARE precio_titulo FLOAT;

    -- 1. Obtener el precio del título
    SELECT price INTO precio_titulo
    FROM titles
    WHERE title_id = titulo_id;

    -- 2. Calcular el ingreso total (Precio * SUM(cantidad vendida))
    SELECT SUM(qty * precio_titulo) INTO ingreso_total
    FROM sales
    WHERE title_id = titulo_id;

    -- Si el precio es NULL o no hay ventas, el ingreso es 0.00
    RETURN IFNULL(ingreso_total, 0.00);
END //

DELIMITER ;

-- Ejemplo de uso:
SELECT ingresoPorTitulo (7) AS ingreso_titulo;

-- Ejercicio 4: Crear una función para obtener el nombre completo de un empleado a partir de su código.
DELIMITER //

CREATE FUNCTION nombreCompletoEmpleado (empleado_id INT)
RETURNS VARCHAR(100)
READS SQL DATA
BEGIN
    DECLARE nombre_completo VARCHAR(100);

    SELECT 
        -- Concatenar Nombre (fname), Inicial (minit) si existe, y Apellido (lname)
        CONCAT(
            fname, 
            ' ',
            IF(minit IS NULL OR minit = '', '', CONCAT(minit, '. ')), 
            lname
        ) INTO nombre_completo
    FROM employee
    WHERE emp_id = empleado_id;

    RETURN IFNULL(nombre_completo, 'Empleado no encontrado');
END //

DELIMITER ;

-- Ejemplo de uso:
SELECT nombreCompletoEmpleado(1) nombre_completo;

-- Ejercicio 5: Crear una función para calcular el precio promedio de libros publicados de cada editorial.
DELIMITER //

CREATE FUNCTION PrecioPromedioPorEditorial (editorial_id CHAR(4))
RETURNS FLOAT
READS SQL DATA
BEGIN
    DECLARE precio_promedio FLOAT;

    SELECT AVG(price) INTO precio_promedio
    FROM titles
    WHERE pub_id = editorial_id;

    RETURN precio_promedio;
END //

DELIMITER ;

-- Ejemplo de uso:
SELECT PrecioPromedioPorEditorial('0736') precio_promedio;