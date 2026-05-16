package una.bolsadeempleo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.UsuarioRepository;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }

        return new User(
                usuario.getCorreo(),
                usuario.getPasswordHash(),
                List.of(
                        () -> "ROLE_" + usuario.getRol()
                )
        );
    }
}