package com.refugioKimba.dto;

import com.refugioKimba.model.RolUsuario;

public class UsuarioRegisterDTO {
    private String email;
    private String contrasenia;
    private String nombre;
    private String telefono;
    private RolUsuario rol;


    public UsuarioRegisterDTO() {
    }

    public UsuarioRegisterDTO(String contrasenia, String nombre, String telefono, RolUsuario rol) {
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }

    public UsuarioRegisterDTO(String email, String contrasenia, String nombre, String telefono, RolUsuario rol) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.telefono = telefono;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
