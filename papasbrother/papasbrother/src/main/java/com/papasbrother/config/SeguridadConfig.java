package com.papasbrother.config;

import com.papasbrother.servicio.impl.UsuarioDetallesServicioImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SeguridadConfig {

    private final UsuarioDetallesServicioImpl usuarioDetallesServicio;
    private final ManejadorDeAutenticacion manejadorDeAutenticacion;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/registro", "/login", "/", "/css/**", "/js/**", "/img/**", "/videos/**", "/menu", "/contacto", "/nosotros", "/inicio", "/promociones", "/terminos", "/politicas").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(manejadorDeAutenticacion) // usamos el componente aquí
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout").permitAll()
            )
            .userDetailsService(usuarioDetallesServicio);

        return http.build();
    }
        // Codificador de contraseñas

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    
}


