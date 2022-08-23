package com.selin.modulo1.reporteins.servicios;

import com.selin.modulo1.reporteins.entidades.Usuario;
import com.selin.modulo1.reporteins.entidades.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Long idRol) throws Exception;
    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
}
