CREATE DATABASE bolsa_empleo;
USE bolsa_empleo;
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255),
    rol VARCHAR(20) NOT NULL,
    aprobado BOOLEAN DEFAULT FALSE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE empresa (
    id_empresa INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    localizacion VARCHAR(150),
    telefono VARCHAR(20),
    descripcion TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE nacionalidad (
    id_nacionalidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);
CREATE TABLE oferente (
    id_oferente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    residencia VARCHAR(150),
    id_nacionalidad VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE administrador (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
CREATE TABLE puesto (
    id_puesto INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa INT NOT NULL,
    descripcion TEXT NOT NULL,
    salario_usd DECIMAL(10,2) NOT NULL,
    tipo_publicacion VARCHAR(20) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);
CREATE TABLE caracteristica (
    id_caracteristica INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_padre INT,
    FOREIGN KEY (id_padre) REFERENCES caracteristica(id_caracteristica)
);
CREATE TABLE oferente_habilidad (
    id_oferente INT,
    id_caracteristica INT,
    nivel INT NOT NULL,
    PRIMARY KEY (id_oferente, id_caracteristica),
    FOREIGN KEY (id_oferente) REFERENCES oferente(id_oferente),
    FOREIGN KEY (id_caracteristica) REFERENCES caracteristica(id_caracteristica)
);
CREATE TABLE puesto_caracteristica (
    id_puesto INT,
    id_caracteristica INT,
    nivel_requerido INT NOT NULL,
    PRIMARY KEY (id_puesto, id_caracteristica),
    FOREIGN KEY (id_puesto) REFERENCES puesto(id_puesto),
    FOREIGN KEY (id_caracteristica) REFERENCES caracteristica(id_caracteristica)
);
CREATE TABLE candidatura (
    id_candidatura INT AUTO_INCREMENT PRIMARY KEY,
    id_oferente INT NOT NULL,
    id_puesto INT NOT NULL,
    porcentaje_coincidencia DECIMAL(5,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_oferente) REFERENCES oferente(id_oferente),
    FOREIGN KEY (id_puesto) REFERENCES puesto(id_puesto)
);
CREATE TABLE cv (
    id_cv INT AUTO_INCREMENT PRIMARY KEY,
    id_oferente INT NOT NULL,
    ruta_archivo VARCHAR(255) NOT NULL,
    fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_oferente) REFERENCES oferente(id_oferente)
);