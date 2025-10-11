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
        T.ytd_sales * T.price * (T.royalty / 100.0) * (TA.royaltyper / 100.0)
    ) INTO regalia_total
    FROM titles AS T
    JOIN titleauthor AS TA ON T.title_id = TA.title_id
    WHERE TA.au_id = autor_id
      AND T.price IS NOT NULL
      AND T.ytd_sales IS NOT NULL;

    -- Si no hay ventas o el autor no existe, la regalía es 0.00
    RETURN IFNULL(regalia_total, 0.00);
END //

DELIMITER ;

-- Prueba de función:
SELECT CalcularRegaliaTotalAutor(172) regalias_autor;

-- Ejercicio 2: Crear una función para obtener el precio máximo de cada tipo de libro.
DELIMITER //

CREATE FUNCTION ObtenerPrecioMaximoPorTipo (tipo_libro CHAR(12))
RETURNS FLOAT
READS SQL DATA
BEGIN
    DECLARE precio_max FLOAT;

    SELECT MAX(price) INTO precio_max
    FROM titles
    WHERE type = tipo_libro;

	-- Si no hay precio definido o el tipo indicado no existe, el precio máximo es 0.00
    RETURN IFNULL(precio_max, 0.00);
END //

DELIMITER ;

-- Prueba de función:
SELECT ObtenerPrecioMaximoPorTipo('psychology') precioMaximo;

-- Ejercicio 3: Crear una función para calcular el ingreso (cantidad vendida multiplicada por el precio) de cada título.
DELIMITER //

CREATE FUNCTION CalcularIngresoPorTitulo (titulo_id INT)
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
    SELECT SUM(S.qty * precio_titulo) INTO ingreso_total
    FROM sales s
    WHERE s.title_id = titulo_id;

    -- Si el precio es NULL o no hay ventas, el ingreso es 0.00
    RETURN IFNULL(ingreso_total, 0.00);
END //

DELIMITER ;

-- Prueba de función:
SELECT CalcularIngresoPorTitulo(7) ingresoPorTitulo;

-- Ejercicio 4: Crear una función para obtener el nombre completo de un empleado a partir de su código.
DELIMITER //

CREATE FUNCTION ObtenerNombreCompletoEmpleado (empleado_id INT)
RETURNS VARCHAR(100)
READS SQL DATA
BEGIN
    DECLARE nombre_completo VARCHAR(100);

    SELECT 
        -- Concatenar Nombre (fname), Inicial (minit) si existe, y Apellido (lname)
        CONCAT(
            E.fname, 
            ' ',
            IF(E.minit IS NULL OR E.minit = '', '', CONCAT(E.minit, '. ')), 
            E.lname
        ) INTO nombre_completo
    FROM employee AS E
    WHERE E.emp_id = empleado_id;

	-- Si no existe el empleado buscado, se devuelve leyenda "Empleado no encontrado"
    RETURN IFNULL(nombre_completo, 'Empleado no encontrado');
END //

DELIMITER ;

-- Prueba de función:
SELECT ObtenerNombreCompletoEmpleado(1) nombreCompleto;

-- Ejercicio 5: Crear una función para calcular el precio promedio de libros publicados de cada editorial.
DELIMITER //

CREATE FUNCTION CalcularPrecioPromedioPorEditorial (editorial_id CHAR(4))
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

-- Prueba de función:
SELECT CalcularPrecioPromedioPorEditorial('0736') precioPromedio;