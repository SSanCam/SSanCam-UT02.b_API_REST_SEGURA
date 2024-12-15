package com.refugioKimba.service;

import com.refugioKimba.model.Usuario;
import com.refugioKimba.repository.UsuarioRepository;
import com.refugioKimba.dto.UsuarioRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getContrasenia())
                .roles(usuario.getRol().name())
                .build();
    }

    public Usuario registrarUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        if (usuarioRepository.findByEmail(usuarioRegisterDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setContrasenia(passwordEncoder.encode(usuarioRegisterDTO.getContrasenia()));
        nuevoUsuario.setEmail(usuarioRegisterDTO.getEmail());
        nuevoUsuario.setRol(usuarioRegisterDTO.getRol());
        nuevoUsuario.setNombre(usuarioRegisterDTO.getNombre());
        nuevoUsuario.setTelefono(usuarioRegisterDTO.getTelefono());

        usuarioRepository.save(nuevoUsuario);

        return nuevoUsuario;
    }
}
