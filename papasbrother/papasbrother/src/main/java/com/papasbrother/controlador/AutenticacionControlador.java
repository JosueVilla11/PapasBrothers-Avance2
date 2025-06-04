package com.papasbrother.controlador;

import com.papasbrother.modelo.*;
import com.papasbrother.repositorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final UsuarioRepositorio usuarioRepositorio;
    private final RolRepositorio rolRepositorio;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Redirige a la vista de login
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));

        Rol rolUsuario = rolRepositorio.findByNombre("ROLE_USER");  // Busca si el rol "ROLE_USER" ya existe en la base de datos.
        if (rolUsuario == null) {
            rolUsuario = new Rol();
            rolUsuario.setNombre("ROLE_USER");
            rolRepositorio.save(rolUsuario);
        }


        // Asigna el rol al usuario y lo guarda en la base de datos.
        usuario.getRoles().add(rolUsuario);
        usuarioRepositorio.save(usuario);
        return "redirect:/login";
    }
}
