package com.example.ensolapp.Utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.ensolapp.Models.VisitaTecnica;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class GerarPDF {

    public static void gerarPDF(Context context, VisitaTecnica visitaTecnica) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Visita_" + visitaTecnica.getCliente().get("nomeCliente") + ".pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Text title = new Text("Visita técnica ensol").setTextAlignment(TextAlignment.CENTER);

        Paragraph p = new Paragraph();
        p.add(title);

        document.add(p);
        document.close();
        Toast.makeText(context, "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
