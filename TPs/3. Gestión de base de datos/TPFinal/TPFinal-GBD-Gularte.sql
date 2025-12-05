-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Docente: Jorge Sánchez
-- Estudiante: Pablo Gularte
-- --------------------------
-- Trabajo Práctico Final Gestión de Base de Datos
-- --------------------------
-- ------------------------------------------------------------------------------------
-- ## CONSIDERACIONES PREVIAS ##
-- Para los puntos 1 a 9 (excepto el 5) se creará y utilizará una base de datos llamada 
-- "tpf_gbd" y se creará (cuando sea necesario) una tabla para cada punto con nombre
-- "consignaX", donde "X" se corresponde con el número de consigna.
-- ------------------------------------------------------------------------------------

-- Pasos previos: Creación y selección de base de datos "tpf_gbd"
Create database tpf_gbd;
Use tpf_gbd;

-- ********* --
-- CONSIGNAS --
-- ********* --
-- 1 - Crear una función llamada "calcular_total_ventas" que tome como parámetro el mes y el año, y devuelva el total de ventas realizadas en ese mes. Verificar mediante consulta.
-- Se crea la tabla "consigna1"
Create table consigna1 (
	id int not null auto_increment primary key,
    fecha date,
    cantidad int
);

-- Se ingresan datos en la tabla
Insert consigna1 (fecha, cantidad) values 
("2025-01-15", 25),
("2025-01-25", 100),
("2025-02-01", 15),
("2025-02-05", 7);

-- Se crea la función "calcular_total_ventas"
DELIMITER //

CREATE FUNCTION calcular_total_ventas(mes INT, anio INT)
RETURNS DECIMAL(10,2)
READS SQL DATA
BEGIN
    DECLARE total DECIMAL(10,2);

    SELECT SUM(cantidad)
    INTO total
    FROM consigna1
    WHERE MONTH(fecha) = mes
      AND YEAR(fecha) = anio;

    RETURN total;
END;
//

-- Restauramos el delimitador original
DELIMITER ;

-- Consultas de verificación de funcionamiento de función
select calcular_total_ventas(1, 2025) total_ventas_enero_2025;
select calcular_total_ventas(2, 2025) total_ventas_febrero_2025;

-- 2 - Crear una función llamada "obtener_nombre_empleado" que tome como parámetro el ID de un empleado y devuelva su nombre completo. Verificar mediante consulta.
-- Se crea la tabla "consigna2"
Create table consigna2 (
	id_empleado int not null auto_increment primary key,
    nombre varchar(50),
    apellido varchar(50)
);

-- Se ingresan datos en la tabla
Insert consigna2 (nombre, apellido) values 
("Nombre 1", "Apellido 1"),
("Nombre 2", "Apellido 2"),
("Nombre 3", "Apellido 3"),
("Nombre 4", "Apellido 4");

-- Se crea la función "obtener_nombre_empleado"
DELIMITER //

CREATE FUNCTION obtener_nombre_empleado(id INT)
RETURNS varchar(100)
READS SQL DATA
BEGIN
    DECLARE nombre_completo varchar(100);
    
    SELECT concat(nombre, " ", apellido)
    INTO nombre_completo
    FROM consigna2
    WHERE id_empleado = id;

    RETURN nombre_completo;
END;
//

-- Restauramos el delimitador original
DELIMITER ;

-- Consultas de verificación de funcionamiento de función
select obtener_nombre_empleado(1) nombre_completo_empleado_1;
select obtener_nombre_empleado(2) nombre_completo_empleado_2;


-- 3 - Crear un procedimiento almacenado llamado "obtener_promedio" que tome como parámetro de entrada el nombre de un curso y calcule el promedio de las calificaciones de todos los alumnos inscriptos en ese curso. Verificar mediante ejecución del procedimiento.
-- Se crea la tabla "consigna3"
Create table consigna3 (
	nombre_curso varchar(50),
    estudiante varchar(50),
    nota int
);

