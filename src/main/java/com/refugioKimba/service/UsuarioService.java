package com.refugioKimba.service;


import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.dto.UsuarioLoginDTO;
import com.refugioKimba.dto.UsuarioRegisterDTO;
import com.refugioKimba.exception.*;
import com.refugioKimba.interfaces.IUsuarioService;
import com.refugioKimba.model.Usuario;
import com.refugioKimba.repository.UsuarioRepository;
import com.refugioKimba.utils.Mapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public UsuarioDTO insert(UsuarioDTO usuarioDTO) throws GeneralException {
        if (usuarioDTO == null) {
            throw new BadRequestException("El DTO proporcionado no puede ser nulo.");
        }

        try {
            Usuario usuario = mapper.dtoToEntity(usuarioDTO, Usuario.class);
            Usuario usuarioCreado = usuarioRepository.save(usuario);
            return mapper.entityToDto(usuarioCreado, UsuarioDTO.class);

        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Error al crear el usuario: " + e.getMessage());
        } catch (DuplicatedException e) {
            throw new DuplicatedException("El usuario con este email ya existe: " + e.getMessage());
        } catch (Exception e) {
            throw new GeneralException("Error al crear el usuario. Por favor, intente nuevamente más tarde: " + e.getMessage());
        }
    }

    @Override
    public UsuarioDTO getById(Long id) throws GeneralException {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            return mapper.entityToDto(usuario, UsuarioDTO.class);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se encuentra el usuario con el id: " + id);
        } catch (Exception e) {
            throw new GeneralException("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> mapper.entityToDto(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO modify(Long id, UsuarioDTO dto) throws GeneralException {
        try {
            Usuario u = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado para modificar"));

            u.setNombre(dto.getNombre());
            u.setEmail(dto.getEmail());
            u.setTelefono(dto.getTelefono());
            u.setRol(dto.getRol());
            u.setContrasenia(dto.getContrasenia());

            if (dto.getContrasenia() != null && !dto.getContrasenia().isEmpty()){
                u.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
            }

            Usuario usuarioModificado = usuarioRepository.save(u);
            return mapper.entityToDto(usuarioModificado, UsuarioDTO.class);

        } catch (Exception e) {
            throw new GeneralException("Error al modificar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws GeneralException {
        try {
            Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
            if (usuarioExistente == null) {
                throw new EntityNotFoundException("No se ha encontrado el usuario con el id: " + id);
            }
            usuarioRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se ha encontrado el usuario con el id: " + id);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @Override
    @PostMapping("/login")
    public String login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO
    ) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getContrasenia())// modo de autenticación
            );
        } catch (Exception e) {
            System.out.println("Excepcion en authentication");
            throw new BadRequestException("Credenciales del usuario incorrectas");
        }

        String token = "";
        try {
            token = tokenService.generateToken(authentication);
        } catch (Exception e) {
            System.out.println("Excepcion en generar token");
            throw new InternalServerErrorException("Error al generar el token de autenticación");
        }
        return token;

    }
    /*
    @Override
    public String login(UsuarioLoginDTO dto) throws GeneralException {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.getEmail());

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                // Verificar si la contraseña coincide
                if (passwordEncoder.matches(dto.getContrasenia(), usuario.getContrasenia())) {
                    // Si la autenticación es exitosa, generar el token
                    return tokenService.generateToken(usuario);
                } else {
                    throw new BadRequestException("Contraseña incorrecta.");
                }
            } else {
                throw new EntityNotFoundException("Usuario no encontrado.");
            }
        } catch (Exception e) {
            throw new GeneralException("Error al intentar iniciar sesión: " + e.getMessage());
        }
    }
     */


    @Override
    public String register(UsuarioRegisterDTO dto) throws GeneralException {
        try {
            EmailValidator emailValidator = EmailValidator.getInstance();
            if (!emailValidator.isValid(dto.getEmail())) {
                throw new BadRequestException("El email no es válido.");
            }

            String email = dto.getEmail();
            String regex = "[^a-zA-Z0-9]1;";
            if (email.matches(regex)) {
                throw new BadRequestException("El email contiene caracteres no permitidos.");
            }

            if (dto.getContrasenia().length() < 8) {
                throw new BadRequestException("La contraseña debe tener al menos 8 caracteres.");
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
        } catch (BadRequestException e) {
            throw new BadRequestException("Datos inválidos para el registro del usuario: " + e.getMessage());
        } catch (DuplicatedException e) {
            throw new DuplicatedException("El email ya está en uso: " + e.getMessage());
        } catch (Exception e) {
            throw new GeneralException("Error al registrar el usuario. Por favor, intente nuevamente más tarde: " + e.getMessage());
        }
    }
}
