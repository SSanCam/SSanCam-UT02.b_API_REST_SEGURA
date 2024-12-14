package com.refugioKimba.service;

import com.refugioKimba.dto.AdopcionDTO;
import com.refugioKimba.exception.EntityNotFoundException;
import com.refugioKimba.exception.GeneralException;
import com.refugioKimba.interfaces.IAdopcionService;
import com.refugioKimba.model.Adopcion;
import com.refugioKimba.repository.AdopcionRepository;
import com.refugioKimba.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdopcionService implements IAdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public AdopcionDTO insert(AdopcionDTO dto) throws GeneralException {
        if (dto == null) {
            throw new GeneralException("El DTO proporcionado no puede ser nulo.");
        }

        try {
            Adopcion adopcion = mapper.dtoToEntity(dto, Adopcion.class);
            Adopcion adopcioncreada = adopcionRepository.save(adopcion);
            return mapper.entityToDto(adopcioncreada, AdopcionDTO.class);
        } catch (Exception e) {
            throw new GeneralException("Error al crear la adopción: " + e.getMessage());
        }
    }

    @Override
    public AdopcionDTO getById(Long id) throws GeneralException {
        Adopcion adopcion = adopcionRepository.findById(id).orElse(null);
        try {
            if (adopcion == null) {
                throw new EntityNotFoundException("No se encuetra el registro de adopción con id: " + id);
            }
            return mapper.entityToDto(adopcion, AdopcionDTO.class);
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la adopción: " + e.getMessage());
        }
    }

    @Override
    public List<AdopcionDTO> getAll() {
        List<Adopcion> adopciones = adopcionRepository.findAll();
        return adopciones.stream()
                .map(adopcion -> mapper.entityToDto(adopcion, AdopcionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdopcionDTO modify(Long id, AdopcionDTO adopcionDTO) throws GeneralException {
        try {
            Adopcion adopcion = adopcionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encuentra la adopción con el id: " + id));
            adopcion.setFechaAdopcion(adopcionDTO.getFechaAdopcion());
            adopcion.setObservaciones(adopcionDTO.getObservaciones());
            adopcionRepository.save(adopcion);
            return mapper.entityToDto(adopcion, AdopcionDTO.class);
        } catch (Exception e) {
            throw new GeneralException("Error al modificar la adopción: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GeneralException {
        try {
            Adopcion adopcionExistente = adopcionRepository.findById(id).orElse(null);
            if (adopcionExistente == null) {
                throw new EntityNotFoundException("No se encuentra la adopción con el id: " + id);
            }
            adopcionRepository.deleteById(id);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar la adopción: " + e.getMessage());
        }
    }
}
