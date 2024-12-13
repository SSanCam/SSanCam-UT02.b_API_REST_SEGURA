package com.refugioKimba.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Representa un usuario del sistema del Refugio Kimba.
 * La entidad Usuario almacena información básica de los usuarios, como su nombre, email,
 * teléfono y rol dentro del sistema.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String contrasenia;

    @Column(name = "telefono", length = 9)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolUsuario rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Adopcion> adopciones;

    /**
     * Constructores para el JPA
     */
    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasenia, String telefono, RolUsuario rol, List<Adopcion> adopciones) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.rol = rol;
        this.adopciones = adopciones;
    }

    public Usuario(Long id, String nombre, String email, String contrasenia, String telefono, RolUsuario rol, List<Adopcion> adopciones) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.rol = rol;
        this.adopciones = adopciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Adopcion> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<Adopcion> adopciones) {
        this.adopciones = adopciones;
    }
}
