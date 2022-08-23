package com.selin.modulo1.reporteins.repositorios;

import com.selin.modulo1.reporteins.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
