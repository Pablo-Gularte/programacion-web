-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP3: Trabajo Práctico Vistas
-- --------------------------
-- Ejercicio 1: Crea una vista que muestre el título, el autor y el precio de todos los libros de la tabla titles.
-- Ejercicio 2: Crea una vista que muestre el título, el autor, el precio y el tipo de todos los libros de la tabla titles.
-- Ejercicio 3: Crea una vista que muestre el título, el autor, el precio y la fecha de publicación de todos los libros de la tabla titles.
-- Ejercicio 4: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales.
-- Ejercicio 5: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda.
-- Ejercicio 6: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tipo de libro.
-- Ejercicio 7: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda y tipo de libro.
-- Ejercicio 8: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda y año de publicación.
-- Ejercicio 9: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación.
-- Ejercicio 10: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro, año de publicación y mes de publicación.

-- Vistas que tienen condiciones con la cláusula WHERE:
-- Ejercicio 11: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que 
-- 				superaron los 10 libros.
-- Ejercicio 12: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que 
-- 				se realizaron en el año 1990.
-- Ejercicio 13: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se
-- 				realizaron entre 1990 y 1994.
-- Ejercicio 14: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se
-- 				realizaron en la tienda con el ID 7066.
-- Ejercicio 15: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se 
-- 				realizaron por el autor con el ID 172.

-- Actualización de datos en tablas mediante la actualización de vistas:
-- Ejercicio 16: Crea una vista que permita actualizar el precio de un libro en la tabla titles.
-- Ejercicio 17: Crea una vista que permita actualizar el nombre de un autor en la tabla authors.
-- Ejercicio 18: Crea una vista que permita actualizar la cantidad vendida de un libro en la tabla sales.
-- Ejercicio 19: Crea una vista que permita actualizar la fecha de publicación de un libro en la tabla titles.
-- Ejercicio 20: Crea una vista que permita actualizar el tipo de un libro en la tabla titles.

-- Desarrollo
use pubs;

-- Ejercicio 1
-- CREATE VIEW vista_ejercicio1 AS
SELECT 
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) nombre,
    t.price precio
FROM
    titles t
        JOIN
    titleauthor ta ON ta.title_id = t.title_id
        JOIN
    authors a ON a.au_id = ta.au_id;

-- Ejercicio 2
-- CREATE VIEW vista_ejercicio2 AS
SELECT 
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    t.type AS tipo
FROM
    titles t
        JOIN
    titleauthor ta ON t.title_id = ta.title_id
        JOIN
    authors a ON ta.au_id = a.au_id;
    
-- Ejercicio 3
-- CREATE VIEW vista_ejercicio3 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    t.pubdate AS publicacion
FROM titles t
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 4
-- CREATE VIEW vista_ejercicio4 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 5
-- CREATE VIEW vista_ejercicio5 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    s.stor_id AS id_tienda
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 6
-- CREATE VIEW vista_ejercicio6 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    t.type AS tipo
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 7
-- CREATE VIEW vista_ejercicio7 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    s.stor_id AS id_tienda,
    t.type AS tipo
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 8
-- CREATE VIEW vista_ejercicio8 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    s.stor_id AS id_tienda,
    YEAR(t.pubdate) AS anio_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 9
-- CREATE VIEW vista_ejercicio9 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    s.stor_id AS id_tienda,
    t.type AS tipo,
    YEAR(t.pubdate) AS anio_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 10
-- CREATE VIEW vista_ejercicio10 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) AS nombre,
    t.price precio,
    s.qty AS cantidad,
    s.stor_id AS id_tienda,
    t.type AS tipo,
    YEAR(t.pubdate) AS anio_publicacion,
    month(t.pubdate) AS mes_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 11
-- CREATE VIEW vista_ejercicio11 AS
SELECT
    t.title,
    CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
    t.price,
    s.qty AS Cantidad_Vendida,
    t.type AS Tipo_Libro
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE YEAR(s.ord_date) = 1990;

