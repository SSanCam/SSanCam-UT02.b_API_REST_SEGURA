package com.refugioKimba.service;


import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.interfaces.IUsuarioService;
import com.refugioKimba.model.Usuario;
import com.refugioKimba.repository.UsuarioRepository;
import com.refugioKimba.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mapper mapper;

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
        Usuario u = usuarioRepository.getById(id);
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
        usuarioRepository.deleteById(id);
    }
}
