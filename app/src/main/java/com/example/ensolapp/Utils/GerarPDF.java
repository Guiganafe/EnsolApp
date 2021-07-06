package com.example.ensolapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.SparseArray;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class GerarPDF {

    private ProgressBar progressBar;

    public static void gerarPDF(Context context, VisitaTecnica visitaTecnica, Fotos fotos) throws IOException {

        Bitmap foto_padrao, foto_acesso_telhado, foto_orientacao_telhado, foto_inversor;

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Visita_" + visitaTecnica.getCliente().get("nomeCliente") + ".pdf");
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

        Text subtitle = new Text("Visita técnica para fechamento de contrato").setFontSize(22);
        Paragraph p_sub = new Paragraph(subtitle);
        p_sub.setTextAlignment(TextAlignment.CENTER);
        document.add(p_sub);

        Text cliente_title = new Text("Informações sobre o cliente").setFontSize(20);
        Paragraph p_cliente = new Paragraph(cliente_title);
        p_cliente.setTextAlignment(TextAlignment.CENTER);
        document.add(p_cliente);

        if(visitaTecnica.getDataVisita() != null){
            int dia = visitaTecnica.getDataVisita().getDay(), mes = visitaTecnica.getDataVisita().getMonth(), ano = visitaTecnica.getDataVisita().getYear();
            Paragraph data = new Paragraph(new Text(String.format(Locale.getDefault(),"Data da visita: %02d/%02d/%04d", dia, mes+1, ano)).setFontSize(20));
            document.add(data);
        }

        if(visitaTecnica.getCliente().get("tipoCliente") != null){
            Paragraph paragraph = new Paragraph(new Text("Tipo de cliente: " + visitaTecnica.getCliente().get("tipoCliente")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("nomeCliente") != null){
            Paragraph paragraph = new Paragraph(new Text("Nome do cliente: " + visitaTecnica.getCliente().get("nomeCliente")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("razaoSocial") != null){
            Paragraph paragraph = new Paragraph(new Text("Razão social: " + visitaTecnica.getCliente().get("razaoSocial")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("cpf_cnpj") != null){
            Paragraph paragraph = new Paragraph(new Text("CPF/CNPJ: " + visitaTecnica.getCliente().get("cpf_cnpj")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("responsavel") != null){
            Paragraph paragraph = new Paragraph(new Text("Responsavel: " + visitaTecnica.getCliente().get("responsavel")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("telefone") != null){
            Paragraph paragraph = new Paragraph(new Text("Telefone: " + visitaTecnica.getCliente().get("telefone")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("email") != null){
            Paragraph paragraph = new Paragraph(new Text("E-mail: " + visitaTecnica.getCliente().get("email")).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCliente().get("endereco") != null){
            Paragraph paragraph = new Paragraph(new Text("Endereco: " + visitaTecnica.getCliente().get("endereco")).setFontSize(20));
            document.add(paragraph);
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        Text ambiente_title = new Text("Informações sobre o ambiente").setFontSize(20);
        Paragraph p_ambiente = new Paragraph(ambiente_title);
        p_ambiente.setTextAlignment(TextAlignment.CENTER);
        document.add(p_ambiente);

        if(visitaTecnica.getPadraoEntrada() != null){
            Paragraph paragraph = new Paragraph(new Text("Padrão de entrada: " + visitaTecnica.getPadraoEntrada()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getAmperagemDisjuntosEntrada() != null){
            Paragraph paragraph = new Paragraph(new Text("Amperagem do disjuntor de entrada do padrão: " + visitaTecnica.getAmperagemDisjuntosEntrada()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getNumeroUc() != null){
            Paragraph paragraph = new Paragraph(new Text("Número UC: " + visitaTecnica.getNumeroUc()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCondicaoPadraoEntrada() != null){
            Paragraph paragraph = new Paragraph(new Text("Condição do padrão de entrada: " + visitaTecnica.getCondicaoPadraoEntrada()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getTipoRamal() != null){
            Paragraph paragraph = new Paragraph(new Text("Tipo do ramal: " + visitaTecnica.getTipoRamal()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getLocalInstalacaoModulos() != null){
            Paragraph paragraph = new Paragraph(new Text("Local de instalação dos modulos: " + visitaTecnica.getLocalInstalacaoModulos()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getMaterialEstruturaTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Material da estrutura do telhado: " + visitaTecnica.getMaterialEstruturaTelhado()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getCondicaoTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Condição do telhado: " + visitaTecnica.getCondicaoTelhado()).setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getOrientacaoTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Orientação do telhado: " + visitaTecnica.getOrientacaoTelhado()).setFontSize(20));
            document.add(paragraph);
        }

        Paragraph p_util = new Paragraph(new Text("Dimenssões úteis").setFontSize(20));
        document.add(p_util);

        if(visitaTecnica.getLarguraTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Largura do telhado: " + visitaTecnica.getLarguraTelhado() + " metros").setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getComprimentoTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Comprimento do telhado: " + visitaTecnica.getComprimentoTelhado() + " metros").setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getAlturaTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Altura do telhado: " + visitaTecnica.getAlturaTelhado() + " metros").setFontSize(20));
            document.add(paragraph);
        }

        if(visitaTecnica.getAcessoTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Acesso ao telhado: " + visitaTecnica.getAcessoTelhado() + " metros").setFontSize(20));
            document.add(paragraph);
        }

        Paragraph p_acesso = new Paragraph(new Text("Acesso ao telhado").setFontSize(20));
        document.add(p_acesso);

        if(visitaTecnica.getObsFinais() != null){
            Paragraph paragraph = new Paragraph(new Text("Observações finais: " + visitaTecnica.getObsFinais()).setFontSize(20));
            document.add(paragraph);
        }

        if(fotos.getFoto_padrao() != null){
            //Configuração de exibição da foto
            foto_padrao = fotos.getFoto_padrao();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_padrao.compress(Bitmap.CompressFormat.PNG, 30, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Foto do padrão de entrada").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotos.getFoto_acesso_telhado() != null){
            //Configuração de exibição da foto
            foto_acesso_telhado = fotos.getFoto_acesso_telhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_acesso_telhado.compress(Bitmap.CompressFormat.PNG, 30, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_acesso = new Paragraph(new Text("Foto do acesso ao telhado").setFontSize(20));
            p_foto_acesso.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_acesso);
        }

        if(fotos.getFoto_orientacao_telhado() != null){
            //Configuração de exibição da foto
            foto_orientacao_telhado = fotos.getFoto_orientacao_telhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_orientacao_telhado.compress(Bitmap.CompressFormat.PNG, 30, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_orientacao = new Paragraph(new Text("Foto da orientação do telhado").setFontSize(20));
            p_foto_orientacao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_orientacao);
        }

        if(fotos.getFotoLocalInstalacaoInversor() != null){
            //Configuração de exibição da foto
            foto_inversor = fotos.getFotoLocalInstalacaoInversor();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_inversor.compress(Bitmap.CompressFormat.PNG, 30, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_inversor = new Paragraph(new Text("Foto do local de instalação do inversor").setFontSize(20));
            p_foto_inversor.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_inversor);
        }

        document.close();
        Toast.makeText(context, "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
