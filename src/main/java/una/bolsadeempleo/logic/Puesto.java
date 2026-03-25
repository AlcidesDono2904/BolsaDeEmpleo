package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "puesto")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa idEmpresa;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "salario_usd", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioUsd;

    @Size(max = 20)
    @NotNull
    @Column(name = "tipo_publicacion", nullable = false, length = 20)
    private String tipoPublicacion;

    @ColumnDefault("1")
    @Column(name = "activo")
    private Boolean activo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

    @OneToMany(mappedBy = "idPuesto")
    private Set<Candidatura> candidaturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPuesto")
    private Set<PuestoCaracteristica> puestoCaracteristicas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(@NotNull Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public @NotNull String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull BigDecimal getSalarioUsd() {
        return salarioUsd;
    }

    public void setSalarioUsd(@NotNull BigDecimal salarioUsd) {
        this.salarioUsd = salarioUsd;
    }

    public @Size(max = 20) @NotNull String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(@Size(max = 20) @NotNull String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Instant getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Instant fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public Set<PuestoCaracteristica> getPuestoCaracteristicas() {
        return puestoCaracteristicas;
    }

    public void setPuestoCaracteristicas(Set<PuestoCaracteristica> puestoCaracteristicas) {
        this.puestoCaracteristicas = puestoCaracteristicas;
    }

    public String getCaracteristicasTexto() {
        if (puestoCaracteristicas == null) return "";

        return puestoCaracteristicas.stream()
                .map(pc -> pc.getIdCaracteristica().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}