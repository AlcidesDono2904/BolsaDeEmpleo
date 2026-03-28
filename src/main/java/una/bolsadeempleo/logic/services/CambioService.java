package una.bolsadeempleo.logic.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.TipoCambioDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CambioService {
    private final String URL = "https://api.hacienda.go.cr/indicadores/tc/dolar";

    public double obtenerTipoCambioVenta() {
        RestTemplate restTemplate = new RestTemplate();

        TipoCambioDTO response = restTemplate.getForObject(URL, TipoCambioDTO.class);

        return response.getVenta().getValor();
    }

    public ArrayList<Double> calcularVenta(List<Puesto> puestos){
        ArrayList<Double> salariosConvertidos = new ArrayList<>();
        Double tipoCambioVenta = obtenerTipoCambioVenta();
        for(Puesto p : puestos){
            double salarioEnColones = p.getSalarioUsd().doubleValue() * tipoCambioVenta;
            salariosConvertidos.add(salarioEnColones);
        }
        return salariosConvertidos;
    }
}
