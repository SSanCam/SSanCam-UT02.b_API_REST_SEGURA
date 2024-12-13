package com.refugioKimba.dto;

import com.refugioKimba.model.EstadoAnimal;
import com.refugioKimba.model.TipoAnimal;

import java.time.LocalDate;

public class AnimalDTO {
    private String nombre;
    private TipoAnimal tipo;
    private EstadoAnimal estado;
    private LocalDate registro;

    public AnimalDTO() {
    }

    public AnimalDTO(String nombre, TipoAnimal tipo, EstadoAnimal estado, LocalDate registro) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.registro = registro;
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
}
