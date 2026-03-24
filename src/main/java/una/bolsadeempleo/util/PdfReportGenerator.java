package una.bolsadeempleo.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import una.bolsadeempleo.logic.Empresa;
import una.bolsadeempleo.logic.Puesto;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PdfReportGenerator {

    public static byte[] generarReportePuestos(List<Puesto> puestos, Integer mes, Integer anio) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Título
        document.add(new Paragraph("REPORTE DE PUESTOS")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18));

        // Subtítulo con mes y año
        String mesNombre = obtenerNombreMes(mes);
        document.add(new Paragraph(mesNombre + " de " + anio)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12));

        document.add(new Paragraph("\n"));

        // Agrupar puestos por empresa
        Map<Empresa, List<Puesto>> puestosAgrupados = puestos.stream()
                .collect(Collectors.groupingBy(Puesto::getIdEmpresa));

        // Si no hay puestos, mostrar mensaje
        if (puestosAgrupados.isEmpty()) {
            document.add(new Paragraph("No hay puestos publicados en el período seleccionado.")
                    .setTextAlignment(TextAlignment.CENTER));
        } else {
            // Iterar sobre empresas
            for (Map.Entry<Empresa, List<Puesto>> entry : puestosAgrupados.entrySet()) {
                Empresa empresa = entry.getKey();
                List<Puesto> puestosEmpresa = entry.getValue();

                // Encabezado de empresa
                document.add(new Paragraph("EMPRESA: " + empresa.getNombre())
                        .setFontSize(14)
                        .setBold());

                document.add(new Paragraph("Ubicación: " + (empresa.getLocalizacion() != null ? empresa.getLocalizacion() : "No especificada"))
                        .setFontSize(10));

                document.add(new Paragraph("\n"));

                // Tabla de puestos
                Table table = new Table(5);
                table.addHeaderCell("ID").setBold();
                table.addHeaderCell("Descripción").setBold();
                table.addHeaderCell("Salario USD").setBold();
                table.addHeaderCell("Tipo").setBold();
                table.addHeaderCell("Fecha Publicación").setBold();

                for (Puesto puesto : puestosEmpresa) {
                    table.addCell(String.valueOf(puesto.getId()));
                    table.addCell(puesto.getDescripcion());
                    table.addCell("$" + puesto.getSalarioUsd());
                    table.addCell(puesto.getTipoPublicacion());
                    table.addCell(formatearFecha(puesto.getFechaPublicacion()));
                }

                document.add(table);
                document.add(new Paragraph("\n"));
            }

            // Footer
            document.add(new Paragraph("Total de puestos: " + puestos.size())
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10));
        }

        document.close();
        return baos.toByteArray();
    }

    private static String obtenerNombreMes(Integer mes) {
        String[] meses = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return mes >= 1 && mes <= 12 ? meses[mes] : "Mes inválido";
    }

    private static String formatearFecha(Instant fecha) {
        if (fecha == null) return "Sin fecha";
        return fecha.atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString();
    }
}
