package com.selin.modulo1.reporteins.servicios.impl;

import com.selin.modulo1.reporteins.entidades.Rol;
import com.selin.modulo1.reporteins.repositorios.RolRepository;
import com.selin.modulo1.reporteins.servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol obtenerRol(Long idRol) {
        return rolRepository.findByID(idRol);
    }
}
