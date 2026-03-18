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
@Table(name = "oferente")
public class Oferente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferente", nullable = false)
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

    @Size(max = 100)
    @NotNull
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 150)
    @Column(name = "residencia", length = 150)
    private String residencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nacionalidad")
    private Nacionalidad idNacionalidad;

    @OneToMany(mappedBy = "idOferente")
    private Set<Candidatura> candidaturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOferente")
    private Set<Cv> cvs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOferente")
    private Set<OferenteHabilidad> oferenteHabilidads = new LinkedHashSet<>();

    public Oferente() {
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

    public @Size(max = 100) @NotNull String getApellido() {
        return apellido;
    }

    public void setApellido(@Size(max = 100) @NotNull String apellido) {
        this.apellido = apellido;
    }

    public @Size(max = 20) String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Size(max = 20) String telefono) {
        this.telefono = telefono;
    }

    public @Size(max = 150) String getResidencia() {
        return residencia;
    }

    public void setResidencia(@Size(max = 150) String residencia) {
        this.residencia = residencia;
    }

    public Nacionalidad getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Nacionalidad idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public Set<Cv> getCvs() {
        return cvs;
    }

    public void setCvs(Set<Cv> cvs) {
        this.cvs = cvs;
    }

    public Set<OferenteHabilidad> getOferenteHabilidads() {
        return oferenteHabilidads;
    }

    public void setOferenteHabilidads(Set<OferenteHabilidad> oferenteHabilidads) {
        this.oferenteHabilidads = oferenteHabilidads;
    }


}