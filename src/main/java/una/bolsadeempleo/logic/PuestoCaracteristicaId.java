package una.bolsadeempleo.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PuestoCaracteristicaId implements java.io.Serializable {
    private static final long serialVersionUID = 1019097795472487762L;
    @NotNull
    @Column(name = "id_puesto", nullable = false)
    private Integer idPuesto;

    @NotNull
    @Column(name = "id_caracteristica", nullable = false)
    private Integer idCaracteristica;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PuestoCaracteristicaId entity = (PuestoCaracteristicaId) o;
        return Objects.equals(this.idPuesto, entity.idPuesto) &&
                Objects.equals(this.idCaracteristica, entity.idCaracteristica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPuesto, idCaracteristica);
    }

    public @NotNull Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(@NotNull Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public @NotNull Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(@NotNull Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }
}