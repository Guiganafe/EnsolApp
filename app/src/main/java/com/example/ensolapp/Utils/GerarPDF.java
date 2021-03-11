package com.example.ensolapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;

public class GerarPDF {

    public static void gerarPDF(Context context, VisitaTecnica visitaTecnica, Fotos fotos) throws FileNotFoundException {

        Bitmap foto_padrao, foto_acesso_telhado, foto_orientacao_telhado;

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Visita_" + visitaTecnica.getCliente().get("nomeCliente") + ".pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Text title = new Text("www.ensol.eco.br").setTextAlignment(TextAlignment.CENTER);
        Paragraph p = new Paragraph(title);
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
        document.add(logoImg);

        Text subtitle = new Text("Visita técnica para fechamento de contrato").setTextAlignment(TextAlignment.CENTER);
        Paragraph p_sub = new Paragraph(subtitle);
        p.setTextAlignment(TextAlignment.CENTER);
        document.add(p_sub);

        Text cliente_title = new Text("Informações sobre o cliente").setTextAlignment(TextAlignment.CENTER);
        Paragraph p_cliente = new Paragraph(cliente_title);
        p.setTextAlignment(TextAlignment.CENTER);
        document.add(p_cliente);

        if(visitaTecnica.getDataVisita() != null){
            int dia = visitaTecnica.getDataVisita().getDay(), mes = visitaTecnica.getDataVisita().getMonth(), ano = visitaTecnica.getDataVisita().getYear();
            Paragraph data = new Paragraph(String.format(Locale.getDefault(),"Data da visita: %02d/%02d/%04d", dia, mes+1, ano));
            document.add(data);
        }

        if(visitaTecnica.getCliente().get("tipoCliente") != null){
            Paragraph paragraph = new Paragraph("Tipo de cliente: " + visitaTecnica.getCliente().get("tipoCliente"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("nomeCliente") != null){
            Paragraph paragraph = new Paragraph("Nome do cliente: " + visitaTecnica.getCliente().get("nomeCliente"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("razaoSocial") != null){
            Paragraph paragraph = new Paragraph("Razão social: " + visitaTecnica.getCliente().get("razaoSocial"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("cpf_cnpj") != null){
            Paragraph paragraph = new Paragraph("CPF/CNPJ: " + visitaTecnica.getCliente().get("cpf_cnpj"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("responsavel") != null){
            Paragraph paragraph = new Paragraph("Responsavel: " + visitaTecnica.getCliente().get("responsavel"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("telefone") != null){
            Paragraph paragraph = new Paragraph("Telefone: " + visitaTecnica.getCliente().get("telefone"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("email") != null){
            Paragraph paragraph = new Paragraph("E-mail: " + visitaTecnica.getCliente().get("email"));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("endereco") != null){
            Paragraph paragraph = new Paragraph("Endereco: " + visitaTecnica.getCliente().get("endereco"));
            document.add(paragraph);
        }

        if(visitaTecnica.getPadraoEntrada() != null){
            Paragraph paragraph = new Paragraph("Padrão de entrada: " + visitaTecnica.getPadraoEntrada());
            document.add(paragraph);
        }

        if(fotos.getFoto_padrao() != null){
            //Configuração de exibição da foto
            foto_padrao = fotos.getFoto_padrao();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_padrao.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph("Foto do padrão de entrada:");
            document.add(p_foto_padrao);
            //Insere a imagem no PDF
            document.add(img);
        }

        if(visitaTecnica.getAmperagemDisjuntosEntrada() != null){
            Paragraph paragraph = new Paragraph("Amperagem do disjuntor de entrada do padrão: " + visitaTecnica.getAmperagemDisjuntosEntrada());
            document.add(paragraph);
        }

        if(visitaTecnica.getCondicaoPadraoEntrada() != null){
            Paragraph paragraph = new Paragraph("Condição do padrão de entrada: " + visitaTecnica.getCondicaoPadraoEntrada());
            document.add(paragraph);
        }

        if(visitaTecnica.getLocalInstalacaoModulos() != null){
            Paragraph paragraph = new Paragraph("Local de instalação dos modulos: " + visitaTecnica.getLocalInstalacaoModulos());
            document.add(paragraph);
        }

        if(visitaTecnica.getMaterialEstruturaTelhado() != null){
            Paragraph paragraph = new Paragraph("Material da estrutura do telhado: " + visitaTecnica.getMaterialEstruturaTelhado());
            document.add(paragraph);
        }

        if(visitaTecnica.getCondicaoTelhado() != null){
            Paragraph paragraph = new Paragraph("Condição do telhado: " + visitaTecnica.getCondicaoTelhado());
            document.add(paragraph);
        }

        if(visitaTecnica.getOrientacaoTelhado() != null){
            Paragraph paragraph = new Paragraph("Orientção do telhado: " + visitaTecnica.getOrientacaoTelhado());
            document.add(paragraph);
        }

        if(fotos.getFoto_orientacao_telhado() != null){
            //Configuração de exibição da foto
            foto_orientacao_telhado = fotos.getFoto_orientacao_telhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_orientacao_telhado.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere o título da imagem no PDF
            Paragraph p_foto_orientacao = new Paragraph("Foto da orientação do telhado:");
            document.add(p_foto_orientacao);
            //Insere a imagem no PDF
            document.add(img);
        }

        Paragraph p_util = new Paragraph("Dimenssões úteis");
        document.add(p_util);

        if(visitaTecnica.getLarguraTelhado() != null){
            Paragraph paragraph = new Paragraph("Largura do telhado: " + visitaTecnica.getLarguraTelhado() + " metros");
            document.add(paragraph);
        }

        if(visitaTecnica.getComprimentoTelhado() != null){
            Paragraph paragraph = new Paragraph("Comprimento do telhado: " + visitaTecnica.getComprimentoTelhado() + " metros");
            document.add(paragraph);
        }

        if(visitaTecnica.getAlturaTelhado() != null){
            Paragraph paragraph = new Paragraph("Altura do telhado: " + visitaTecnica.getAlturaTelhado() + " metros");
            document.add(paragraph);
        }

        Paragraph p_acesso = new Paragraph("Acesso ao telhado");
        document.add(p_acesso);

        if(visitaTecnica.getAcessoEscada() != null){
            Paragraph paragraph = new Paragraph("Escada (até 5 metros): " + visitaTecnica.getAcessoEscada());
            document.add(paragraph);
        }

        if(visitaTecnica.getAcessoAndaime() != null){
            Paragraph paragraph = new Paragraph("Andaime (acima de 5 metros): " + visitaTecnica.getAcessoAndaime());
            document.add(paragraph);
        }

        if(fotos.getFoto_acesso_telhado() != null){
            //Configuração de exibição da foto
            foto_acesso_telhado = fotos.getFoto_acesso_telhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_acesso_telhado.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere o título da imagem no PDF
            Paragraph p_foto_acesso = new Paragraph("Foto do acesso ao telhado:");
            document.add(p_foto_acesso);
            //Insere a imagem no PDF
            document.add(img);
        }

        if(visitaTecnica.getObsFinais() != null){
            Paragraph paragraph = new Paragraph("Observações finais: " + visitaTecnica.getObsFinais());
            document.add(paragraph);
        }

        document.close();
        Toast.makeText(context, "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
