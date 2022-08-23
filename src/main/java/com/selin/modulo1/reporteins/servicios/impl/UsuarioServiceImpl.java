package com.selin.modulo1.reporteins.servicios.impl;

import com.selin.modulo1.reporteins.entidades.Rol;
import com.selin.modulo1.reporteins.entidades.Usuario;
import com.selin.modulo1.reporteins.entidades.UsuarioRol;
import com.selin.modulo1.reporteins.excepciones.UsuarioFoundException;
import com.selin.modulo1.reporteins.repositorios.RolRepository;
import com.selin.modulo1.reporteins.repositorios.UsuarioRepository;
import com.selin.modulo1.reporteins.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Usuario guardarUsuario(Usuario usuario, Long idRol) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }else{
            Set<UsuarioRol> usuarioRols = new HashSet<>();
            usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
            Rol rol = rolRepository.findByID(idRol);

            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(usuario);
            usuarioRol.setRol(rol);
            usuarioRols.add(usuarioRol);

            usuario.getUsuarioRoles().addAll(usuarioRols);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
}
