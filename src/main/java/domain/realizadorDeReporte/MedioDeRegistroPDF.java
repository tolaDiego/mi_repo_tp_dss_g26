package domain.realizadorDeReporte;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MedioDeRegistroPDF implements   IMedioDeRegistro{

    @Override
    public void registrarReporte(String rutaArchivo,CalculadorDatosDeReporte datos) throws FileNotFoundException {
        // Crear un escritor de PDF
        String ruta=rutaArchivo
                + LocalDate.now().getYear()
                +"-"+LocalDate.now().getMonth()
                +"-"+LocalDate.now().getDayOfMonth()
                +"_hora_"+ LocalTime.now().getHour()
                +"_"+ LocalTime.now().getMinute()
                +".pdf";
        PdfWriter writer = new PdfWriter(ruta);

        // Crear un documento PDF
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph(" Viandas Colocadas y Retiradas"));
        // Crear una tabla
        float[] columnasTabla = {200f, 200f, 200f};
        Table table = new Table(columnasTabla);

        // Agregar encabezados a la tabla
        table.addCell("Nombre Ubicacion");
        table.addCell("Colocadas");
        table.addCell("Retiradas");

        // Llenar la tabla con ViandasColocadasRetiradas
        for (ViandasColocadasRetiradas item : datos.contarViandasColocadasRetiradas()) {
            table.addCell(item.getNombreUbicacion());
            table.addCell(String.valueOf(item.getColocadas()));
            table.addCell(String.valueOf(item.getRetiradas()));
        }

        // Agregar la tabla al documento
        document.add(table);
        /* ********************************************************************** */
        document.add(new Paragraph("\n Viandas Donadas"));
        Table tablaColaborador = new Table(new float[]{200f, 200f});
        // Agregar encabezados a la tabla
        tablaColaborador.addCell("Nombre ");
        tablaColaborador.addCell("Viandas ");

        // Llenar la tabla con contarViandasPorColaborador
        for (ViandasPorColaborador item : datos.contarViandasPorColaborador()) {
            tablaColaborador.addCell(item.getNombre());
            tablaColaborador.addCell(String.valueOf(item.getViandas()));

        }
        // Agregar la tabla al documento
        document.add(tablaColaborador);

        /* ********************************************************************** */
        document.add(new Paragraph("\n Incidentes de heladeras"));
        Table tablaIncidentes = new Table(new float[]{200f, 200f,200f});
        // Agregar encabezados a la tabla
        tablaIncidentes.addCell("Nombre Ubicacion");
        tablaIncidentes.addCell("Direccion");
        tablaIncidentes.addCell("incidentes");

        // Llenar la tabla con ViandasColocadasRetiradas
        for (IncidentePorHeladera item : datos.contarIncidentesPorHeladera()) {
            tablaIncidentes.addCell(item.getNombreUbicacion());
            tablaIncidentes.addCell(item.getDireccion());
            tablaIncidentes.addCell(String.valueOf(item.getIncidentes()));
        }

        // Agregar la tabla al documento
        document.add(tablaIncidentes);

        // Cerrar el documento
        document.close();
    }

}
