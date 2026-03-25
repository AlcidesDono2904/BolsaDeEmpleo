package una.bolsadeempleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import una.bolsadeempleo.logic.Empresa;
import una.bolsadeempleo.logic.Puesto;

import java.util.List;

public interface PuestoRepository extends JpaRepository<Puesto, Integer> {

    // últimos 5 puestos públicos
    List<Puesto> findTop5ByTipoPublicacionAndActivoOrderByFechaPublicacionDesc(String tipo, Boolean activo);
    // búsqueda simple
    List<Puesto> findByDescripcionContainingIgnoreCaseAndTipoPublicacion(String descripcion, String tipo);
    List<Puesto> findByDescripcionContainingIgnoreCase(String texto);
    List<Puesto> findByIdEmpresa(Empresa empresa);
    @Query("SELECT DISTINCT p FROM Puesto p " +
            "JOIN p.puestoCaracteristicas pc " +
            "WHERE pc.idCaracteristica.id IN :ids")
    List<Puesto> findDistinctByPuestoCaracteristicas_IdCaracteristica_IdIn(@Param("ids") List<Integer> ids);

    @Query("SELECT DISTINCT p FROM Puesto p JOIN p.puestoCaracteristicas pc WHERE pc.idCaracteristica.id IN :ids")
    List<Puesto> findByCaracteristicasIds(@Param("ids") List<Integer> ids);
}