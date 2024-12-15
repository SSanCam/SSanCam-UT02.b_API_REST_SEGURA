-- Insertar datos en la tabla Usuarios
INSERT INTO usuarios (nombre, email, telefono, contraseña, rol) VALUES
('Sara Sánchez', 'sara@kimba.com', '123456789', 'hashed_password_1', 'ADMINISTRADOR'),
('Carlos López', 'carlos@kimba.com', '987654321', 'hashed_password_2', 'GENERICO'),
('Ana Martínez', 'ana@kimba.com', '567890123', 'hashed_password_3', 'GENERICO');

-- Insertar datos en la tabla Animales (solo tipo gato y perro)
INSERT INTO animales (nombre, tipo_animal, estado, registro) VALUES
('Max', 'perro', 'en adopción', '2024-01-01'),
('Bella', 'gato', 'apadrinado', '2024-02-15'),
('Luna', 'perro', 'en adopción', '2024-03-10'),
('Mía', 'gato', 'adoptado', '2024-03-25');

-- Insertar datos en la tabla Adopciones
INSERT INTO adopciones (id_usuario, id_animal, fecha_adopcion, observaciones) VALUES
(3, 1, '2024-04-05', 'Adoptada por Carlos López'),
(2, 4, '2024-04-10', 'Adoptada por Ana Martínez');