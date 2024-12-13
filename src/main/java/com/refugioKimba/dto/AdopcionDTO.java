package com.refugioKimba.dto;

import java.time.LocalDate;

public class AdopcionDTO {
    private Long idUsuario;
    private Long idAnimal;
    private LocalDate fechaAdopcion;
    private String observaciones;


    public AdopcionDTO() {
    }

    public AdopcionDTO(Long idUsuario, Long idAnimal, LocalDate fechaAdopcion, String observaciones) {
        this.idUsuario = idUsuario;
        this.idAnimal = idAnimal;
        this.fechaAdopcion = fechaAdopcion;
        this.observaciones = observaciones;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
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
}