-- Ejercicio 12
-- Ejercicio 13
-- Ejercicio 14
-- Ejercicio 15
-- Ejercicio 16
-- Ejercicio 17
-- Ejercicio 18
-- Ejercicio 19
-- Ejercicio 20


-- GEMINI: https://gemini.google.com/app/cf466799ab51194d
-- -- Ejercicio 1: Título, autor y precio.
-- CREATE VIEW Vista_Ej1_Libros_Autor_Precio AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price
-- FROM titles t
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 2: Título, autor, precio y tipo.
-- CREATE VIEW Vista_Ej2_Libros_Detalle AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     t.type AS Tipo_Libro
-- FROM titles t
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 3: Título, autor, precio y fecha de publicación.
-- CREATE VIEW Vista_Ej3_Libros_FechaPub AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     t.pubdate AS Fecha_Publicacion
-- FROM titles t
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 4: Título, autor, precio y cantidad vendida (de todas las ventas).
-- CREATE VIEW Vista_Ej4_Libros_Ventas AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 5: ...por cada tienda (incluye ID_Tienda).
-- CREATE VIEW Vista_Ej5_Ventas_Por_Tienda AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 6: ...por cada tipo de libro (incluye Tipo_Libro).
-- CREATE VIEW Vista_Ej6_Ventas_Por_Tipo AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     t.type AS Tipo_Libro
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 7: ...por cada tienda y tipo de libro.
-- CREATE VIEW Vista_Ej7_Ventas_Tienda_Tipo AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 8: ...por cada tienda y año de publicación (del libro).
-- CREATE VIEW Vista_Ej8_Ventas_Tienda_AnioPub AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 9: ...por cada tienda, tipo de libro y año de publicación.
-- CREATE VIEW Vista_Ej9_Ventas_Tienda_Tipo_AnioPub AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id;

-- -- Ejercicio 10: ...solo para ventas realizadas en el año 1990.
-- CREATE VIEW Vista_Ej10_Ventas_1990_Por_Tienda AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE YEAR(s.ord_date) = 1990;

-- -- Ejercicio 11: ...solo para ventas realizadas en el año 1990, por tipo de libro.
-- CREATE VIEW Vista_Ej11_Ventas_1990_Por_Tipo AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     t.type AS Tipo_Libro
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE YEAR(s.ord_date) = 1990;

-- -- Ejercicio 12: ...solo para ventas realizadas en el año 1990, agrupado por tienda, tipo y año de publicación.
-- CREATE VIEW Vista_Ej12_Ventas_Agrupadas_1990 AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE YEAR(s.ord_date) = 1990;

-- -- Ejercicio 13: ...solo para ventas realizadas entre 1990 y 1994, agrupado por tienda, tipo y año de publicación.
-- CREATE VIEW Vista_Ej13_Ventas_Agrupadas_1990_1994 AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE YEAR(s.ord_date) BETWEEN 1990 AND 1994;

-- -- Ejercicio 14: ...solo para ventas realizadas en la tienda con ID 7066, agrupado por tienda, tipo y año de publicación.
-- CREATE VIEW Vista_Ej14_Ventas_Tienda_7066 AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE s.stor_id = '7066';

-- -- Ejercicio 15: ...solo para ventas realizadas por el autor con ID 172, agrupado por tienda, tipo y año de publicación.
-- CREATE VIEW Vista_Ej15_Ventas_Autor_172 AS
-- SELECT
--     t.title,
--     CONCAT(a.au_fname, ' ', a.au_lname) AS Nombre_Autor,
--     t.price,
--     s.qty AS Cantidad_Vendida,
--     s.stor_id AS ID_Tienda,
--     t.type AS Tipo_Libro,
--     YEAR(t.pubdate) AS Anio_Publicacion
-- FROM sales s
-- JOIN titles t ON s.title_id = t.title_id
-- JOIN titleauthor ta ON t.title_id = ta.title_id
-- JOIN authors a ON ta.au_id = a.au_id
-- WHERE a.au_id = 172;

