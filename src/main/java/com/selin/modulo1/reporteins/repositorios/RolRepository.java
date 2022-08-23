package com.selin.modulo1.reporteins.repositorios;

import com.selin.modulo1.reporteins.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByID(Long idRol);
}