-- Se ingresan datos en la tabla
Insert consigna3 (nombre_curso, estudiante, nota) values 
("Curso 1", "Estudiante 1", 7),
("Curso 1", "Estudiante 2", 8),
("Curso 2", "Estudiante 1", 9),
("Curso 2", "Estudiante 2", 10);

-- Se crea el procedimeinto "obtener_promedio"
DELIMITER //

CREATE PROCEDURE obtener_promedio(
    IN nombre VARCHAR(100), -- Parámetro de ENTRADA: Nombre del curso
    OUT promedio DECIMAL(4, 2)    -- Parámetro de SALIDA: Promedio calculado
)
BEGIN
    SELECT AVG(nota)
    INTO promedio
    FROM consigna3
    WHERE nombre_curso = nombre;
END;
//

-- Restauramos el delimitador original
DELIMITER ;

-- Consultas de verificación de funcionamiento del procedimiento
-- Se define variable apra recibir el valor del parámetro de salida del procedimiento
set @promedio=0;

-- Se verifica elpromedio de "Curso 1"
call obtener_promedio("curso 1", @promedio);
Select @promedio promedio_notas_curso_1;

-- Se verifica elpromedio de "Curso 2"
call obtener_promedio("curso 2", @promedio);
Select @promedio promedio_notas_curso_2;

-- 4 - Crear un procedimiento almacenado "actualizar_stock" que tome como parámetros de entrada el código del producto y la cantidad a agregar al stock actual. El procedimiento debe actualizar el stock sumando la cantidad especificada al stock actual del producto correspondiente. Verificar mediante ejecución del procedimiento.
-- Se crea la tabla "consigna4"
Create table consigna4 (
	codigo_producto varchar(50),
    stock int
);

-- Se ingresan datos en la tabla
Insert consigna4 values 
("Producto 1", 7),
("Producto 2", 8),
("Producto 3", 9),
("Producto 4", 10);

-- Se crea el procedimeinto "actualizar_stock"
DELIMITER //

CREATE PROCEDURE actualizar_stock(
    IN producto VARCHAR(50),
    in cantidad int
)
BEGIN
    UPDATE consigna4
       SET stock = stock + cantidad
    WHERE codigo_producto = producto;
END;
//

-- Restauramos el delimitador original
DELIMITER ;

-- Consultas de verificación de funcionamiento del procedimiento
-- Se verifica el stock de "Producto 1"
call actualizar_stock("Producto 1", 10);
Select stock from consigna4 where codigo_producto = "Producto 1";

-- Si falla la ejecución del procedimiento por tener activada la variable "sql_safe_updates", descomentar la línea de abajo y volver a invocar el porcedimiento y la consulta de verificación
-- SET sql_safe_updates = "OFF";


-- 5 - Crear una vista que muestre el título, el autor, el precio y la editorial de todos los libros de cocina de la base pubs.
create view consigna5 as
Select t.title titulo, concat(a.au_fname, " ", a.au_lname) nombre, t.price precio, p.pub_name editorial
  From pubs.titles t
      ,pubs.titleauthor ta
      ,pubs.authors a
      ,pubs.publishers p
Where t.type like "%cook"
  And ta.title_id = t.title_id
  And a.au_id = ta.au_id
  And p.pub_id = t.pub_id;

-- 6 – Dadas las siguientes tablas:
CREATE TABLE fabricantes (
    id_fabricante INT PRIMARY KEY,
    nombre_fabricante VARCHAR(255) NOT NULL
);

INSERT INTO fabricantes (id_fabricante, nombre_fabricante)
VALUES(1, 'Fabricante A'),(2, 'Fabricante B'),(3, 'Fabricante C');

CREATE TABLE productos (
    id_producto INT PRIMARY KEY,
    id_fabricante INT,
    nombre_producto VARCHAR(255) NOT NULL,
    fecha_lanzamiento DATE,
    FOREIGN KEY (id_fabricante) REFERENCES fabricantes(id_fabricante)
);

INSERT INTO productos (id_producto, id_fabricante, nombre_producto, fecha_lanzamiento)
VALUES(1, 1, 'Producto X', '2020-01-01'),(2, 2, 'Producto Y', '2019-12-01'), (3, 3, 'Producto Z', '2021-05-15'); 

