package una.bolsadeempleo.logic.DTO;
import java.util.List;

public class OferenteDetalleDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String residencia;
    private String correo;

    private List<HabilidadDTO> habilidades;

    public OferenteDetalleDTO() {
    }

    public List<HabilidadDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}