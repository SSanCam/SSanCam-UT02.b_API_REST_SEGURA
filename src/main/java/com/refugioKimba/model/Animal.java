package com.refugioKimba.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoAnimal tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAnimal estado;

    @Column(name = "registro", nullable = false)
    private LocalDate registro;

    @OneToOne(mappedBy = "animal")
    private Adopcion adopcion;

}
