package una.bolsadeempleo.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Size(max = 255)
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 20)
    @NotNull
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @ColumnDefault("0")
    @Column(name = "aprobado")
    private Boolean aprobado;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_registro")
    private Instant fechaRegistro;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Administrador> administradors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Empresa> empresas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsuario")
    private Set<Oferente> oferentes = new LinkedHashSet<>();

    public Usuario() {
    }

    public Usuario(String correo, String passwordHash, String rol, Boolean aprobado, Instant fechaRegistro, Set<Administrador> administradors, Set<Empresa> empresas, Set<Oferente> oferentes) {
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.aprobado = aprobado;
        this.fechaRegistro = fechaRegistro;
        this.administradors = administradors;
        this.empresas = empresas;
        this.oferentes = oferentes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(max = 100) @NotNull String getCorreo() {
        return correo;
    }

    public void setCorreo(@Size(max = 100) @NotNull String correo) {
        this.correo = correo;
    }

    public @Size(max = 255) String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@Size(max = 255) String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public @Size(max = 20) @NotNull String getRol() {
        return rol;
    }

    public void setRol(@Size(max = 20) @NotNull String rol) {
        this.rol = rol;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Set<Administrador> getAdministradors() {
        return administradors;
    }

    public void setAdministradors(Set<Administrador> administradors) {
        this.administradors = administradors;
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Set<Oferente> getOferentes() {
        return oferentes;
    }

    public void setOferentes(Set<Oferente> oferentes) {
        this.oferentes = oferentes;
    }
}