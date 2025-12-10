CREATE DATABASE FabricaSmartTV;
USE FabricaSmartTV;

-- 1. Tabla de Componentes
CREATE TABLE Componente (
    id_componente INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(100),
    tipo ENUM('Comprado', 'Fabricado')
);

-- 2. Tabla de Smart TVs
CREATE TABLE SmartTV (
    id_tv INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(50),
    pulgadas INT
);

-- 3. Tabla Mapa de Armado (Relación N:M entre TV y Componente)
CREATE TABLE Mapa_Armado (
    id_tv INT,
    id_componente INT,
    orden_armado INT,
    ubicacion VARCHAR(50),
    PRIMARY KEY (id_tv, id_componente),
    FOREIGN KEY (id_tv) REFERENCES SmartTV(id_tv),
    FOREIGN KEY (id_componente) REFERENCES Componente(id_componente)
);

-- 4. Tabla Importadores
CREATE TABLE Importador (
    id_importador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100)
);

-- 5. Tabla Órdenes de Compra (Para componentes importados)
CREATE TABLE Orden_Compra (
    nro_orden INT PRIMARY KEY AUTO_INCREMENT,
    id_importador INT,
    id_componente INT,
    fecha DATE,
    cantidad INT,
    FOREIGN KEY (id_importador) REFERENCES Importador(id_importador),
    FOREIGN KEY (id_componente) REFERENCES Componente(id_componente)
);

-- 6. Tabla Empleados
CREATE TABLE Empleado (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    especialidad VARCHAR(50)
);

-- 7. Tabla Hoja de Trabajo (Para componentes fabricados internamente)
CREATE TABLE Hoja_Trabajo (
    nro_hoja INT PRIMARY KEY AUTO_INCREMENT,
    id_empleado INT,
    id_componente INT,
    fecha DATE,
    cantidad INT,
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    FOREIGN KEY (id_componente) REFERENCES Componente(id_componente)
);