--  a) Crear un índice compuesto en las columnas id_fabricante y nombre_producto de la tabla productos.
Create index indice_compuesto_consigna_6 on productos(id_fabricante, nombre_producto);

-- b) Crear un índice único en la columna id_producto de la tabla productos.
Create unique index indice_unico_consigna_6 on productos(id_producto);


--  c) Modificar el índice idx_productos_id_fabricante_nombre para que sea  único en la columna id_fabricante.
-- Dado que no sabemos en qué tabla está definido el índice "idx_productos_id_fabricante_nombre" debemos guiarnos por la indicación que
-- menciona a la columna "id_fabricante" como parte de dicho índice. Esta columna está presente en ambas tablas. Por lo tanto, el índice
-- mencionado puede corresponder a cualquiera de las dos tablas. Analicemos ambos casos.

-- Índice "idx_productos_id_fabricante_nombre" sobre tabla "fabricantes". En este caso, dado que la columna "id_fabricante" está definida
-- como clave primaria, los valores de la columna serán siempre únicos. Por lo tanto, el índice "idx_productos_id_fabricante_nombre" único sobre
-- la columna "id_fabricante" de la tabla "fabricantes" sería redundante y rechazado por el motor de la base de datos, ya que no permite crear
-- un índice único sobre la misma columna definida como clave primaria.

-- Indice "idx_productos_id_fabricante_nombre" sobre la tabla "productos". En este caso, definir un índice único sobre la columna "fabricante_id"
-- de la tabla "productos" implica un cambio de regla de negocio ya que establece que la relación ente fabricante y producto deja de ser 1:N
-- (un fabricante, muchos productos) para pasar a ser 1:1 (un fabricante, un solo producto). Esta redefinición no parece corresponderse con los
-- modelos de negocios habituales o al menos fuerza una relación que, de ser necesaria, podría resolverse modificando la estructira de la tabla
-- "productos". Si es necesario definir sobre la columna "fabricante_id" un índice único, la columna "producto_id" no sólo deja de ser necesaria 
-- sino que pasa a ser innecesaria por redundante. Por lo tanto, se puede eliminar dicha columna de la tabla "productos" ya que su función de unicidad
-- la pasa a cumplir la columna "fabricante_id", que funcionará a la vez como clave primaria de hecho de la tabla "productos" y clave foránea de la tabla
-- "fabricantes", garantizando así la consistencia de relación y la integridad de los datos entre ambas tablas. Para poder aplicar este índice, primero se
-- debe eliminar el índice asociado a la clave foránea generada en tiempo de creación de la tabla "productos" sobrfe la columna "id_fabricante". Para ello
-- se debe recuperaer el nombre del índice asociado (consultando la tabla TABLE_CONSTRAINTS del esquema INFORMATION_SCHEMA), eliminarlo y crear el índice
-- único. Luego de esto, es recomendable regenerar la clave foranea para mantener la integridad referencial.

--  d) Crear un nuevo índice único en la columna id_fabricante
-- En este caso volvemos sobre los dos escenarios planteados en el punto anterior.

--  e) Eliminar el índice idx_productos_id_fabricante de la tabla productos
ALTER TABLE productos DROP INDEX IF EXISTS idx_productos_id_fabricante;

-- 7 -  Se desea modificar un sistema de gestión de empleados para incluir  un mecanismo automático que transfiera a los empleados que cumplen con ciertos criterios de jubilación a una tabla especializada llamada jubilados. 
--  Los criterios de jubilación son: los empleados deben tener 30 años o más de antigüedad y 65 años o más de edad. Además, se requiere que cualquier inserción en la tabla empleados que cumpla con estos criterios resulte en una inserción automática en la tabla jubilados.
CREATE TABLE empleados (
  nombre VARCHAR(50) NOT NULL,
  edad INT NOT NULL,
  antiguedad INT NOT NULL
);


CREATE TABLE jubilados (
  nombre VARCHAR(50) NOT NULL,
  edad INT NOT NULL,
  antiguedad INT NOT NULL
);

