package com.refugioKimba.service;


import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.dto.UsuarioRegisterDTO;
import com.refugioKimba.exception.BadRequestException;
import com.refugioKimba.exception.DuplicatedException;
import com.refugioKimba.exception.EntityNotFoundException;
import com.refugioKimba.interfaces.IUsuarioService;
import com.refugioKimba.model.Usuario;
import com.refugioKimba.repository.UsuarioRepository;
import com.refugioKimba.utils.Mapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO insert(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.dtoToEntity(usuarioDTO, Usuario.class);
        Usuario usuarioCreado = usuarioRepository.save(usuario);
        return mapper.entityToDto(usuarioCreado, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return mapper.entityToDto(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> mapper.entityToDto(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO modify(Long id, UsuarioDTO dto) {
        Usuario u = usuarioRepository.getReferenceById(id);
        if (u != null) {
            u.setNombre(dto.getNombre());
            u.setEmail(dto.getEmail());
            u.setTelefono(dto.getTelefono());
            u.setRol(dto.getRol());
            u.setContrasenia(dto.getContrasenia());
            return mapper.entityToDto(u, UsuarioDTO.class);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            throw new EntityNotFoundException("No se ha encontrado el usuario con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }


    @Override
    public String login(UsuarioDTO dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(dto.getContrasenia(), usuario.getContrasenia())) {
                return "Inicio de sesión exitoso";
            } else {
                throw new BadRequestException("Contraseña incorrecta.");
            }
        }
        throw new EntityNotFoundException("Usuario no encontrado.");
    }

    @Override
    public String register(UsuarioRegisterDTO dto) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(dto.getEmail())) {
            throw new BadRequestException("El email no es válido.");
        }

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new DuplicatedException("El email ya existe.");
        } else {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail(dto.getEmail());
            nuevoUsuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
            nuevoUsuario.setRol(dto.getRol());
            nuevoUsuario.setNombre(dto.getNombre());
            nuevoUsuario.setTelefono(dto.getTelefono());
            usuarioRepository.save(nuevoUsuario);
        }
        return "Usuario creado correctamente";
    }

}
