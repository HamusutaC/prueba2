package com.selin.modulo1.reporteins.controladores;

import com.selin.modulo1.reporteins.configuraciones.JwtUtils;
import com.selin.modulo1.reporteins.entidades.JwtRequest;
import com.selin.modulo1.reporteins.entidades.JwtResponse;
import com.selin.modulo1.reporteins.entidades.Usuario;
import com.selin.modulo1.reporteins.excepciones.UsuarioNotFoundException;
import com.selin.modulo1.reporteins.servicios.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch(UsuarioNotFoundException usuarioNFE){
            usuarioNFE.printStackTrace();
            throw new UsuarioNotFoundException("Usuario no encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException disabled){
            throw new Exception("Usuario desabilitado" + disabled.getMessage());
        }catch(BadCredentialsException basCE){
            throw new Exception("Credenciales invalidas" + basCE.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
