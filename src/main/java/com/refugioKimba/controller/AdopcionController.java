package com.refugioKimba.controller;

import com.refugioKimba.dto.AdopcionDTO;
import com.refugioKimba.exception.GeneralException;
import com.refugioKimba.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<AdopcionDTO> insert(
            @RequestBody AdopcionDTO dto
    ) throws GeneralException {
        AdopcionDTO nuevaAdopcion = adopcionService.insert(dto);
        return ResponseEntity.status(201).body(nuevaAdopcion);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<AdopcionDTO>> getAll() {
        List<AdopcionDTO> adopciones = adopcionService.getAll();
        return ResponseEntity.ok(adopciones);
    }
}
