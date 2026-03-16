package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cv")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cv", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oferente", nullable = false)
    private Oferente idOferente;

    @Size(max = 255)
    @NotNull
    @Column(name = "ruta_archivo", nullable = false)
    private String rutaArchivo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_subida")
    private Instant fechaSubida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Oferente getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(@NotNull Oferente idOferente) {
        this.idOferente = idOferente;
    }

    public @Size(max = 255) @NotNull String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(@Size(max = 255) @NotNull String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Instant getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Instant fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}