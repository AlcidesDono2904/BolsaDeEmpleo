package una.bolsadeempleo.logic.services;

import org.springframework.stereotype.Service;
import una.bolsadeempleo.util.NacionalidadDataLoader;

import java.util.ArrayList;
import java.util.List;

@Service
public class NacionalidadService {

    public List<String> listarNacionalidades() {
        NacionalidadDataLoader nacionalidadDataLoader;
        List<String> nacionalidades = new ArrayList<>();
        try {
            nacionalidadDataLoader = new NacionalidadDataLoader();
            System.out.println("Cargando nacionalidades desde el archivo Excel...");
            nacionalidades = nacionalidadDataLoader.cargarNacionalidadesDelExcel();
            System.out.println(nacionalidades);
        } catch (Exception e) {
            System.err.println("Error al cargar las nacionalidades: " + e.getMessage());
            e.printStackTrace();
        }
        return nacionalidades;
    }
}
