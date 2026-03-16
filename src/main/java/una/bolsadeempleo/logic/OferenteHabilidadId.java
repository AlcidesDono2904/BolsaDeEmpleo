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
public class OferenteHabilidadId implements java.io.Serializable {
    private static final long serialVersionUID = 7428424744712353926L;
    @NotNull
    @Column(name = "id_oferente", nullable = false)
    private Integer idOferente;

    @NotNull
    @Column(name = "id_caracteristica", nullable = false)
    private Integer idCaracteristica;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OferenteHabilidadId entity = (OferenteHabilidadId) o;
        return Objects.equals(this.idCaracteristica, entity.idCaracteristica) &&
                Objects.equals(this.idOferente, entity.idOferente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaracteristica, idOferente);
    }

    public @NotNull Integer getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(@NotNull Integer idOferente) {
        this.idOferente = idOferente;
    }

    public @NotNull Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(@NotNull Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }
}