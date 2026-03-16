package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "candidatura")
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidatura", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oferente", nullable = false)
    private Oferente idOferente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto idPuesto;

    @Column(name = "porcentaje_coincidencia", precision = 5, scale = 2)
    private BigDecimal porcentajeCoincidencia;

    @Getter
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPorcentajeCoincidencia() {
        return porcentajeCoincidencia;
    }

    public void setPorcentajeCoincidencia(BigDecimal porcentajeCoincidencia) {
        this.porcentajeCoincidencia = porcentajeCoincidencia;
    }

    public @NotNull Puesto getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(@NotNull Puesto idPuesto) {
        this.idPuesto = idPuesto;
    }

    public @NotNull Oferente getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(@NotNull Oferente idOferente) {
        this.idOferente = idOferente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}