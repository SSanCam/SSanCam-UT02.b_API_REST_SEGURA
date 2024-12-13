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
}
