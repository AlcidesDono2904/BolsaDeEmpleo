package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
