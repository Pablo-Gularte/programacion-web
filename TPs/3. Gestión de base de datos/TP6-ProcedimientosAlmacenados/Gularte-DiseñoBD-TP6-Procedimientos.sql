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
DELIMITER //
CREATE PROCEDURE TotalVentasPorProducto()
BEGIN
    SELECT 
        p.nombre, 
        SUM(v.cantidad) total_vendido
    FROM productos p
    INNER JOIN ventas v ON p.id = v.producto_id
    GROUP BY p.nombre
    ORDER BY total_vendido DESC;
END //
DELIMITER ;

-- Ejemplo de uso
CALL TotalVentasPorProducto();

-- 2. Procedimiento que actualiza el stock de un producto y devuelve su nuevo valor
DELIMITER //
CREATE PROCEDURE actualizarStockYDevolverNuevoValor(
    IN producto_id_in INT, 
    IN cantidad_a_ajustar INT, 
    OUT nuevo_stock_out INT
)
BEGIN
    -- 1. Actualiza el stock
    UPDATE productos
    SET stock = stock + cantidad_a_ajustar
    WHERE id = producto_id_in;
    
    -- 2. Devuelve el nuevo stock
    SELECT stock INTO nuevo_stock_out
    FROM productos
    WHERE id = producto_id_in;
END //
DELIMITER ;

-- Ejemplo de uso (Asume que el stock inicial de 'Laptop' (id=1) es 10. Se insertan 10 más. Quedan 20 en stock_nuevo)
SET @nuevo_stock = 0;
CALL actualizarStockYDevolverNuevoValor(1, 10, @nuevo_stock); 
SELECT @nuevo_stock stock_nuevo;

-- 3. Procedimiento que muestra la lista de productos con un stock menor a cierto valor
DELIMITER //
CREATE PROCEDURE productosConStockBajo(
    IN stock_limite INT
)
BEGIN
    SELECT id, nombre, stock
    FROM productos
    WHERE stock < stock_limite
    ORDER BY stock ASC;
END //
DELIMITER ;

-- Ejemplo de uso
CALL productosConStockBajo(10);

-- 4. Procedimiento que muestra el nombre y la cantidad de compras de un cliente en un rango de fechas
DELIMITER //
CREATE PROCEDURE comprasClienteRangoFechas(
    IN cliente_id_in INT,
    IN fecha_inicio DATE, 
    IN fecha_fin DATE
)
BEGIN
    SELECT c.nombre, COUNT(v.id) total_compras
    FROM clientes c
    INNER JOIN ventas v ON c.id = v.cliente_id
    WHERE c.id = cliente_id_in
	AND v.fecha BETWEEN fecha_inicio AND fecha_fin
    GROUP BY c.nombre;
END //
DELIMITER ;

-- Ejemplo de uso
CALL comprasClienteRangoFechas(1, '2022-01-01', '2022-01-31');

-- 5. Procedimiento que muestra el promedio de precio de los productos comprados por un cliente
DELIMITER //
CREATE PROCEDURE promedioPrecioProductosComprados(
    IN cliente_id_in INT
)
BEGIN
    SELECT c.nombre cliente, AVG(p.precio) promedio
    FROM clientes c
    INNER JOIN ventas v ON c.id = v.cliente_id
    INNER JOIN productos p ON v.producto_id = p.id
    WHERE c.id = cliente_id_in
    GROUP BY c.nombre;
END //
DELIMITER ;

-- Ejemplo de uso
CALL promedioPrecioProductosComprados(1);

-- 6. Procedimiento que muestra la lista de clientes que han comprado un producto en particular
DELIMITER //
CREATE PROCEDURE clientesCompraronProducto(
    IN producto_id_in INT
)
BEGIN
    SELECT DISTINCT c.nombre, c.direccion, c.telefono
    FROM clientes c
    INNER JOIN ventas v ON c.id = v.cliente_id
    WHERE v.producto_id = producto_id_in
    ORDER BY c.nombre;
END //
DELIMITER ;

-- Ejemplo de uso
CALL clientesCompraronProducto(1);


-- 7. Procedimiento que actualiza el precio de un producto y devuelve su nuevo valor
DELIMITER //
CREATE PROCEDURE actualizarPrecioYDevolverNuevoValor(
    IN producto_id_in INT, 
    IN nuevo_precio DECIMAL(10,2),
    OUT nuevo_precio_out DECIMAL(10,2)
)
BEGIN
    -- 1. Actualiza el precio
    UPDATE productos
    SET precio = nuevo_precio
    WHERE id = producto_id_in;
    
    -- 2. Devuelve el nuevo precio
    SELECT precio INTO nuevo_precio_out
    FROM productos
    WHERE id = producto_id_in;
END //
DELIMITER ;

-- Ejemplo de uso
SET @nuevo_p = 0.00;
CALL actualizarPrecioYDevolverNuevoValor(1, 1600.50, @nuevo_p);
SELECT @nuevo_p nuevo_precio;

-- 8. Procedimiento que muestra el nombre y el stock de los productos que se han vendido en un rango de fechas
DELIMITER //
CREATE PROCEDURE productosVendidosEnRango(
    IN fecha_inicio DATE, 
    IN fecha_fin DATE
)
BEGIN
    SELECT DISTINCT p.nombre, p.stock
    FROM productos p
    INNER JOIN ventas v ON p.id = v.producto_id
    WHERE v.fecha BETWEEN fecha_inicio AND fecha_fin
    ORDER BY p.nombre;
END //
DELIMITER ;

-- Ejemplo de uso
CALL productosVendidosEnRango('2022-01-01', '2022-01-05');


-- 9. Procedimiento que muestra el total de ventas por cliente
DELIMITER //
CREATE PROCEDURE totalVentasPorCliente()
BEGIN
    SELECT c.nombre, SUM(v.cantidad * p.precio) total
    FROM clientes c
    INNER JOIN ventas v ON c.id = v.cliente_id
    INNER JOIN productos p ON v.producto_id = p.id
    GROUP BY c.nombre
    ORDER BY total DESC;
END //
DELIMITER ;

-- Ejemplo de uso
CALL totalVentasPorCliente();

-- 10. Procedimiento que muestra la lista de productos ordenada por precio de mayor a menor
DELIMITER //
CREATE PROCEDURE productosOrdenadosPorPrecioDesc()
BEGIN
    SELECT id, nombre, precio, stock
    FROM productos
    ORDER BY precio DESC;
END //
DELIMITER ;

-- Ejemplo de uso
CALL productosOrdenadosPorPrecioDesc();