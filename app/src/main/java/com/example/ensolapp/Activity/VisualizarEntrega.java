package com.example.ensolapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ensolapp.Base.EntregaBase;
import com.example.ensolapp.Base.VisitaTecnicaBase;
import com.example.ensolapp.Models.Entrega;
import com.example.ensolapp.Models.FotosEntrega;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDF;
import com.example.ensolapp.Utils.GerarPDFEntrega;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class VisualizarEntrega extends AppCompatActivity {

    private Entrega entrega;
    private FotosEntrega fotosEntrega;
    private int position;
    private TextView tipo_cliente_visualizar_entrega, razao_social_visualizar, responsavel_visualizar, cpf_cnpj_visualizar,
            email_visualizar, estrutura_fixacao_textView_visualizar, modulos_instalados_textView_visualizar,
            string_box_instalada_aberta_textView_visualizar, string_quadro_instalado_textView_visualizar,
            ponto_conexao_textView_visualizar, layout_final_textView_visualizar, placas_Seguranca_textView_visualizar,
            obs_finais_textView_visualizar, obs_finais_visualizar, ressalvas_textView_visualizar, ressalvas_visualizar,
            tensao_string_quadro_textView_visualizar, data_visita_visualizar, nome_cliente_visualizar, telefone_visualizar,
            medidas_tensao_textView_visualizar,
            endereco_visualizar;
    private LinearLayout layout_tipo_cliente_visualizar_entrega, layout_razao_social_visualizar, layout_responsavel_visualizar,
            layout_cpf_cnpj_visualizar, layout_email_visualizar;
    private ImageView estrutura_fixacao_visualizar, modulos_instalados_visualizar, string_box_instalada_aberta_visualizar,
            medidas_tensao_visualizar, string_quadro_instalado_visualizar, tensao_string_quadro_visualizar,
            ponto_conexao_visualizar, layout_final_visualizar, placas_Seguranca_visualizar;
    private FloatingActionButton fab_download_form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_entrega);
        inicializarComponentes();
        carregadDadosController();
        onClickController();
    }

    private void carregadDadosController() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        entrega = EntregaBase.getInstance().getEntregasPorDia().get(position);
        fotosEntrega = new FotosEntrega();

        nome_cliente_visualizar.setText(entrega.getCliente().get("nomeCliente").toString());
        telefone_visualizar.setText(entrega.getCliente().get("telefone").toString());
        endereco_visualizar.setText(entrega.getCliente().get("endereco").toString());

        if(entrega.getCliente().get("tipoCliente") != null){
            tipo_cliente_visualizar_entrega.setText(entrega.getCliente().get("tipoCliente").toString());
        } else {
            layout_tipo_cliente_visualizar_entrega.setVisibility(View.GONE);
        }

        if(entrega.getCliente().get("razaoSocial") != null){
            razao_social_visualizar.setText(entrega.getCliente().get("razaoSocial").toString());
        } else {
            layout_razao_social_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getCliente().get("responsavel") != null){
            responsavel_visualizar.setText(entrega.getCliente().get("responsavel").toString());
        } else {
            layout_responsavel_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getCliente().get("cpf_cnpj") != null){
            cpf_cnpj_visualizar.setText(entrega.getCliente().get("cpf_cnpj").toString());
        } else {
            layout_cpf_cnpj_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getCliente().get("email") != null){
            email_visualizar.setText(entrega.getCliente().get("email").toString());
        } else {
            layout_email_visualizar.setVisibility(View.GONE);
        }

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(entrega.getDataVisita());
        data_visita_visualizar.setText(date);

        if(entrega.getFotoEstruturaFixacaoTelhadoUrl() != null){
            estrutura_fixacao_visualizar.setBackground(null);
            estrutura_fixacao_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoEstruturaFixacaoTelhadoUrl()).into(estrutura_fixacao_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoEstruturaFixacaoTelhadoUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoEstruturaFixacaoTelhado(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            estrutura_fixacao_visualizar.setVisibility(View.GONE);
            estrutura_fixacao_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoModulosTelhadoUrl() != null){
            modulos_instalados_visualizar.setBackground(null);
            modulos_instalados_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoModulosTelhadoUrl()).into(modulos_instalados_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoModulosTelhadoUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoModulosTelhado(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            modulos_instalados_visualizar.setVisibility(View.GONE);
            modulos_instalados_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoStringBoxAbertaUrl() != null){
            string_box_instalada_aberta_visualizar.setBackground(null);
            string_box_instalada_aberta_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoStringBoxAbertaUrl()).into(string_box_instalada_aberta_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoStringBoxAbertaUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoStringBoxAberta(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            string_box_instalada_aberta_visualizar.setVisibility(View.GONE);
            string_box_instalada_aberta_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoMedidaTensaoConectadoUrl() != null){
            medidas_tensao_visualizar.setBackground(null);
            medidas_tensao_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoMedidaTensaoConectadoUrl()).into(medidas_tensao_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoMedidaTensaoConectadoUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoMedidaTensaoConectado(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            medidas_tensao_visualizar.setVisibility(View.GONE);
            medidas_tensao_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoStringBoxInstaladaUrl() != null){
            string_quadro_instalado_visualizar.setBackground(null);
            string_quadro_instalado_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoStringBoxInstaladaUrl()).into(string_quadro_instalado_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoStringBoxInstaladaUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoStringBoxInstalada(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            string_quadro_instalado_visualizar.setVisibility(View.GONE);
            string_quadro_instalado_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoTensaoStringBoxUrl() != null){
            tensao_string_quadro_visualizar.setBackground(null);
            tensao_string_quadro_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoTensaoStringBoxUrl()).into(tensao_string_quadro_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoTensaoStringBoxUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoTensaoStringBox(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            tensao_string_quadro_visualizar.setVisibility(View.GONE);
            tensao_string_quadro_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoPontoConexaoCaUrl() != null){
            ponto_conexao_visualizar.setBackground(null);
            ponto_conexao_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoPontoConexaoCaUrl()).into(ponto_conexao_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoPontoConexaoCaUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoPontoConexaoCa(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            ponto_conexao_visualizar.setVisibility(View.GONE);
            ponto_conexao_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoFinalInstalacaoKitUrl() != null){
            layout_final_visualizar.setBackground(null);
            layout_final_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoFinalInstalacaoKitUrl()).into(layout_final_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoFinalInstalacaoKitUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoFinalInstalacaoKit(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            layout_final_visualizar.setVisibility(View.GONE);
            layout_final_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getFotoPlacaSegurancaUrl() != null){
            placas_Seguranca_visualizar.setBackground(null);
            placas_Seguranca_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entrega.getFotoPlacaSegurancaUrl()).into(placas_Seguranca_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(entrega.getFotoPlacaSegurancaUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotosEntrega.setFotoPlacaSeguranca(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            placas_Seguranca_visualizar.setVisibility(View.GONE);
            placas_Seguranca_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getObsFinais() != null){
            obs_finais_visualizar.setText(entrega.getObsFinais());
        } else {
            obs_finais_visualizar.setVisibility(View.GONE);
            obs_finais_textView_visualizar.setVisibility(View.GONE);
        }

        if(entrega.getRessalvasTelhado() != null){
            ressalvas_visualizar.setText(entrega.getRessalvasTelhado());
        } else {
            ressalvas_visualizar.setVisibility(View.GONE);
            ressalvas_textView_visualizar.setVisibility(View.GONE);
        }
    }

    private void onClickController() {
        fab_download_form.setOnClickListener( view -> {
            try {
                GerarPDFEntrega.gerarPDF(this, entrega, fotosEntrega);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void inicializarComponentes() {
        //TextViews
        tipo_cliente_visualizar_entrega = findViewById(R.id.tipo_cliente_visualizar_entrega);
        razao_social_visualizar = findViewById(R.id.razao_social_visualizar_entrega);
        responsavel_visualizar = findViewById(R.id.responsavel_visualizar_entrega);
        cpf_cnpj_visualizar = findViewById(R.id.cpf_cnpj_visualizar_entrega);
        email_visualizar = findViewById(R.id.email_visualizar_entrega);
        estrutura_fixacao_textView_visualizar = findViewById(R.id.estrutura_fixacao_textView_visualizar);
        modulos_instalados_textView_visualizar = findViewById(R.id.modulos_instalados_textView_visualizar);
        string_box_instalada_aberta_textView_visualizar = findViewById(R.id.string_box_instalada_aberta_textView_visualizar);
        string_quadro_instalado_textView_visualizar = findViewById(R.id.string_quadro_instalado_textView_visualizar);
        ponto_conexao_textView_visualizar = findViewById(R.id.ponto_conexao_textView_visualizar);
        layout_final_textView_visualizar = findViewById(R.id.layout_final_textView_visualizar);
        placas_Seguranca_textView_visualizar = findViewById(R.id.placas_Seguranca_textView_visualizar);
        obs_finais_textView_visualizar = findViewById(R.id.obs_finais_textView_visualizar);
        obs_finais_visualizar = findViewById(R.id.obs_finais_visualizar);
        ressalvas_textView_visualizar = findViewById(R.id.ressalvas_textView_visualizar);
        ressalvas_visualizar = findViewById(R.id.ressalvas_visualizar);
        tensao_string_quadro_textView_visualizar = findViewById(R.id.tensao_string_quadro_textView_visualizar);
        nome_cliente_visualizar = findViewById(R.id.nome_cliente_visualizar_entrega);
        data_visita_visualizar = findViewById(R.id.data_visita_visualizar_entrega);
        telefone_visualizar = findViewById(R.id.telefone_visualizar_entrega);
        endereco_visualizar = findViewById(R.id.endereco_visualizar_entrega);
        medidas_tensao_textView_visualizar = findViewById(R.id.medidas_tensao_textView_visualizar);

        //Layouts
        layout_tipo_cliente_visualizar_entrega = findViewById(R.id.layout_tipo_cliente_visualizar_entrega);
        layout_razao_social_visualizar = findViewById(R.id.layout_razao_social_visualizar_entrega);
        layout_responsavel_visualizar = findViewById(R.id.layout_responsavel_visualizar_entrega);
        layout_cpf_cnpj_visualizar = findViewById(R.id.layout_cpf_cnpj_visualizar_entrega);
        layout_email_visualizar = findViewById(R.id.layout_email_visualizar_entrega);

        //ImageViews
        estrutura_fixacao_visualizar = findViewById(R.id.estrutura_fixacao_visualizar);
        modulos_instalados_visualizar = findViewById(R.id.modulos_instalados_visualizar);
        string_box_instalada_aberta_visualizar = findViewById(R.id.string_box_instalada_aberta_visualizar);
        medidas_tensao_visualizar = findViewById(R.id.medidas_tensao_visualizar);
        string_quadro_instalado_visualizar = findViewById(R.id.string_quadro_instalado_visualizar);
        tensao_string_quadro_visualizar = findViewById(R.id.tensao_string_quadro_visualizar);
        ponto_conexao_visualizar = findViewById(R.id.ponto_conexao_visualizar);
        layout_final_visualizar = findViewById(R.id.layout_final_visualizar);
        placas_Seguranca_visualizar = findViewById(R.id.placas_Seguranca_visualizar);

        //Botões
        fab_download_form = findViewById(R.id.fab_download_form_entrega);
    }
}