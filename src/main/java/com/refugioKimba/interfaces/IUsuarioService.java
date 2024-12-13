package com.refugioKimba.interfaces;

import com.refugioKimba.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO insert(UsuarioDTO usuarioDTO);
    UsuarioDTO getById(Long id);
    List<UsuarioDTO> getAll();
    UsuarioDTO modify(Long id, UsuarioDTO usuarioDTO);
    void delete(Long id);
}