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
    System.out.println("Autenticación exitosa para: " + authentication.getName());

    boolean esAdmin = authentication.getAuthorities().stream()
            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

    if (esAdmin) {
        System.out.println("Redirigiendo a /admin/inicio");
        response.sendRedirect("/admin/inicio");
    } else {
        System.out.println("Redirigiendo a /inicio");
        response.sendRedirect("/inicio");
    }

    }
}