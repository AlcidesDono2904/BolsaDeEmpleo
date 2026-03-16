package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 50)
    @NotNull
    @Column(name = "identificacion", nullable = false, length = 50)
    private String identificacion;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    public Administrador(Integer id, Usuario idUsuario, String identificacion, String nombre) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public Administrador() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(@NotNull Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @Size(max = 50) @NotNull String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(@Size(max = 50) @NotNull String identificacion) {
        this.identificacion = identificacion;
    }

    public @Size(max = 100) @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@Size(max = 100) @NotNull String nombre) {
        this.nombre = nombre;
    }
}