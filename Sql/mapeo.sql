CREATE DATABASE IF NOT EXISTS guatemala_express;

CREATE USER IF NOT EXISTS 'guatemala_express'@'localhost'
IDENTIFIED BY '123';

GRANT ALL PRIVILEGES ON guatemala_express.* TO 'guatemala_express'@'localhost';
FLUSH PRIVILEGES;

USE guatemala_express;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nit VARCHAR(20) NOT NULL,
    dpi VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (nit, dpi, correo_electronico)
);

INSERT INTO usuario (nit, dpi, nombre_completo, telefono, direccion, correo_electronico, contrasenia, rol) VALUES
('123456789', '1234567890123', 'Juan Perez', '555-1234', 'Zona 3 Quetzaltenango', 'juan.perez@example.com', 'password123', 'ADMINISTRADOR_SISTEMA');


CREATE TABLE sucursal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    departamento VARCHAR(255) NOT NULL,
    municipio VARCHAR(255) NOT NULL,
    longitud DECIMAL(9, 6) NOT NULL,
    latitud DECIMAL(9, 6) NOT NULL,
    telefono VARCHAR(15) NOT NULL UNIQUE,
    fecha_apertura DATE NOT NULL,
    UNIQUE (longitud, latitud)
);


CREATE TABLE administrador_sucursal (
    usuario_id INT NOT NULL,
    sucursal_id INT NOT NULL,
    PRIMARY KEY (usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE chofer (
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    fotografia LONGBLOB NOT NULL,
    numero_licencia VARCHAR(50) NOT NULL UNIQUE,
    tipo_licencia VARCHAR(50) NOT NULL,
    fecha_vencimiento_licencia DATE NOT NULL,
    salario_base_por_viaje DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id)
);

CREATE TABLE bus (
    numero_placa VARCHAR(20) PRIMARY KEY,
    id_sucursal INT NOT NULL,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    anio_fabricacion YEAR NOT NULL,
    capacidad_pasajeros INT NOT NULL,
    kilometraje_actual DECIMAL(10, 2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    fotografia LONGBLOB NOT NULL,
    estado_operativo VARCHAR(30) NOT NULL DEFAULT 'DISPONIBLE',
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id)
);

CREATE TABLE ruta_regular(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_sucursal_origen INT NOT NULL,
    id_sucursal_destino INT NOT NULL,
    distancia_aproximada_km DECIMAL(10, 2) NOT NULL,
    precio_boleto DECIMAL(10, 2) NOT NULL,
    duracion_estimada TIME NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id_sucursal_origen) REFERENCES sucursal(id),
    FOREIGN KEY (id_sucursal_destino) REFERENCES sucursal(id)
);

CREATE TABLE viaje (
    id INT PRIMARY KEY AUTO_INCREMENT,
    placa_bus VARCHAR(20) NOT NULL,
    id_chofer INT NOT NULL,
    fecha_hora_salida_programada DATETIME NOT NULL,
    fecha_hora_llegada_programada DATETIME NOT NULL,
    salario_aplicado DECIMAL(10, 2) NOT NULL,
    estado_viaje VARCHAR(50) NOT NULL DEFAULT 'PROGRAMADO',
    FOREIGN KEY (placa_bus) REFERENCES bus(numero_placa),
    FOREIGN KEY (id_chofer) REFERENCES chofer(id_usuario)
);

CREATE TABLE solicitud_alquiler(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    latitud_origen DECIMAL(9, 6) NOT NULL,
    longitud_origen DECIMAL(9, 6) NOT NULL,
    latitud_destino DECIMAL(9, 6) NOT NULL,
    longitud_destino DECIMAL(9, 6) NOT NULL,
    distancia_aproximada_km DECIMAL(10, 2) NOT NULL,
    fecha_hora_salida DATETIME NOT NULL,
    fecha_hora_retorno DATETIME,
    precio_estimado DECIMAL(10, 2) NOT NULL,
    precio_confirmado DECIMAL(10, 2),
    viaje_con_retorno BOOLEAN NOT NULL DEFAULT FALSE,
    cantidad_pasajeros INT NOT NULL,
    estado_solicitud VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    estado_pago VARCHAR(50) NOT NULL DEFAULT 'NO_PAGADO',
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id)
);

CREATE TABLE pago_alquiler(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_solicitud_alquiler INT NOT NULL UNIQUE,
    id_usuario INT NOT NULL,
    fecha_hora_pago DATETIME NOT NULL,
    monto_pagado DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_solicitud_alquiler) REFERENCES solicitud_alquiler(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);


CREATE TABLE viaje_regular (
    id_viaje INT PRIMARY KEY,
    id_ruta INT NOT NULL,
    FOREIGN KEY (id_viaje) REFERENCES viaje(id),
    FOREIGN KEY (id_ruta) REFERENCES ruta_regular(id)
);

CREATE TABLE viaje_privado (
    id_viaje INT PRIMARY KEY NOT NULL,
    id_solicitud_alquiler INT NOT NULL UNIQUE,
    FOREIGN KEY (id_viaje) REFERENCES viaje(id),
    FOREIGN KEY (id_solicitud_alquiler) REFERENCES solicitud_alquiler(id)
);

CREATE TABLE registro_salida(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_chofer INT NOT NULL,
    placa_bus VARCHAR(20) NOT NULL,
    id_viaje INT NOT NULL UNIQUE,
    fecha_hora_salida DATETIME NOT NULL,
    kilometraje_inicial DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_chofer) REFERENCES chofer(id_usuario),
    FOREIGN KEY (placa_bus) REFERENCES bus(numero_placa),
    FOREIGN KEY (id_viaje) REFERENCES viaje(id)
);

CREATE TABLE registro_llegada(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_viaje INT NOT NULL UNIQUE,
    fecha_hora_llegada DATETIME NOT NULL,
    kilometraje_final DECIMAL(10, 2) NOT NULL,
    gasto_combustible DECIMAL(10, 2) NOT NULL,
    tarifa_depreciacion_aplicada DECIMAL(10, 2) NOT NULL,
    monto_depreciacion DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_viaje) REFERENCES viaje(id)
);

CREATE TABLE configuracion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

INSERT INTO configuracion (descripcion, valor) VALUES
('Tarifa de depreciación por kilómetro', 3.00);

CREATE TABLE compra (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_viaje INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    monto_total DECIMAL(10,2) NOT NULL,
    UNIQUE (id, id_viaje),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_viaje) REFERENCES viaje(id)
);

CREATE TABLE boleto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_compra INT NOT NULL,
    id_viaje INT NOT NULL,
    numero_asiento VARCHAR(10) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    UNIQUE (id_viaje, numero_asiento),
    FOREIGN KEY (id_compra, id_viaje) REFERENCES compra(id, id_viaje)
);

CREATE TABLE recarga (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    fecha_hora_recarga DATETIME NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE gasto_taller(
    id INT PRIMARY KEY AUTO_INCREMENT,
    placa_bus VARCHAR(20) NOT NULL,
    monto_mano_de_obra DECIMAL(10, 2) NOT NULL,
    monto_repuestos DECIMAL(10, 2) NOT NULL,
    fecha_mantenimiento DATE NOT NULL,
    FOREIGN KEY (placa_bus) REFERENCES bus(numero_placa)
);
