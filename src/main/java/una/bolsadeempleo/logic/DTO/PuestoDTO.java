package una.bolsadeempleo.logic.DTO;

public class PuestoDTO {
    private Integer id;
    private String descripcion;
    private String nombreEmpresa;
    private Double salarioUsd;
    private Double salarioColones;

    public PuestoDTO(Integer id, String descripcion, String nombreEmpresa, Double salarioUsd, Double salarioColones) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombreEmpresa = nombreEmpresa;
        this.salarioUsd = salarioUsd;
        this.salarioColones = salarioColones;
    }

    public PuestoDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Double getSalarioUsd() {
        return salarioUsd;
    }

    public void setSalarioUsd(Double salarioUsd) {
        this.salarioUsd = salarioUsd;
    }

    public Double getSalarioColones() {
        return salarioColones;
    }

    public void setSalarioColones(Double salarioColones) {
        this.salarioColones = salarioColones;
    }
}
