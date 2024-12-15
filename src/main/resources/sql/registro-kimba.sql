-- Inserción de usuarios con roles GENERICO y ADMINISTRADOR
INSERT INTO usuarios (nombre, email, telefono, contraseña, rol) VALUES
('Carlos López', 'carlos@kimba.com', '987654321', 'hashed_password_2', 'GENERICO'),
('Ana Martínez', 'ana@kimba.com', '567890123', 'hashed_password_3', 'GENERICO'),
('Lucía Torres', 'lucia@kimba.com', '987123456', 'hashed_password_6', 'GENERICO'),
('José Pérez', 'jose@kimba.com', '789654321', 'hashed_password_8', 'ADMINISTRADOR');

-- Inserción de animales
INSERT INTO animales (nombre, tipo_animal, estado, registro) VALUES
('Max', 'PERRO', 'EN_ADOPCION', '2024-01-01'),
('Bella', 'GATO', 'APADRINADO', '2024-02-15'),
('Luna', 'PERRO', 'EN_ADOPCION', '2024-03-10'),
('Mía', 'GATO', 'ADOPTADO', '2024-03-25'),
('Rex', 'PERRO', 'EN_ADOPCION', '2024-04-01');

-- Inserción de adopciones
INSERT INTO adopciones (id_usuario, id_animal, fecha_adopcion, observaciones) VALUES
(3, 1, '2024-04-05', 'Adoptada por Carlos López'),
(2, 4, '2024-04-10', 'Adoptada por Ana Martínez');
