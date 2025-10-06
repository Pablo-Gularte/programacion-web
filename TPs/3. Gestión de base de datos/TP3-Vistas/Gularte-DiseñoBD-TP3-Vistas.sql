-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP3: Trabajo Práctico Vistas
-- --------------------------
-- Desarrollo
use pubs;

-- Ejercicio 1: Crea una vista que muestre el título, el autor y el precio de todos los libros de la tabla titles.
CREATE VIEW vista_ejercicio1 AS
SELECT 
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio
FROM titles t
JOIN titleauthor ta ON ta.title_id = t.title_id
JOIN authors a ON a.au_id = ta.au_id;

-- Ejercicio 2: Crea una vista que muestre el título, el autor, el precio y el tipo de todos los libros de la tabla titles.
CREATE VIEW vista_ejercicio2 AS
SELECT 
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    t.type tipo
FROM titles t
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;
    
-- Ejercicio 3: Crea una vista que muestre el título, el autor, el precio y la fecha de publicación de todos los libros de la tabla titles.
CREATE VIEW vista_ejercicio3 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    t.pubdate publicacion
FROM titles t
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 4: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales.
CREATE VIEW vista_ejercicio4 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id;

-- Ejercicio 5: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda.
CREATE VIEW vista_ejercicio5 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    s.stor_id id_tienda
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY s.stor_id;

-- Ejercicio 6: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tipo de libro.
CREATE VIEW vista_ejercicio6 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    t.type tipo
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY t.type;

-- Ejercicio 7: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda y tipo de libro.
CREATE VIEW vista_ejercicio7 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    s.stor_id id_tienda,
    t.type tipo
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY s.stor_id, t.type;

-- Ejercicio 8: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda y año de publicación.
CREATE VIEW vista_ejercicio8 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    s.stor_id id_tienda,
    YEAR(t.pubdate) anio_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY id_tienda, anio_publicacion;

-- Ejercicio 9: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación.
CREATE VIEW vista_ejercicio9 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    s.stor_id id_tienda,
    t.type tipo,
    YEAR(t.pubdate) anio_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY id_tienda, tipo, anio_publicacion;

-- Ejercicio 10: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro, año de publicación y mes de publicación.
CREATE VIEW vista_ejercicio10 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad,
    s.stor_id id_tienda,
    t.type tipo,
    YEAR(t.pubdate) anio_publicacion,
    month(t.pubdate) mes_publicacion
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
ORDER BY id_tienda, tipo, anio_publicacion, mes_publicacion;

-- Vistas que tienen condiciones con la cláusula WHERE:
-- Ejercicio 11: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que 
-- 				superaron los 10 libros.
CREATE VIEW vista_ejercicio11 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE s.qty > 10
ORDER BY s.stor_id, t.type, YEAR(t.pubdate);

-- Ejercicio 12: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que 
-- 				se realizaron en el año 1990.
CREATE VIEW vista_ejercicio12 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE year(s.ord_date) = "1990"
ORDER BY s.stor_id, t.type, YEAR(t.pubdate);


-- Ejercicio 13: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se
-- 				realizaron entre 1990 y 1994.
CREATE VIEW vista_ejercicio13 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE year(s.ord_date) between "1990" and "1994"
ORDER BY s.stor_id, t.type, YEAR(t.pubdate);

-- Ejercicio 14: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se
-- 				realizaron en la tienda con el ID 7066.
CREATE VIEW vista_ejercicio14 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE s.stor_id = 7066
ORDER BY s.stor_id, t.type, YEAR(t.pubdate);

-- Ejercicio 15: Crea una vista que muestre el título, el autor, el precio y la cantidad vendida de todos los libros de la tabla sales por cada tienda, tipo de libro y año de publicación, pero solo para las ventas que se 
-- 				realizaron por el autor con el ID 172.
CREATE VIEW vista_ejercicio15 AS
SELECT
    t.title titulo,
    CONCAT(a.au_fname, ' ', a.au_lname) autor,
    t.price precio,
    s.qty cantidad
FROM sales s
JOIN titles t ON s.title_id = t.title_id
JOIN titleauthor ta ON t.title_id = ta.title_id
JOIN authors a ON ta.au_id = a.au_id
WHERE a.au_id = 172
ORDER BY s.stor_id, t.type, YEAR(t.pubdate);


-- Actualización de datos en tablas mediante la actualización de vistas:
-- Ejercicio 16: Crea una vista que permita actualizar el precio de un libro en la tabla titles.
CREATE VIEW vista_ejercicio16 AS
SELECT
	t.title_id,
    t.price
FROM titles t
WHERE t.title is not null;

-- Ejercicio 17: Crea una vista que permita actualizar el nombre de un autor en la tabla authors.
CREATE VIEW vista_ejercicio17 AS
SELECT
	a.au_id,
    a.au_fname,
    a.au_lname
FROM authors a
WHERE a.au_id is not null;

-- Ejercicio 18: Crea una vista que permita actualizar la cantidad vendida de un libro en la tabla sales.
CREATE VIEW vista_ejercicio18 AS
SELECT
	s.title_id,
    s.qty
FROM sales s
WHERE s.title_id is not null;

-- Ejercicio 19: Crea una vista que permita actualizar la fecha de publicación de un libro en la tabla titles.
CREATE VIEW vista_ejercicio19 AS
SELECT
	t.title_id,
    t.pubdate
FROM titles t
WHERE t.title_id is not null;

-- Ejercicio 20: Crea una vista que permita actualizar el tipo de un libro en la tabla titles.
CREATE VIEW vista_ejercicio20 AS
SELECT
	t.title_id,
    t.type
FROM titles t
WHERE t.title_id is not null;