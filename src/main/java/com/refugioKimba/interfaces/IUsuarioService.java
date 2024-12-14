package com.refugioKimba.interfaces;

import com.refugioKimba.dto.UsuarioDTO;
import com.refugioKimba.dto.UsuarioRegisterDTO;
import com.refugioKimba.exception.GeneralException;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO insert(UsuarioDTO usuarioDTO) throws GeneralException;
    UsuarioDTO getById(Long id) throws GeneralException;
    List<UsuarioDTO> getAll();
    UsuarioDTO modify(Long id, UsuarioDTO usuarioDTO) throws GeneralException;
    void delete(Long id) throws GeneralException;
    public String login(UsuarioDTO dto) throws GeneralException;
    public String register(UsuarioRegisterDTO dto) throws GeneralException;
}