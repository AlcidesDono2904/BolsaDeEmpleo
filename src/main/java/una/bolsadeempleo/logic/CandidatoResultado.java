package una.bolsadeempleo.logic;

import lombok.Getter;
import lombok.Setter;

// DTO para almacenar el resultado de la compatibilidad de un oferente con un puesto de trabajo
@Getter
@Setter
public class CandidatoResultado {

    private Oferente oferente;
    private Double porcentajeCompatibilidad;
    private Integer requisitosAlcanzados;

    public CandidatoResultado() {
    }

    public CandidatoResultado(Oferente oferente, Double porcentajeCompatibilidad, Integer requisitosAlcanzados) {
        this.oferente = oferente;
        this.porcentajeCompatibilidad = porcentajeCompatibilidad;
        this.requisitosAlcanzados = requisitosAlcanzados;
    }
}
