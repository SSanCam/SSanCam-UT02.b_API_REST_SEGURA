package com.refugioKimba.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "adopciones")
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adopcion")
    private Long idAdopcion;

    @Column(name = "fecha_adopcion", nullable = false)
    private LocalDate fechaAdopcion;

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

    public Adopcion(LocalDate fechaAdopcion, String observaciones, Usuario usuario, Animal animal) {
        this.fechaAdopcion = fechaAdopcion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.animal = animal;
    }

    public Adopcion(Long idAdopcion, LocalDate fechaAdopcion, String observaciones, Usuario usuario, Animal animal) {
        this.idAdopcion = idAdopcion;
        this.fechaAdopcion = fechaAdopcion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.animal = animal;
    }

    public Long getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(Long idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
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
