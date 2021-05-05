package com.example.ensolapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.R;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class GerarPDFOrcamento {
    public static void gerarPDF(Context context, Orcamento orcamento, Bitmap foto) throws FileNotFoundException {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Orçamento_" + orcamento.getNomeCliente() + ".pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Text url = new Text("www.ensol.eco.br").setFontSize(12);
        Paragraph p = new Paragraph(url);
        p.setTextAlignment(TextAlignment.CENTER);
        document.add(p);

        //Adiciona a logo ao PDF
        Drawable logo = context.getDrawable(R.drawable.logo_ensol);
        Bitmap logoBitmap = ((BitmapDrawable)logo).getBitmap();
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] logoData = ByteStream.toByteArray();
        ImageData logoDataImg = ImageDataFactory.create(logoData);
        Image logoImg = new Image(logoDataImg);
        logoImg.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(logoImg);

        Text subtitle = new Text("Solicitação de orçamento").setFontSize(22);
        Paragraph p_sub = new Paragraph(subtitle);
        p_sub.setTextAlignment(TextAlignment.CENTER);
        document.add(p_sub);

        if(orcamento.getDataSolicitacao() != null){
            int dia = orcamento.getDataSolicitacao().getDay(), mes = orcamento.getDataSolicitacao().getMonth(), ano = orcamento.getDataSolicitacao().getYear();
            Paragraph data = new Paragraph(new Text(String.format(Locale.getDefault(),"Data da visita: %02d/%02d/%04d", dia, mes+1, ano)).setFontSize(20));
            document.add(data);
        }

        if(orcamento.getNomeCliente() != null){
            Paragraph paragraph = new Paragraph(new Text("Nome do cliente: " + orcamento.getNomeCliente()).setFontSize(20));
            document.add(paragraph);
        }

        if(orcamento.getContato() != null){
            Paragraph paragraph = new Paragraph(new Text("Contato: " + orcamento.getContato()).setFontSize(20));
            document.add(paragraph);
        }

        if(orcamento.getPotenciaDesejada() != null){
            Paragraph paragraph = new Paragraph(new Text("Potência de geração desejada: " + orcamento.getPotenciaDesejada()).setFontSize(20));
            document.add(paragraph);
        }

        if(orcamento.getLocalizacao() != null){
            Paragraph paragraph = new Paragraph(new Text("Localização: " + orcamento.getLocalizacao()).setFontSize(20));
            document.add(paragraph);
        }

        if(orcamento.getObservacao() != null){
            Paragraph paragraph = new Paragraph(new Text("Observação: " + orcamento.getObservacao()).setFontSize(20));
            document.add(paragraph);
        }

        if(foto != null){
            //Configuração de exibição da foto
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Foto da conta de energia").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        document.close();
        Toast.makeText(context, "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();

    }
}
