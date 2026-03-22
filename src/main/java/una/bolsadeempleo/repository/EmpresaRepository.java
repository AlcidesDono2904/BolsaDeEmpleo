package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Empresa;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List<Empresa> findByIdUsuarioAprobadoFalse();
}
