package com.refugioKimba.repository;

import com.refugioKimba.model.Adopcion;
import com.refugioKimba.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
