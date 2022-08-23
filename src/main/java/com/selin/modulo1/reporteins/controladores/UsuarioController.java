package com.selin.modulo1.reporteins.controladores;

import com.selin.modulo1.reporteins.entidades.Rol;
import com.selin.modulo1.reporteins.entidades.Usuario;
import com.selin.modulo1.reporteins.entidades.UsuarioRol;
import com.selin.modulo1.reporteins.servicios.RolService;
import com.selin.modulo1.reporteins.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/create/{idRol}")
    public Usuario guardarUsuario(@RequestBody Usuario usuario, @PathVariable Long idRol) throws Exception {
        return usuarioService.guardarUsuario(usuario, idRol);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
    }
}
