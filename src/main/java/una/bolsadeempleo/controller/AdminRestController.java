package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.SesionUsuarioBean;
import una.bolsadeempleo.logic.Usuario;


@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private Service service;

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok("Lista de usuarios");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

