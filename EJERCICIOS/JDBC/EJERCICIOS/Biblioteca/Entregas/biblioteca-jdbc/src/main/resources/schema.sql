-- noinspection SqlNoDataSourceInspectionForFile

-- Crear la tabla de autores
CREATE TABLE Autor (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL
);

-- Crear la tabla de libros con un campo de fecha de publicación
CREATE TABLE Libro (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      titulo VARCHAR(255) NOT NULL,
                      autor_id BIGINT,
                      fecha_publicacion DATE,  -- Nuevo campo para la fecha de
    -- publicación
                      FOREIGN KEY (autor_id) REFERENCES autor(id) ON DELETE CASCADE
);

-- Insertar algunos datos de ejemplo en la tabla de autores
INSERT INTO autor (nombre) VALUES ('Gabriel García Márquez');
INSERT INTO autor (nombre) VALUES ('Julio Cortázar');

-- Insertar algunos libros asociados a los autores con fechas de publicación
INSERT INTO libro(titulo, autor_id, fecha_publicacion) VALUES ('Cien años de ' ||
                                                         'soledad', 1, DATE '1967-06-05');
INSERT INTO libro (titulo, autor_id, fecha_publicacion) VALUES ('El amor en los tiempos del cólera', 1, DATE '1985-04-05');
INSERT INTO libro (titulo, autor_id, fecha_publicacion) VALUES ('Rayuela', 2, DATE '1963-06-28');
INSERT INTO libro (titulo, autor_id, fecha_publicacion) VALUES ('La vuelta al día en ochenta mundos', 2, DATE '1970-10-01');