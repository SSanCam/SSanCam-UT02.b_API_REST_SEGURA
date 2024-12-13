package com.refugioKimba.repository;

import com.refugioKimba.model.Adopcion;
import com.refugioKimba.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
