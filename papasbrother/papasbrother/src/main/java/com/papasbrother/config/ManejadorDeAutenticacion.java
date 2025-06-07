package com.papasbrother.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ManejadorDeAutenticacion implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        System.out.println("Autenticación exitosa para: " + authentication.getName());  // Imprime en consola que la autenticación fue exitosa e indica el usuario autenticado.

        boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN")); // Verifica si el usuario tiene el rol de administrador.


        // Redirige al usuario a la página correspondiente según su rol.        

        if (esAdmin) {
            System.out.println("Redirigiendo a /admin/inicio");
            response.sendRedirect("/admin/inicio");
        } else {
            System.out.println("Redirigiendo a /inicio");
            response.sendRedirect("/inicio");
        }

    }
}