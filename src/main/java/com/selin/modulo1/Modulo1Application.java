package com.selin.modulo1;

import com.selin.modulo1.reporteins.entidades.Rol;
import com.selin.modulo1.reporteins.entidades.Usuario;
import com.selin.modulo1.reporteins.entidades.UsuarioRol;
import com.selin.modulo1.reporteins.excepciones.UsuarioFoundException;
import com.selin.modulo1.reporteins.servicios.RolService;
import com.selin.modulo1.reporteins.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Modulo1Application{

    public static void main(String[] args) {
        SpringApplication.run(Modulo1Application.class, args);
    }

}
