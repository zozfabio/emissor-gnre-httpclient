package me.zozfabio.emissorgnre.emissaopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EmissaoPdfApplication {

    public static void main(String[] args) throws IOException {
        String resultadoLote = "RESULTADO";

        LoteGuiasGNRE lote = new LoteGuiasGNRE(resultadoLote);
        PDFLoteGuiasGNRE pdf = new PDFLoteGuiasGNRE(lote);

        File file = File.createTempFile("guia_gnre", ".pdf");

        try (FileOutputStream is = new FileOutputStream(file)) {
            Document documento = new Document(PageSize.A4, 10, 10, 10, 10);
            PdfWriter writer = PdfWriter.getInstance(documento, is);

            documento.open();
            pdf.addGuiasTo(documento, writer);
            documento.close();

            System.out.println(file.getAbsolutePath());
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
}
