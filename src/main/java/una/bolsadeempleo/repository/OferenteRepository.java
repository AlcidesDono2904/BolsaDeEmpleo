package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Oferente;

public interface OferenteRepository extends JpaRepository<Oferente, Integer> {

    Oferente findByIdUsuarioId(Integer idUsuario);
}
