package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
