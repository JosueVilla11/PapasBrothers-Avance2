package com.papasbrother.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControlador {

    @GetMapping("/admin/inicio")
    public String inicioAdmin() {
        return "admin/inicio";
    }

    
}