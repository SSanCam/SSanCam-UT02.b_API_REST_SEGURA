package com.refugioKimba.controller;

import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.dto.UsuarioRegisterDTO;
import com.refugioKimba.exception.GeneralException;
import com.refugioKimba.service.TokenService;
import com.refugioKimba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO usuarioDTO) throws GeneralException {
        UsuarioDTO usuarioCreado = usuarioService.insert(usuarioDTO);
        return ResponseEntity.status(201).body(usuarioCreado);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) throws GeneralException {
        UsuarioDTO usuario = usuarioService.getById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('GENERIC')")
    public ResponseEntity<UsuarioDTO> modify(
            @PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO
    ) throws GeneralException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if ("GENERIC".equals(authentication.getAuthorities().toString()) && !username.equals(usuarioDTO.getEmail())) {
            throw new GeneralException("No tienes permiso para modificar este usuario.");
        }

        UsuarioDTO usuarioModificado = usuarioService.modify(id, usuarioDTO);
        return ResponseEntity.ok(usuarioModificado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws GeneralException {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();  // Respuesta sin contenido para indicar éxito en la eliminación
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) throws GeneralException {
        String token = usuarioService.login(usuarioDTO);
        return ResponseEntity.ok(token);
    }

    // Register: método para registrar nuevos usuarios
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioRegisterDTO usuarioRegisterDTO) throws GeneralException {
        String message = usuarioService.register(usuarioRegisterDTO);
        return ResponseEntity.status(201).body(message);
    }

    // Métodos auxiliares para obtener el nombre de usuario y rol autenticado
    private String getAuthenticatedUsername() {
        return org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private String getRole() {
        return org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
}
