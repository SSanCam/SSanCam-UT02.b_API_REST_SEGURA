package com.refugioKimba.controller;

import com.refugioKimba.dto.AnimalDTO;
import com.refugioKimba.exception.GeneralException;
import com.refugioKimba.service.AnimalService;
import com.refugioKimba.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> insert(@RequestBody AnimalDTO animalDTO) throws GeneralException {
        AnimalDTO animalCreado = animalService.insert(animalDTO);
        return ResponseEntity.status(201).body(animalCreado);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> getById(@PathVariable Long id) throws GeneralException {
        AnimalDTO animal = animalService.getById(id);
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> modify(@PathVariable Long id, @RequestBody AnimalDTO animalDTO) throws GeneralException {
        AnimalDTO animalMod = animalService.modify(id, animalDTO);
        return ResponseEntity.ok(animalMod);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAll() {
        List<AnimalDTO> animales = animalService.getAll().stream()
                .map(animal -> mapper.entityToDto(animal, AnimalDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(animales);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) throws GeneralException {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
