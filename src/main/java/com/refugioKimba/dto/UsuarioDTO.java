package com.refugioKimba.dto;

import com.refugioKimba.model.RolUsuario;

public class UsuarioDTO {

    private String nombre;
    private String email;
    private String contrasenia;
    private String telefono;
    private RolUsuario rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String email, String contrasenia, String telefono, RolUsuario rol) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
}
