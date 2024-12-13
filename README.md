# Refugio Kimba

### Desarrollo web en Entorno Servidor
### UT02-b Spring Boot | Proyecto – API REST Segura
### __Sara Sánchez Camilleri.__

<hr>
<br>
<br>

a. Nombre del proyecto.
Refugio Kimba

Aplicación web y móvil para la gestión de una protectora de animales.

# b. Idea del Proyecto.

## API REST para una protectora de animales

Con vistas al Trabajo de Fin de Curso (TFC), este proyecto será una práctica que puede evolucionar para convertirse en una herramienta funcional o incluso en una parte del proyecto final.

La idea es desarrollar una aplicación web para una protectora de animales, cuyo objetivo principal será facilitar la gestión de las instalaciones y los animales en busca de adopción, además de promover la participación de voluntarios.

# c. Justificación del proyecto.

La gestión de protectoras de animales es una labor que requiere organización, dedicación y recursos limitados. Sin embargo, en la mayoría de los casos, estos centros dependen de procesos manuales, hojas de cálculo o herramientas básicas para registrar información sobre los animales, adopciones, voluntarios y donaciones.

Esta situación genera varios problemas:

- Falta de eficiencia: La información desorganizada puede retrasar las adopciones y el seguimiento de los animales.
- Dificultades de comunicación: La falta de centralización dificulta la coordinación entre los voluntarios y el personal.
- Pérdida de oportunidades: Sin un sistema adecuado, es más difícil captar padrinos, donantes y adoptantes.

Con este proyecto, buscamos ofrecer una solución práctica y eficiente mediante una aplicación web y móvil que permita:

1. Centralizar toda la información del refugio en un sistema digital seguro.
2. Optimizar los procesos internos, como la gestión de adopciones y apadrinamientos.
3. Mejorar la experiencia de los adoptantes, voluntarios y padrinos al proporcionarles acceso a información actualizada y personalizada.
4. Fortalecer la misión del refugio al facilitar la promoción de los animales en busca de hogar y la captación de apoyo.

<hr>
<br>
<br>

# d. Descripción detallada de las entidades

## 1. Planteamiento de las entidades

### Entidades básicas

Para garantizar un mínimo viable funcional, el proyecto trabajará inicialmente con las siguientes entidades fundamentales:

- Usuarios: Representarán a las personas que interactúan con el sistema. Los usuarios estarán clasificados en distintos roles según sus responsabilidades y actividades: administrador, voluntario, padrino, usuario regular, entre otros.
  - Roles básicos: administrador y usuario genérico.
  - Conforme se amplíen las funcionalidades, se irán incrementando los roles necesarios.


- Animales: Contendrá la información detallada de todos los animales registrados por la protectora, incluyendo datos básicos, estado de salud y disponibilidad para adopción o apadrinamiento.


- Adopciones: Gestionará y registrará la información relacionada con las adopciones realizadas, permitiendo un seguimiento detallado de los animales que han salido del refugio hacia un hogar permanente.

### Ampliación en entidades:

Conforme avance el desarrollo del proyecto, se integrarán nuevas entidades para ampliar las funcionalidades y proporcionar herramientas adicionales tanto para los usuarios como para el personal del centro. Estas futuras incorporaciones están diseñadas para mejorar la experiencia de usuario y optimizar los procesos internos del refugio. Las entidades adicionales son las siguientes:

- Apadrinamiento: Registro de animales apadrinados, asociándolos con usuarios responsables y facilitando la comunicación personalizada con los padrinos.

- Cita Veterinaria: Registro detallado de citas veterinarias asignadas a cada animal mientras permanezcan bajo la tutela del refugio.

- Donaciones: Registro de las donaciones realizadas al refugio, ya sea por usuarios registrados o donantes anónimos.

- Visitas al Centro: Gestión de actividades organizadas por el refugio, como jornadas de adopción y eventos educativos.


## 2. Descripción detallada de las entidades

### Usuarios
Campos principales:

- ```id_usuario```: INT (PK, AUTO_INCREMENT).
- ```nombre```: VARCHAR(100) (NOT NULL).
- ```email```: VARCHAR(255) (NOT NULL, UNIQUE).
- ```telefono```: VARCHAR(9) NOT NULL CHECK (telefono ~ '^[0-9]{9}$').
- ```contraseña```: VARCHAR(255) (NOT NULL, almacenada como hash).
- ```rol```: ENUM (administrador, voluntario, padrino, adoptante, genérico) NOT NULL.

Roles disponibles:

