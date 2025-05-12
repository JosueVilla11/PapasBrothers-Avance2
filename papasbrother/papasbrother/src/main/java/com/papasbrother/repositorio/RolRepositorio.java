package com.papasbrother.repositorio;

import com.papasbrother.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
    
}
