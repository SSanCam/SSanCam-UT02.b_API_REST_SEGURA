package com.refugioKimba.interfaces;

import com.refugioKimba.dto.AnimalDTO;

import java.util.List;

public interface IAnimalService {
    AnimalDTO insert(AnimalDTO animalDTO);
    AnimalDTO getById(Long id);
    List<AnimalDTO> getAll();
    AnimalDTO modify(Long id, AnimalDTO dto);
    void delete(Long id);
}