- ```Administrador```: Puede crear, leer, modificar y eliminar registros en la base de datos.
- ```Genérico```: Puede leer registros genéricos, como información sobre animales.

### Animales
Campos principales:

- ```id_animal```: INT (PK, AUTO_INCREMENT).
- ```nombre```: VARCHAR(100) (NOT NULL).
- ```tipo_animal```: ENUM (perro, gato) (NOT NULL).
- ```estado```: ENUM (en adopción, apadrinado, en tratamiento, adoptado) (NOT NULL).

### Adopciones
Campos principales:

- ```id_adopcion```: INT (PK, AUTO_INCREMENT).
- ```id_usuario```: INT (FK, referencia a Usuarios.id_usuario).
- ```id_animal```: INT (FK, referencia a Animales.id_animal).
- ```fecha_adopcion```: DATE (NOT NULL).
- ```observaciones```: TEXT (NULLABLE).

Relaciones:

- **Usuario y Adopciones**: Relación 1:N (Un usuario puede adoptar múltiples animales).
- **Animal y Adopciones**: Relación 1:1 (Un animal solo puede ser adoptado una vez).

<hr>
<br>
<br>

- # Diagrama Entidad/Relación

```plantuml
@startuml
entity "Usuarios" as Usuarios {
    + id_usuario : INT
    + nombre : VARCHAR(100)
    + email : VARCHAR(255)
    + telefono : VARCHAR(9)
    + contraseña : VARCHAR(255)
    + rol : ENUM
}

entity "Animales" as Animales {
    + id_animal : INT
    + nombre : VARCHAR(100)
    + tipo_animal : ENUM
    + estado : ENUM
}

entity "Adopciones" as Adopciones {
    + id_adopcion : INT
    + id_usuario : INT
    + id_animal : INT
    + fecha_adopcion : DATE
    + observaciones : TEXT
}

Usuarios ||--o{ Adopciones : "1:N"
Animales ||--|| Adopciones : "1:1"
@enduml


<hr>
<br>
<br>

# 3. Descripción de los *endpoints*. 

En esta sección se detallan los diferentes endpoints disponibles en la API REST del proyecto Refugio Kimba. Cada uno de ellos está diseñado para manejar las operaciones relacionadas con las principales entidades del sistema, como usuarios, animales y adopciones.

Para cada endpoint se especifican:

- La operación que realiza (por ejemplo, creación, lectura, actualización o eliminación).
- Los roles que tienen acceso a dicho endpoint.
- Las restricciones aplicables, como requisitos de autenticación o permisos específicos.
- El formato de los datos de entrada y salida.
- Las posibles excepciones y los códigos de estado HTTP asociados.

## a. Usuarios.

Los ususarios tendran un método C.R.U.D aunque no todos los tipos de usuarios tendrán acceso a todos los métodos, dependerán del *rol* que tengan.

- ### C.R.U.D

-`POST /usuarios/` : Permiten crearse nuevos usuarios.
- **RUTA PÚBLICA** ✅
  - **Entrada**: JSON con `nombre`, `email`, `telefono`, `contraseña` y `rol`
  - **Restricciones**: Método accesible sólo a los usuarios *administradores*.
  - **Salida**: 201: CREATED -> Usuario creado con éxito. Devuelve el JSON con los datos del usuario creado, incluyendo el `id` que se crea de forma automática.
  - **Excepciones**: 400: BAD_REQUEST -> Cuando los datos son inválidos o faltantes.



- `GET /usuarios/{id}`: Permite a los *administradores* obtener la información de un usuario concreto a través de su ID.
  - **RUTA PÚBLICA** ❌
  - **Entrada**: `ruta` + `id` del usuario.
  - **Restricciones**: Sólo los usuarios `administradores` tienen permiso para obtener ésta información.
  - **Salida**: 200: OK -> Devuelve el JSON con los datos del usuario.
  - **Excepciones**: 400: BAD_REQUEST -> Dátos inválidos.


- `GET /usuarios?rol=ROL` : Obtiene los datos de todos los usuarios que compartan el mismo tipo de rol.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: `ruta` + `rol=ROL` (ej : `GET /usuarios?rol=generico`)
    - **Restricciones**: Sólo los usuarios `administradores` tienen permiso para obtener ésta información.
    - **Salida**: 200: OK -> JSON con los datos de todos los usuarios encontrados.
    - **Excepciones**: 
      - 400: BAD_REQUEST -> Si el valor del *rol* es inválido. 
      - 404 NOT_FOUND -> El *rol* es válido, pero no se encuentra ningún usuario que cumpla el requisito.


- `PUT /usuarios/{id}`: Actualiza la información de un usuario.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: JSON con los campos a actualizar.
    - **Restricciones**: Sólo usuarios *administradores* o el propio usuario pueden modificar éstos datos.
    - **Salida**: 200 OK: JSON con los datos del usuario actualizado.
    - **Excepciones**: 400 Bad Request: Datos inválidos.

- `DELETE /usuarios/{id}`:
    - **RUTA PÚBLICA** ❌
    - **Entrada**: `ruta` + `id` del usuario a eliminar.
    - **Restricciones**: Sólo usuarios *administradores* pueden eliminar usuarios de la base de datos.
    - **Salida**: 200 OK: Mensaje de confirmación de eliminación.
    - **Excepciones**: 404 Not Found: Usuario no encontrado.

- ### **Autenticación**
- `POST  /usuarios/login`
    - **RUTA PÚBLICA** ✅
    - **Entrada**: JSON con `email` y `contraseña`.
    - **Restricciones**: Credenciales válidas son requeridas para autenticación.
    - **Salida**: 200 OK: JSON con el token JWT y datos básicos del usuario.
    - **Excepciones**: 401 Unauthorized: Credenciales incorrectas.

- ### **Registro**


- `POST /usuarios/register/`
    - **RUTA PÚBLICA** ✅
    - **Entrada**: JSON con nombre, email, telefono, contraseña y rol.
    - **Restricciones**: Los usuarios con el rol genérico solo pueden crear usuarios del mismo rol. Los usuarios administradores pueden crear usuarios con cualquier rol.
    - **Salida**: 201 Created: Devuelve JSON con los datos del usuario creado.
    - **Excepciones**: 400 Bad Request: Datos inválidos o faltantes.

<hr>

## b. Animales.

- `POST /animales/`: Permite registrar un nuevo animal en el refugio.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: JSON con nombre, tipo_animal (perro, gato), estado, etc.
    - **Restricciones**: Solo administradores pueden registrar animales.
    - **Salida**: 201 Created: JSON con los datos del animal registrado.
    - **Excepciones**: 400 Bad Request: Datos inválidos o faltantes.

- `GET /animales/{id}`: Permite obtener la información de un animal específico.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: 	`Ruta` con el `ID` del animal.
    - **Restricciones**: Sólo administradores.
    - **Salida**: 200 OK: JSON con los datos del animal solicitado.
    - **Excepciones**: 404 Not Found: Animal no encontrado.

- `PUT /animales/{id}`: Permite actualizar la información de un animal registrado.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: JSON con los datos a actualizar.
    - **Restricciones**: Solo administradores pueden modificar animales.
    - **Salida**: 200 OK: JSON con los datos actualizados del animal.
    - **Excepciones**: 400 Bad Request: Datos inválidos.

- `GET /animales?estado={estado}`: Obtiene una lista de animales según su estado (en adopción, apadrinado, etc.).
    - **RUTA PÚBLICA** ❌
    - **Entrada**: `ruta` + estado por el que se quiere fiiltrar. Ej: `GET animales?estado=apadrionado`
    - **Restricciones**: Sólo administradores.
    - **Salida**: 200 OK: JSON con la lista de animales filtrados por estado.
    - **Excepciones**: 404 Not Found: No se encuentan animales con el estado indicado.
                       400 Bad Request: Datos inválidos.

<hr>

## c. Adopciones.

- `POST /adopciones/`: Permite registrar una nueva adopción en el sistema.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: JSON con id_usuario, id_animal, fecha_adopcion, y observaciones.
    - **Restricciones**: Solo administradores pueden registrar adopciones.
    - **Salida**: 201 Created: JSON con los datos de la adopción registrada.
    - **Excepciones**: 400 Bad Request: Datos inválidos.


- `GET /adopciones/`: Permite obtener una lista de todas las adopciones registradas.
    - **RUTA PÚBLICA** ❌
    - **Entrada**: Ninguna
    - **Restricciones**: Solo administradores pueden acceder.
    - **Salida**: 200 OK: JSON con la lista de adopciones.
    - **Excepciones**: 404 Not Found: Registros no encontrados.

### Excepciones genéricas
 
Los errores más comunes en el manejo de datos con la base de datos incluyen problemas de validación, autenticación, permisos insuficientes, fallos internos del servidor, etc.
Los más comunes que nos encontraremos son:

- `400 BAD_REQUEST`: Datos inválidos o faltantes.
- `401 UNAUTHORIZED`: Falta de autenticación o token inválido.
- `403 FORBIDDEN`: Permisos insuficientes para realizar esta acción.
- `404 NOT_FOUND`: Recurso no encontrado.
- `405 METHOD_NOT_ALLOWED`: Método HTTP no permitido.
- `409 CONFLICT`: Conflicto con el estado actual del recurso.
- `422 UNPROCESSABLE_ENTITY`: Datos correctos pero no procesables.
- `429 TOO_MANY_REQUESTS`: Límite de solicitudes excedido.
- `500 INTERNAL_SERVER_ERROR`: Error inesperado en el servidor.

En caso de que se produzca una excepción, la API devolverá una respuesta en formato JSON que incluirá detalles sobre el error. Este es un ejemplo para un error `404 NOT_FOUND`:

```json
{
  "timestamp": "2024-12-10T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "El recurso solicitado no existe.",
  "path": "/usuarios/99"
}
```

<hr>
<br>
<br>

## 4. Lógica de negocio.

### Usuarios
- **Registro**:
  - Los usuarios genéricos solo pueden registrarse a sí mismos o a otros usuarios genéricos.
  - Solo los administradores pueden registrar usuarios con roles avanzados, como voluntarios o padrinos.
  - Se valida que los datos como el email y el teléfono sean únicos en la base de datos.
- **Modificación**:
  - Los usuarios solo pueden modificar sus propios datos, a excepción de su rol.
  - Los administradores pueden modificar cualquier usuario.
- **Eliminación**:
  - Los administradores son los únicos con permisos para eliminar usuarios. Sin embargo, se ofrece un endpoint dedicado para que un usuario pueda solicitar la eliminación de su cuenta, respetando las políticas de privacidad.

### Animales
- **Registro**:
  - Solo los administradores pueden registrar nuevos animales en el sistema.
  - Los datos como el estado (en adopción, apadrinado, etc.) y el tipo de animal (perro, gato) son validados para cumplir con las reglas predefinidas.
- **Modificación**:
  - El estado de un animal solo puede ser actualizado por un administrador.
  - Un animal que está adoptado no puede volver a cambiar su estado a "en adopción".
- **Consulta**:
  - Los administradores pueden filtrar animales por tipo o estado para facilitar la gestión interna.

### Adopciones
- **Registro**:
  - Solo los administradores pueden registrar adopciones.
  - Antes de realizar una adopción, se valida que el animal esté en el estado "en adopción".
  - Un usuario no puede adoptar más de una vez al mismo animal.
- **Consulta**:
  - Los administradores tienen acceso a todos los registros de adopción.

### Validaciones generales
- Se asegura que los datos enviados en cada solicitud cumplan con los formatos esperados.
- La existencia de recursos referenciados (como id_usuario o id_animal) es verificada antes de procesar cualquier solicitud para evitar inconsistencias.

<hr>
<br>
<br>

## 5. Restricciones de Seguridad.

Con estas medidas, se busca ofrecer un entorno seguro para todos los usuarios del sistema, protegiendo tanto la integridad de los datos como la privacidad de los usuarios
El proyecto utiliza diversas medidas de seguridad para proteger los datos y garantizar un acceso controlado:

### 1. Autenticación mediante JWT:
- Los usuarios deben autenticarse con sus credenciales (email y contraseña) para obtener un token JWT.
- El token es necesario para acceder a rutas privadas y realizar operaciones autorizadas.

### 2. Roles y permisos
- Cada usuario tiene asignado un rol que define sus permisos dentro del sistema.
- Los roles determinan qué endpoints y operaciones están disponibles para cada tipo de usuario.

### 3. Validación de entradas
- Se valida que los datos enviados por el cliente sean correctos, completos y estén en el formato esperado.
- Se utilizan reglas de validación específicas para evitar datos maliciosos o inconsistentes.

### 4. Restricción de métodos HTTP
- Cada endpoint está configurado para aceptar solo los métodos HTTP necesarios (por ejemplo, GET, POST, PUT, DELETE).

### 5. Control de acceso a recursos
- Los usuarios solo pueden acceder a los datos que les corresponden según sus permisos. Por ejemplo, un usuario genérico no puede acceder a los datos de otros usuarios o animales.

### 6. Cifrado
- Las contraseñas de los usuarios se almacenan en la base de datos como hashes cifrados.
- Toda la comunicación entre cliente y servidor debe realizarse a través de HTTPS.



