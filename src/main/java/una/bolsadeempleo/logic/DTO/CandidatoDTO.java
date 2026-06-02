package una.bolsadeempleo.logic.DTO;

public class CandidatoDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Double porcentajeCompatibilidad;
    private Integer requisitosAlcanzados;

    public CandidatoDTO() {
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

    public Double getPorcentajeCompatibilidad() {
        return porcentajeCompatibilidad;
    }

    public void setPorcentajeCompatibilidad(Double porcentajeCompatibilidad) {
        this.porcentajeCompatibilidad = porcentajeCompatibilidad;
    }

    public Integer getRequisitosAlcanzados() {
        return requisitosAlcanzados;
    }

    public void setRequisitosAlcanzados(Integer requisitosAlcanzados) {
        this.requisitosAlcanzados = requisitosAlcanzados;
    }
}
