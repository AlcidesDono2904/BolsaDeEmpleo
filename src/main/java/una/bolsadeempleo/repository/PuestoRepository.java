package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import una.bolsadeempleo.logic.Puesto;

import java.util.List;

public interface PuestoRepository extends JpaRepository<Puesto, Integer> {

    // últimos 5 puestos públicos
    List<Puesto> findTop5ByTipoPublicacionAndActivoOrderByFechaPublicacionDesc(String tipo, Boolean activo);

    // búsqueda simple
    List<Puesto> findByDescripcionContainingIgnoreCaseAndTipoPublicacion(String descripcion, String tipo);

    List<Puesto> findByDescripcionContainingIgnoreCase(String texto);
}