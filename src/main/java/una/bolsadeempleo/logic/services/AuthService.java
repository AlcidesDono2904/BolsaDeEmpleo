package una.bolsadeempleo.logic.services;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import una.bolsadeempleo.logic.DTO.LoginRequestDTO;
import una.bolsadeempleo.logic.DTO.LoginResponseDTO;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.UsuarioRepository;
import una.bolsadeempleo.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo());
        if (usuario == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        boolean passwordCorrecta =
                passwordEncoder.matches(
                        request.getPassword(),
                        usuario.getPasswordHash()
                );
        if (!passwordCorrecta) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token =
                jwtService.generateToken(
                        usuario.getCorreo(),
                        usuario.getRol()
                );

        return new LoginResponseDTO(token);
    }
}