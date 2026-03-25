package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.OferenteHabilidad;
import una.bolsadeempleo.logic.OferenteHabilidadId;

import java.util.List;

public interface OferenteHabilidadRepository
        extends JpaRepository<OferenteHabilidad, OferenteHabilidadId> {

    List<OferenteHabilidad> findByIdOferenteId(Integer idOferente);
}
