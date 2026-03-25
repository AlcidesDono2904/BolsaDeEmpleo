package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreoAndPasswordHash(String correo, String password);
    List<Usuario> findByRolAndAprobado(String rol, Boolean aprobado);

}
