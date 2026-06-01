package una.bolsadeempleo.logic.DTO;

public class OferenteHabilidadNuevaDTO {
    private int idCaracteristica;
    private int nivel;

    public OferenteHabilidadNuevaDTO() {
    }

    public OferenteHabilidadNuevaDTO(int idCaracteristica, int nivel) {
        this.idCaracteristica = idCaracteristica;
        this.nivel = nivel;
    }

    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}