-- 8 - Crear un procedimiento almacenado llamado ActualizarEmpleados que tome dos  parámetros de entrada:
-- codigo_empleado (VARCHAR, 10): El identificador del empleado a actualizar.
-- salario_actualizado (DECIMAL): El nuevo salario del empleado.
-- En el procedimiento, utilizar una transacción para realizar la actualización del salario del empleado:
-- Obtener la información actual del empleado especificado.
-- Verificar si el nuevo salario es válido (no puede ser menor que el salario actual).
-- Si el salario es válido, realizar la actualización del salario del empleado.
-- Si el salario actualizado fuere menor que el salario actual, mostrar un mensaje al usuario indicando que la operación se cancela y realizar un rollback.
-- Llamar al procedimiento ActualizarEmpleados con diferentes valores de codigo_empleado y salario_actualizado, incluyendo casos donde el salario actualizado sea menor que el salario actual.
-- Verificar que el procedimiento funcione correctamente y que se muestren mensajes de error y se realice un rollback cuando corresponda.


-- 9 - Gestión de Usuarios

-- a) Crear un usuario sin privilegios específicos
-- b) Crear un usuario con privilegios de lectura sobre la base pubs
-- c) Crear un usuario con privilegios de escritura sobre la base pubs
-- d) Crear un usuario con todos los privilegios sobre la base pubs
-- e) Crear un usuario con privilegios de lectura sobre la tabla titles
-- f) Eliminar al usuario que tiene todos los privilegios sobre la base pubs
-- g) Eliminar a dos usuarios a la vez
-- h) Eliminar un usuario y sus privilegios asociados
-- i) Revisar los privilegios de un usuario


-- 10 – Gestor Mongo DB

-- a) Activar la base de datos "local" y luego imprimir las colecciones existentes.
-- b) Activar la base de datos "test" y luego imprimir las colecciones existentes.
-- c) Activar la base de datos "baseEjemplo2".
-- d) Mostrar las colecciones existentes en la base de datos "baseEjemplo2".
-- e) Crear otra colección llamada usuarios donde almacenar dos documentos con los 
-- campos nombre y clave.
-- f) Mostrar nuevamente las colecciones existentes en la base de datos "baseEjemplo2".

-- En la base pubs:
-- g) Insertar 2 documentos en la colección clientes con '_id' no repetidos
-- h) Intentar insertar otro documento con clave repetida.
-- i) Mostrar todos los documentos de la colección libros.

-- j) Crear una base de datos llamada "blog".
-- k) Agregar una colección llamada "posts" e insertar 1 documento con una estructura a 
-- su elección.
-- l) Mostrar todas las bases de datos actuales.
-- m) Eliminar la colección "posts"
-- n) Eliminar la base de datos "blog" y mostrar las bases de datos existentes.

-- 11 - A partir de la siguiente especificación deberá recolectar datos para poder diseñar una Base de Datos.

-- a) Determinar las entidades relevantes al Sistema.
-- b) Determinar los atributos de cada entidad.
-- c) Confeccionar el Diagrama de Entidad Relación (DER), junto al Diccionario de Datos
-- d) Realizar el Diagrama de Tablas e implementar en código SQL (puede utilizar cualquier Gestor) la Base de Datos.
-- e) Crear al menos 2 consultas relacionadas para poder probar la Base de Datos.


-- Esta empresa se encuentra ubicada en Hong Kong y se dedica a la fabricación de Smart TV.

-- Las componentes de los TV pueden ser comprados a un importador, en tal caso la compra viene acompañada de una orden, otros componentes son fabricados en la empresa, para lo cual esos componentes tienen asignado un empleado que se dedica exclusivamente a un tipo de componente, aunque un componente puede ser fabricado por más de un empleado, el empleado completa una hoja de trabajo con la cantidad fabricada y la fecha.

-- Los diferentes modelos de Smart TV tienen de 275 a 430 componentes, aunque un componente puede estar incorporado en más de un TV, existe un mapa de armado para cada modelo de TV donde se indica la ubicación y el orden de los componentes.
