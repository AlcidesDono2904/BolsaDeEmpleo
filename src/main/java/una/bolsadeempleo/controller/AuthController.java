package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.logic.Service;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private Service service;

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {

        return service.login(
                usuario.getCorreo(),
                usuario.getPasswordHash()
        );
    }
}
