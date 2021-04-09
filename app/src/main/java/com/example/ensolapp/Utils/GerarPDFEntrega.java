package com.example.ensolapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import com.example.ensolapp.Models.Entrega;
import com.example.ensolapp.Models.FotosEntrega;
import com.example.ensolapp.R;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class GerarPDFEntrega {

    public static void gerarPDF(Context context, Entrega entrega, FotosEntrega fotosEntrega) throws IOException {

        Bitmap foto_estrutura_telhado, foto_modulo_telhado, foto_string_box_aberta, foto_medida_tensao_conectado,
                foto_string_box_instalada, foto_tensao_string_box, foto_ponto_conexao, foto_final_instalação, foto_placa_seguranca;

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Entrega_" + entrega.getCliente().get("nomeCliente") + ".pdf");
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

        Text subtitle = new Text("Relatório de entrega").setFontSize(22);
        Paragraph p_sub = new Paragraph(subtitle);
        p_sub.setTextAlignment(TextAlignment.CENTER);
        document.add(p_sub);

        Text cliente_title = new Text("Informações sobre o cliente").setFontSize(20);
        Paragraph p_cliente = new Paragraph(cliente_title);
        p_cliente.setTextAlignment(TextAlignment.CENTER);
        document.add(p_cliente);

        if(entrega.getDataVisita() != null){
            int dia = entrega.getDataVisita().getDay(), mes = entrega.getDataVisita().getMonth(), ano = entrega.getDataVisita().getYear();
            Paragraph data = new Paragraph(new Text(String.format(Locale.getDefault(),"Data da visita: %02d/%02d/%04d", dia, mes+1, ano)).setFontSize(20));
            document.add(data);
        }

        if(entrega.getCliente().get("tipoCliente") != null){
            Paragraph paragraph = new Paragraph(new Text("Tipo de cliente: " + entrega.getCliente().get("tipoCliente")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("nomeCliente") != null){
            Paragraph paragraph = new Paragraph(new Text("Nome do cliente: " + entrega.getCliente().get("nomeCliente")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("razaoSocial") != null){
            Paragraph paragraph = new Paragraph(new Text("Razão social: " + entrega.getCliente().get("razaoSocial")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("cpf_cnpj") != null){
            Paragraph paragraph = new Paragraph(new Text("CPF/CNPJ: " + entrega.getCliente().get("cpf_cnpj")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("responsavel") != null){
            Paragraph paragraph = new Paragraph(new Text("Responsavel: " + entrega.getCliente().get("responsavel")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("telefone") != null){
            Paragraph paragraph = new Paragraph(new Text("Telefone: " + entrega.getCliente().get("telefone")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("email") != null){
            Paragraph paragraph = new Paragraph(new Text("E-mail: " + entrega.getCliente().get("email")).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getCliente().get("endereco") != null){
            Paragraph paragraph = new Paragraph(new Text("Endereco: " + entrega.getCliente().get("endereco")).setFontSize(20));
            document.add(paragraph);
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        if(fotosEntrega.getFotoEstruturaFixacaoTelhado() != null){
            //Configuração de exibição da foto
            foto_estrutura_telhado = fotosEntrega.getFotoEstruturaFixacaoTelhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_estrutura_telhado.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Estrutura de fixação montada no telhado").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoModulosTelhado() != null){
            //Configuração de exibição da foto
            foto_modulo_telhado = fotosEntrega.getFotoModulosTelhado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_modulo_telhado.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Módulos instalados no telhado").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoStringBoxAberta() != null){
            //Configuração de exibição da foto
            foto_string_box_aberta = fotosEntrega.getFotoStringBoxAberta();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_string_box_aberta.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("String Box instalada (aberta com ligacoes)").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoMedidaTensaoConectado() != null){
            //Configuração de exibição da foto
            foto_medida_tensao_conectado = fotosEntrega.getFotoMedidaTensaoConectado();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_medida_tensao_conectado.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Medidas de tensão da String Box e/ou entrada do inversor").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoStringBoxInstalada() != null){
            //Configuração de exibição da foto
            foto_string_box_instalada = fotosEntrega.getFotoStringBoxInstalada();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_string_box_instalada.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("String Box CA / Quadro AC instalado").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoTensaoStringBox() != null){
            //Configuração de exibição da foto
            foto_tensao_string_box = fotosEntrega.getFotoTensaoStringBox();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_tensao_string_box.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Medidas de tensão da String Box e/ou Quadro AC conectado").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoPontoConexaoCa() != null){
            //Configuração de exibição da foto
            foto_ponto_conexao = fotosEntrega.getFotoPontoConexaoCa();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_ponto_conexao.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Ponto de conexão do circuito CA").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoFinalInstalacaoKit() != null){
            //Configuração de exibição da foto
            foto_final_instalação = fotosEntrega.getFotoFinalInstalacaoKit();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_final_instalação.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Layout final de instalação do kit").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(fotosEntrega.getFotoPlacaSeguranca() != null){
            //Configuração de exibição da foto
            foto_placa_seguranca = fotosEntrega.getFotoPlacaSeguranca();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto_placa_seguranca.compress(Bitmap.CompressFormat.PNG, 40, stream);
            byte[] fotoByte = stream.toByteArray();
            ImageData fotoData = ImageDataFactory.create(fotoByte);
            Image img = new Image(fotoData);
            //Insere a imagem no PDF
            document.add(img);
            //Insere o título da imagem no PDF
            Paragraph p_foto_padrao = new Paragraph(new Text("Placas de segurança").setFontSize(20));
            p_foto_padrao.setTextAlignment(TextAlignment.CENTER);
            document.add(p_foto_padrao);
        }

        if(entrega.getObsFinais() != null){
            Paragraph paragraph = new Paragraph(new Text("Observações finais: " + entrega.getObsFinais()).setFontSize(20));
            document.add(paragraph);
        }

        if(entrega.getRessalvasTelhado() != null){
            Paragraph paragraph = new Paragraph(new Text("Ressalvas do telhado: " + entrega.getRessalvasTelhado()).setFontSize(20));
            document.add(paragraph);
        }

        document.close();
        Toast.makeText(context, "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
