package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Size(max = 150)
    @Column(name = "localizacion", length = 150)
    private String localizacion;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "idEmpresa")
    private Set<Puesto> puestos = new LinkedHashSet<>();

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

    public @Size(max = 150) @NotNull String getNombre() {
        return nombre;
    }

    public void setNombre(@Size(max = 150) @NotNull String nombre) {
        this.nombre = nombre;
    }

    public @Size(max = 150) String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(@Size(max = 150) String localizacion) {
        this.localizacion = localizacion;
    }

    public @Size(max = 20) String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Size(max = 20) String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(Set<Puesto> puestos) {
        this.puestos = puestos;
    }
}