package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oferente_habilidad")
public class OferenteHabilidad {
    @EmbeddedId
    private OferenteHabilidadId id;

    @MapsId("idOferente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oferente", nullable = false)
    private Oferente idOferente;

    @MapsId("idCaracteristica")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_caracteristica", nullable = false)
    private Caracteristica idCaracteristica;

    @NotNull
    @Column(name = "nivel", nullable = false)
    private Integer nivel;

    public OferenteHabilidadId getId() {
        return id;
    }

    public void setId(OferenteHabilidadId id) {
        this.id = id;
    }

    public Oferente getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(Oferente idOferente) {
        this.idOferente = idOferente;
    }

    public Caracteristica getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Caracteristica idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public @NotNull Integer getNivel() {
        return nivel;
    }

    public void setNivel(@NotNull Integer nivel) {
        this.nivel = nivel;
    }
}