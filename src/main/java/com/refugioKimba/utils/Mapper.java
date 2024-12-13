package com.refugioKimba.utils;

import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.dto.UsuarioLoginDTO;
import com.refugioKimba.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static Usuario usuarioToDto(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setContrasenia(dto.getContrasenia());  // Esto puede incluir hashing más adelante.
        u.setTelefono(dto.getTelefono());
        u.setRol(dto.getRol());
        return u;

    }

    public static UsuarioDTO usuarioToEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        return dto;
    }



}
