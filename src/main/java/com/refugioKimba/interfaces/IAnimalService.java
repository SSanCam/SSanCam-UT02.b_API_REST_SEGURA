package com.refugioKimba.interfaces;

import com.refugioKimba.dto.AnimalDTO;
import com.refugioKimba.exception.GeneralException;

import java.util.List;

public interface IAnimalService {
    AnimalDTO insert(AnimalDTO animalDTO) throws GeneralException;
    AnimalDTO getById(Long id) throws GeneralException;
    List<AnimalDTO> getAll();
    AnimalDTO modify(Long id, AnimalDTO dto) throws GeneralException;
    void delete(Long id) throws GeneralException;
}
