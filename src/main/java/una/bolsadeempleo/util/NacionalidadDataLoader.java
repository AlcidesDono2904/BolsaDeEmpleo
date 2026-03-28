package una.bolsadeempleo.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class NacionalidadDataLoader {

    public List<String> cargarNacionalidadesDelExcel() {
        List<String> nacionalidades = new ArrayList<>();

        try {
            // Obtener el archivo desde resources/data/nacionalidades.xlsx
            ClassPathResource resource = new ClassPathResource("data/nacionalidades.xlsx");
            InputStream inputStream = resource.getInputStream();

            // Crear workbook desde el stream
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0); // Obtener la primera hoja

            // Iterar sobre las filas (comenzando desde la fila 1 para saltar el encabezado)
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                var row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                // Obtener el valor de la segunda columna (nombre de la nacionalidad)
                try {
                    String nombreNacionalidad = row.getCell(1).getStringCellValue();

                    if (nombreNacionalidad != null && !nombreNacionalidad.trim().isEmpty()) {
                        nacionalidades.add(nombreNacionalidad.trim());
                    }
                } catch (Exception e) {
                    // Si hay un error al leer la celda, continuamos con la siguiente fila
                    continue;
                }
            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de nacionalidades: " + e.getMessage());
            e.printStackTrace();
        }

        return nacionalidades;
    }
}
