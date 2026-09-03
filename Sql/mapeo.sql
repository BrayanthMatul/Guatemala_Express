CREATE DATABASE guatemala_express;

CREATE USER 'guatemala_express'@'localhost' IDENTIFIED BY '123';

GRANT ALL PRIVILEGES ON guatemala_express.* TO 'guatemala_express'@'localhost';
FLUSH PRIVILEGES;

USE guatemala_express;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nit VARCHAR(20) NOT NULL UNIQUE,
    dpi VARCHAR(20) NOT NULL UNIQUE,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO usuario (nit, dpi, nombre_completo, telefono, direccion, correo_electronico, contrasenia, rol) VALUES
('123456789', '1234567890123', 'Juan Perez', '555-1234', 'Zona 3 Quetzaltenango', 'juan.perez@example.com', 'password123', 'ADMINSTRADOR_SISTEMA');


CREATE TABLE sucursal (
    
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    departamento VARCHAR(255) NOT NULL,
    municipio VARCHAR(255) NOT NULL,
    longitud DECIMAL(9, 6) NOT NULL UNIQUE,
    latitud DECIMAL(9, 6) NOT NULL UNIQUE,
    telefono VARCHAR(15) NOT NULL UNIQUE,
    fecha_apertura DATE NOT NULL,
);


CREATE TABLE administrador_sucursal (
    PRIMARY KEY (usuario_id, sucursal_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE chofer (
    id_sucursal INT NOT NULL,
    fotografia LONGBLOB,
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
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio_fabricacion DATE NOT NULL,
    capacidad_pasajeros INT NOT NULL,
    kilometraje_actual INT NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id)
);

CREATE TABLE ruta_regular(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_sucursal_origen VARCHAR(255) NOT NULL,
    id_sucursal_destino VARCHAR(255) NOT NULL,
    distancia_aproximada_km INT NOT NULL,
    precio_boleto DECIMAL(10, 2) NOT NULL,
    duracion_estimada TIME NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE viaje (
    id INT PRIMARY KEY AUTO_INCREMENT,
    placa_bus VARCHAR(50) NOT NULL,
    id_chofer INT NOT NULL,
    fecha_hora_salida_programada VARCHAR(20) NOT NULL,
    fecha_hora_llegada_programada DATETIME NOT NULL,
    salario_aplicada DATETIME NOT NULL,
    estado_viaje VARCHAR(50) NOT NULL DEFAULT TRUE,
    FOREIGN KEY (placa_bus) REFERENCES bus(numero_placa),
    FOREIGN KEY (id_chofer) REFERENCES chofer(id_usuario),
);

CREATE TABLE solicitud_alquiler(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    latitud_origen DECIMAL(9, 6) NOT NULL,
    longitud_origen DECIMAL(9, 6) NOT NULL,
    latitud_destino DECIMAL(9, 6) NOT NULL,
    longitud_destino DECIMAL(9, 6) NOT NULL,
    fecha_hora_salida DATETIME NOT NULL,
    fecha_hora_retorno DATETIME NOT NULL,
    precio_estimado DECIMAL(10, 2) NOT NULL,
    precio_confirmado DECIMAL(10, 2) NOT NULL,
    estado_solicitud VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    estado_pago VARCHAR(50) NOT NULL DEFAULT 'NO_PAGADO',
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id)
);


CREATE TABLE viaje_regular (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_viaje INT NOT NULL,
    id_ruta_regular INT NOT NULL,
    FOREIGN KEY (id_viaje) REFERENCES viaje(id),
    FOREIGN KEY (id_ruta_regular) REFERENCES ruta_regular(id)
);