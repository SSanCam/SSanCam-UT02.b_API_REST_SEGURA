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
