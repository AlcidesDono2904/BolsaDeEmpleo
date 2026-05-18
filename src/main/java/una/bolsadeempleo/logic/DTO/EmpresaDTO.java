package una.bolsadeempleo.logic.DTO;

public class EmpresaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String localizacion;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Integer id,String nombre, String descripcion, String localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.localizacion = localizacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
