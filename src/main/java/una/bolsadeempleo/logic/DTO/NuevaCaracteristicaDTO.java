package una.bolsadeempleo.logic.DTO;

public class NuevaCaracteristicaDTO {
    private String nombre;
    private Integer idPadre;

    public NuevaCaracteristicaDTO() {
    }

    public NuevaCaracteristicaDTO(String nombre, Integer idPadre) {
        this.nombre = nombre;
        this.idPadre = idPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }
}
