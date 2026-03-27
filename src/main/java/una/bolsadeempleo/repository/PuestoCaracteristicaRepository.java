package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import una.bolsadeempleo.logic.PuestoCaracteristica;
import una.bolsadeempleo.logic.PuestoCaracteristicaId;

import java.util.Set;

@Repository
public interface PuestoCaracteristicaRepository
        extends JpaRepository<PuestoCaracteristica, PuestoCaracteristicaId> {
    Set<PuestoCaracteristica> findByIdPuestoId(Integer idPuesto);
}
