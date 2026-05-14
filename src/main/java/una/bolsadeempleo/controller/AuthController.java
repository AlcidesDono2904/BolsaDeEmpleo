package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.logic.Service;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private Service service;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {

        Usuario u = service.login(
                usuario.getCorreo(),
                usuario.getPasswordHash()
        );

        if (u == null) {
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(u);
    }
}
