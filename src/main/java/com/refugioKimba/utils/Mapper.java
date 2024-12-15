package com.refugioKimba.utils;

import com.refugioKimba.dto.AdopcionDTO;
import com.refugioKimba.dto.AnimalDTO;
import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.model.Adopcion;
import com.refugioKimba.model.Animal;
import com.refugioKimba.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    // Convertir entidad a DTO
    public <T, D> D entityToDto(T entity, Class<D> dtoClass) {
        if (entity == null) {
            return null;
        }
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();

            // Mapeo manual para Usuario
            if (entity instanceof Usuario && dto instanceof UsuarioDTO) {
                Usuario usuario = (Usuario) entity;
                UsuarioDTO usuarioDTO = (UsuarioDTO) dto;

                usuarioDTO.setNombre(usuario.getNombre());
                usuarioDTO.setEmail(usuario.getEmail());
                usuarioDTO.setTelefono(usuario.getTelefono());
                usuarioDTO.setRol(usuario.getRol());
                usuarioDTO.setContrasenia(usuario.getContrasenia());
            }

            // Mapeo manual para Animal
            if (entity instanceof Animal && dto instanceof AnimalDTO) {
                Animal animal = (Animal) entity;
                AnimalDTO animalDTO = (AnimalDTO) dto;

                animalDTO.setNombre(animal.getNombre());
                animalDTO.setTipo(animal.getTipo());
                animalDTO.setEstado(animal.getEstado());
                animalDTO.setRegistro(animal.getRegistro());
            }

            // Mapeo manual para Adopcion
            if (entity instanceof Adopcion && dto instanceof AdopcionDTO) {
                Adopcion adopcion = (Adopcion) entity;
                AdopcionDTO adopcionDTO = (AdopcionDTO) dto;

                adopcionDTO.setIdUsuario(adopcion.getUsuario().getIdUsuario());
                adopcionDTO.setIdAnimal(adopcion.getAnimal().getIdAnimal());
                adopcionDTO.setFechaAdopcion(adopcion.getFechaAdopcion());
                adopcionDTO.setObservaciones(adopcion.getObservaciones());
            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convertir DTO a entidad
    public <T, D> T dtoToEntity(D dto, Class<T> entityClass) {
        if (dto == null) {
            return null;
        }
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();

            // Mapeo manual para Usuario
            if (dto instanceof UsuarioDTO && entity instanceof Usuario) {
                UsuarioDTO usuarioDTO = (UsuarioDTO) dto;
                Usuario usuario = (Usuario) entity;

                usuario.setNombre(usuarioDTO.getNombre());
                usuario.setEmail(usuarioDTO.getEmail());
                usuario.setTelefono(usuarioDTO.getTelefono());
                usuario.setRol(usuarioDTO.getRol());
                usuario.setContrasenia(usuarioDTO.getContrasenia());
            }

            // Mapeo manual para Animal
            if (dto instanceof AnimalDTO && entity instanceof Animal) {
                AnimalDTO animalDTO = (AnimalDTO) dto;
                Animal animal = (Animal) entity;

                animal.setNombre(animalDTO.getNombre());
                animal.setTipo(animalDTO.getTipo());
                animal.setEstado(animalDTO.getEstado());
                animal.setRegistro(animalDTO.getRegistro());
            }

            // Mapeo manual para Adopcion
            if (dto instanceof AdopcionDTO && entity instanceof Adopcion) {
                AdopcionDTO adopcionDTO = (AdopcionDTO) dto;
                Adopcion adopcion = (Adopcion) entity;

                // Este mapeo supone que tienes los objetos completos para Usuario y Animal
                // Esto puede requerir que busques o crees instancias de estos objetos en la base de datos
                Usuario usuario = new Usuario(); // Supuesto, obtener el usuario desde el repositorio

                Animal animal = new Animal(); // Supuesto, obtener el animal desde el repositorio

                adopcion.setUsuario(usuario);
                adopcion.setAnimal(animal);
                adopcion.setFechaAdopcion(adopcionDTO.getFechaAdopcion());
                adopcion.setObservaciones(adopcionDTO.getObservaciones());
            }

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/*
package com.refugioKimba.utils;


import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public <T, D> D entityToDto(T entity, Class<D> dtoClass) {
        if (entity == null){
            return null;
        }
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public<T, D> T dtoToEntity( D dto, Class<T> entityClass) {
        if (dto == null){
            return null;
        }
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/