package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.DTO.LoginRequestDTO;
import una.bolsadeempleo.logic.DTO.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import una.bolsadeempleo.logic.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
/*
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        sesion.logout();
        return ResponseEntity.ok().build();
    }*/
}
