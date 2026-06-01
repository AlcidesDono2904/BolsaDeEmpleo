package una.bolsadeempleo.logic.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OferenteHabilidadDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    private Integer padre;
    @NotNull
    private Integer nivel;

    public OferenteHabilidadDTO(Integer id, String nombre, Integer padre, Integer nivel) {
        this.id = id;
        this.nombre = nombre;
        this.padre = padre;
        this.nivel = nivel;
    }

    public OferenteHabilidadDTO() {
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
