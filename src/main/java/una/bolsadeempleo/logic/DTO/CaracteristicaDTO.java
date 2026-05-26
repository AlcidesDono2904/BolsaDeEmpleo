package una.bolsadeempleo.logic.DTO;

public class CaracteristicaDTO {
    private Integer id;
    private String nombre;
    private Integer padre;

    public CaracteristicaDTO(Integer id, String nombre, Integer padre) {
        this.id = id;
        this.nombre = nombre;
        this.padre = padre;
    }

    public CaracteristicaDTO() {
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

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }
}
