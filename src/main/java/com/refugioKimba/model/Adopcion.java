package com.refugioKimba.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "adopciones")
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adopcion")
    private Long id_adopcion;

    @Column(name = "fecha_adopcion", nullable = false)
    private Date fecha_adopcion;

    @Column(name = "observaciones", nullable = false, length = 255)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;


    public Adopcion() {
    }

    public Adopcion(Date fecha_adopcion, String observaciones, Usuario usuario, Animal animal) {
        this.fecha_adopcion = fecha_adopcion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.animal = animal;
    }

    public Adopcion(Long id_adopcion, Date fecha_adopcion, String observaciones, Usuario usuario, Animal animal) {
        this.id_adopcion = id_adopcion;
        this.fecha_adopcion = fecha_adopcion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.animal = animal;
    }

    public Long getId_adopcion() {
        return id_adopcion;
    }

    public void setId_adopcion(Long id_adopcion) {
        this.id_adopcion = id_adopcion;
    }

    public Date getFecha_adopcion() {
        return fecha_adopcion;
    }

    public void setFecha_adopcion(Date fecha_adopcion) {
        this.fecha_adopcion = fecha_adopcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
