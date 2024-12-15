package com.refugioKimba.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long idAnimal;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal", nullable = false)
    private TipoAnimal tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAnimal estado;

    @Column(name = "registro", nullable = false)
    private LocalDate registro;

    @OneToOne(mappedBy = "animal")
    private Adopcion adopcion;

    public Animal() {
    }

    public Animal(String nombre, TipoAnimal tipo, EstadoAnimal estado, LocalDate registro, Adopcion adopcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.registro = registro;
        this.adopcion = adopcion;
    }

    public Animal(Long idAnimal, String nombre, TipoAnimal tipo, EstadoAnimal estado, LocalDate registro, Adopcion adopcion) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.registro = registro;
        this.adopcion = adopcion;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAnimal getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnimal tipo) {
        this.tipo = tipo;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    public LocalDate getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDate registro) {
        this.registro = registro;
    }

    public Adopcion getAdopcion() {
        return adopcion;
    }

    public void setAdopcion(Adopcion adopcion) {
        this.adopcion = adopcion;
    }
}
