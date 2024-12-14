package com.refugioKimba.service;

import com.refugioKimba.dto.AnimalDTO;
import com.refugioKimba.exception.BadRequestException;
import com.refugioKimba.exception.GeneralException;
import com.refugioKimba.interfaces.IAnimalService;
import com.refugioKimba.model.Animal;
import com.refugioKimba.repository.AnimalRepository;
import com.refugioKimba.utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public AnimalDTO insert(AnimalDTO animalDTO) throws GeneralException {
        if (animalDTO == null){
            throw new BadRequestException("El DTO proporcionado no puede ser nulo.");
        }

        try {
            Animal animal = mapper.dtoToEntity(animalDTO, Animal.class);
            animal.setRegistro(LocalDate.now());
            Animal animalRegistrado = animalRepository.save(animal);
            return mapper.entityToDto(animalRegistrado, AnimalDTO.class);
        } catch (Exception e) {
            throw new GeneralException("Error al crear el animal: " + e.getMessage());
        }
    }

    @Override
    public AnimalDTO getById(Long id) throws GeneralException {
        try {
            Animal animal = animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra ningun animal con el id: " + id));
            return mapper.entityToDto(animal, AnimalDTO.class);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se encuentra el animal con el id: " + id);
        } catch (Exception e) {
            throw new GeneralException("Error al obtener el animal: " + e.getMessage());
        }
    }

    @Override
    public List<AnimalDTO> getAll() {
        List<Animal> animales = animalRepository.findAll();
        return animales.stream()
                .map(animal -> mapper.entityToDto(animal, AnimalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AnimalDTO modify(Long id, AnimalDTO dto) throws GeneralException {
        try {
            Animal a = animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra ningún animal por el id: " + id));
            if(a != null){
                a.setNombre(dto.getNombre());
                a.setTipo(dto.getTipo());
                a.setEstado(dto.getEstado());
                animalRepository.save(a);
                return mapper.entityToDto(a, AnimalDTO.class);
            }
            throw new EntityNotFoundException("No se encuentra el animal con el id: " + id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se encuentra el animal con el id: " + id);
        } catch (Exception e) {
            throw new GeneralException("Error al modificar el animal: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GeneralException {
        try {
            Animal animalExistente = animalRepository.findById(id).orElse(null);
            if (animalExistente == null) {
                throw new EntityNotFoundException("No se encontró el animal con el id: " + id);
            }
            animalRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se ha encontrado el animal con el id: " + id);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar el animal: " + e.getMessage());
        }
    }
}
