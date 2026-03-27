package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Oferente;

import java.util.List;

public interface OferenteRepository extends JpaRepository<Oferente, Integer> {
    List<Oferente> findByIdUsuarioAprobadoFalse();

    List<Oferente> findByIdUsuarioAprobadoTrue();

    Oferente findByIdUsuarioId(Integer usuarioId);
}
