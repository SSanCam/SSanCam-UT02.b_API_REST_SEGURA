package com.refugioKimba.dto;

import com.refugioKimba.model.RolUsuario;

public class UsuarioRegisterDTO {
    private String email;
    private String contrasenia;
    private RolUsuario rol;

    public UsuarioRegisterDTO() {
    }

    public UsuarioRegisterDTO(String email, String contrasenia, RolUsuario rol) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
}
