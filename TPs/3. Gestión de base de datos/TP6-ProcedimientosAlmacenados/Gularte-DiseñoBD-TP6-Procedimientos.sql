-- Curso: Gestión de Base de Datos - Turno noche
-- Tramo: Especialización Profesional en Programación Web
-- Sede: SMATA - CFP8 - GCBA
-- Estudiante: Pablo Gularte
-- --------------------------
-- TP5: Trabajo Práctico sobre Procedimientos Almacenados
-- --------------------------

create database tp_procedimientos;
use tp_procedimientos;

CREATE TABLE clientes (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
direccion VARCHAR(100) NOT NULL,
telefono VARCHAR(20) NOT NULL
);

CREATE TABLE productos (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(255) DEFAULT '',
precio DECIMAL(10,2) NOT NULL,
stock INT DEFAULT 0
);

CREATE TABLE ventas (
id INT AUTO_INCREMENT PRIMARY KEY,
cliente_id INT NOT NULL,
producto_id INT NOT NULL,
cantidad INT NOT NULL,
fecha DATE NOT NULL,
FOREIGN KEY (cliente_id) REFERENCES clientes(id),
FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Insercion de registros
INSERT INTO clientes (nombre, direccion, telefono) VALUES
('Juan Pérez', 'Calle Falsa 123', '555-1234'),
('María García', 'Avenida Siempreviva 742', '555-5678'),
('Pedro González', 'Calle 13 No. 6-11', '555-9101'),
('Ana Hernández', 'Carrera 7 No. 32-60', '555-1212'),
('Luisa Rodríguez', 'Avenida Boyacá No. 64C-31', '555-1415'),
('Carlos Vargas', 'Carrera 15 No. 93-75', '555-1617'),
('Cristina Gómez', 'Carrera 45 No. 34-87', '555-1819'),
('Javier Torres', 'Calle 45 No. 23-09', '555-2022'),
('Laura Sánchez', 'Avenida 68 No. 56-18', '555-2225'),
('Andrés Díaz', 'Carrera 7 No. 11-07', '555-2428');

INSERT INTO productos (nombre, descripcion, precio, stock)
VALUES ('Laptop', 'Laptop HP 15", 8GB RAM, 1TB HDD', 1500.00, 10),
('Smartphone', 'Smartphone Samsung Galaxy S21', 1000.00, 15),
('Tablet', 'Tablet Apple iPad Pro 12.9"', 1200.00, 5),
('Monitor', 'Monitor LG 27", 1440p', 500.00, 20),
('Teclado', 'Teclado mecánico Logitech G513', 100.00, 30),
('Mouse', 'Mouse inalámbrico Logitech M720', 50.00, 25),
('Auriculares', 'Auriculares Sony WH-1000XM4', 300.00, 10),
('Altavoces', 'Altavoces Bose SoundLink Revolve+', 250.00, 8),
('Cámara', 'Cámara Canon EOS R5', 4000.00, 2),
('Impresora', 'Impresora multifunción HP LaserJet Pro M428fdw', 600.00, 5);

INSERT INTO ventas (cliente_id, producto_id, cantidad, fecha) VALUES
(1, 1, 5, '2022-01-01'),
(1, 2, 3, '2022-01-02'),
(2, 3, 2, '2022-01-03'),
(2, 1, 1, '2022-01-04'),
(3, 2, 4, '2022-01-05'),
(3, 3, 1, '2022-01-06'),
(4, 1, 3, '2022-01-07'),
(4, 2, 2, '2022-01-08'),
(5, 3, 6, '2022-01-09'),
(5, 1, 2, '2022-01-10');

select * from clientes;
select * from productos;
select * from ventas;

-- 1. Procedimiento que muestra el total de ventas por producto
-- 2. Procedimiento que actualiza el stock de un producto y devuelve su nuevo valor
-- 3. Procedimiento que muestra la lista de productos con un stock menor a cierto valor
-- 4. Procedimiento que muestra el nombre y la cantidad de compras de un cliente en un rango de fechas
-- 5. Procedimiento que muestra el promedio de precio de los productos comprados por un cliente
-- 6. Procedimiento que muestra la lista de clientes que han comprado un producto en particular
-- 7. Procedimiento que actualiza el precio de un producto y devuelve su nuevo valor
-- 8. Procedimiento que muestra el nombre y el stock de los productos que se han vendido en un rango de fechas
-- 9. Procedimiento que muestra el total de ventas por cliente
-- 10. Procedimiento que muestra la lista de productos ordenada por precio de mayor a menor