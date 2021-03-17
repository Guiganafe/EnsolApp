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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ensolapp.Base.VisitaTecnicaBase;
import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDF;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class VisualizarVisita extends AppCompatActivity {

    private VisitaTecnica visitaTecnica;
    private Fotos fotos;
    private int position;
    private LinearLayout layout_data_visita, layout_tipo_cliente, layout_nome_cliente, layout_razao_social,
            layout_responsavel, layout_telefone, layout_cpf_cnpj, layout_email, layout_endereco, layout_padrao_entrada,
            layout_amperagem_disjuntor, layout_condicao_padrao, layout_local_instalacao, layout_material_telhado,
            layout_condicao_telhado, layout_orientacao_telhado, layout_largura_telhado, layout_comprimento_telhado,
            layout_altura_telhado, layout_escada_acesso, layout_andaime_acesso;
    private TextView data_visita_visualizar, tipo_cliente_visualizar, nome_cliente_visualizar, razao_social_visualizar,
            responsavel_visualizar, telefone_visualizar, cpf_cnpj_visualizar, email_visualizar, endereco_visualizar,
            padrao_entrada_visualizar, amperagem_disjuntor_visualizar, condicao_padrao_visualizar, local_instalacao_visualizar,
            material_telhado_visualizar, condicao_telhado_visualizar, orientacao_telhado_visualizar, largura_telhado_visualizar,
            largura_comprimento_visualizar, altura_telhado_visualizar, escada_acesso_visualizar, andaime_acesso_visualizar,
            obs_finais_vizualizar, obs_finais_textView, foto_padrao_textView, foto_orientacao_textView, foto_acesso_textView;
    private ImageView foto_padrao_entrada_visualizar, foto_orientacao_telhado_visualizar, foto_acesso_telhado_visualizar;
    private FloatingActionButton fab_download_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_visita);
        inicializarComponentes();
        carregadDadosController();
        onClickController();
    }

    private void onClickController() {
        fab_download_form.setOnClickListener(view -> {
            try {
                GerarPDF.gerarPDF(this, visitaTecnica, fotos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void carregadDadosController() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        visitaTecnica = VisitaTecnicaBase.getInstance().getVisitaTecnicaPorDia().get(position);

        nome_cliente_visualizar.setText(visitaTecnica.getCliente().get("nomeCliente").toString());
        telefone_visualizar.setText(visitaTecnica.getCliente().get("telefone").toString());
        endereco_visualizar.setText(visitaTecnica.getCliente().get("endereco").toString());

        if(visitaTecnica.getCliente().get("tipoCliente") != null){
            tipo_cliente_visualizar.setText(visitaTecnica.getCliente().get("tipoCliente").toString());
        } else {
            layout_tipo_cliente.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("razaoSocial") != null){
            razao_social_visualizar.setText(visitaTecnica.getCliente().get("razaoSocial").toString());
        } else {
            layout_razao_social.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("responsavel") != null){
            responsavel_visualizar.setText(visitaTecnica.getCliente().get("responsavel").toString());
        } else {
            layout_responsavel.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("cpf_cnpj") != null){
            cpf_cnpj_visualizar.setText(visitaTecnica.getCliente().get("cpf_cnpj").toString());
        } else {
            layout_cpf_cnpj.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("email") != null){
            email_visualizar.setText(visitaTecnica.getCliente().get("email").toString());
        } else {
            layout_email.setVisibility(View.GONE);
        }

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(visitaTecnica.getDataVisita());
        data_visita_visualizar.setText(date);

        padrao_entrada_visualizar.setText(visitaTecnica.getPadraoEntrada());

        local_instalacao_visualizar.setText(visitaTecnica.getLocalInstalacaoModulos());

        material_telhado_visualizar.setText(visitaTecnica.getMaterialEstruturaTelhado());

        orientacao_telhado_visualizar.setText(visitaTecnica.getOrientacaoTelhado());

        escada_acesso_visualizar.setText(visitaTecnica.getAcessoEscada());

        andaime_acesso_visualizar.setText(visitaTecnica.getAcessoAndaime());

        if(visitaTecnica.getAmperagemDisjuntosEntrada() != null){
            amperagem_disjuntor_visualizar.setText(visitaTecnica.getAmperagemDisjuntosEntrada());
        } else {
            layout_amperagem_disjuntor.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCondicaoPadraoEntrada() != null){
            condicao_padrao_visualizar.setText(visitaTecnica.getCondicaoPadraoEntrada());
        } else {
            layout_condicao_padrao.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCondicaoTelhado() != null){
            condicao_telhado_visualizar.setText(visitaTecnica.getCondicaoTelhado());
        } else {
            layout_condicao_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getLarguraTelhado() != null){
            largura_telhado_visualizar.setText(visitaTecnica.getLarguraTelhado());
        } else {
            layout_largura_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getComprimentoTelhado() != null){
            largura_comprimento_visualizar.setText(visitaTecnica.getComprimentoTelhado());
        } else {
            layout_comprimento_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getAlturaTelhado() != null){
            altura_telhado_visualizar.setText(visitaTecnica.getAlturaTelhado());
        } else {
            layout_altura_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getObsFinais() != null){
            obs_finais_vizualizar.setText(visitaTecnica.getObsFinais());
        } else {
            obs_finais_vizualizar.setVisibility(View.GONE);
            obs_finais_textView.setVisibility(View.GONE);
        }

        fotos = new Fotos();

        if(visitaTecnica.getFotoPadrao() != null){
            foto_padrao_entrada_visualizar.setBackground(null);
            foto_padrao_entrada_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoPadrao()).into(foto_padrao_entrada_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(visitaTecnica.getFotoPadrao()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotos.setFoto_padrao(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            foto_padrao_entrada_visualizar.setVisibility(View.GONE);
            foto_padrao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnica.getFotoOrientacaoTelhado() != null){
            foto_orientacao_telhado_visualizar.setBackground(null);
            foto_orientacao_telhado_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoOrientacaoTelhado()).into(foto_orientacao_telhado_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(visitaTecnica.getFotoOrientacaoTelhado()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotos.setFoto_orientacao_telhado(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            foto_orientacao_telhado_visualizar.setVisibility(View.GONE);
            foto_orientacao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnica.getFotoAcessoTelhado() != null){
            foto_acesso_telhado_visualizar.setBackground(null);
            foto_acesso_telhado_visualizar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoAcessoTelhado()).into(foto_acesso_telhado_visualizar);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(visitaTecnica.getFotoAcessoTelhado()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotos.setFoto_acesso_telhado(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            foto_acesso_telhado_visualizar.setVisibility(View.GONE);
            foto_acesso_textView.setVisibility(View.GONE);
        }
    }

    private void inicializarComponentes() {
        layout_data_visita =  findViewById(R.id.layout_data_visita);
        layout_tipo_cliente =  findViewById(R.id.layout_tipo_cliente);
        layout_nome_cliente =  findViewById(R.id.layout_nome_cliente);
        layout_razao_social =  findViewById(R.id.layout_razao_social);
        layout_responsavel =  findViewById(R.id.layout_responsavel);
        layout_telefone =  findViewById(R.id.layout_telefone);
        layout_cpf_cnpj =  findViewById(R.id.layout_cpf_cnpj);
        layout_email =  findViewById(R.id.layout_email);
        layout_endereco =  findViewById(R.id.layout_endereco);
        layout_padrao_entrada =  findViewById(R.id.layout_padrao_entrada);
        layout_amperagem_disjuntor =  findViewById(R.id.layout_amperagem_disjuntor);
        layout_condicao_padrao =  findViewById(R.id.layout_condicao_padrao);
        layout_local_instalacao =  findViewById(R.id.layout_local_instalacao);
        layout_material_telhado =  findViewById(R.id.layout_material_telhado);
        layout_condicao_telhado =  findViewById(R.id.layout_condicao_telhado);
        layout_orientacao_telhado =  findViewById(R.id.layout_orientacao_telhado);
        layout_largura_telhado =  findViewById(R.id.layout_largura_telhado);
        layout_comprimento_telhado =  findViewById(R.id.layout_comprimento_telhado);
        layout_altura_telhado =  findViewById(R.id.layout_altura_telhado);
        layout_escada_acesso =  findViewById(R.id.layout_escada_acesso);
        layout_andaime_acesso =  findViewById(R.id.layout_andaime_acesso);

        data_visita_visualizar = findViewById(R.id.data_visita_visualizar);
        tipo_cliente_visualizar = findViewById(R.id.tipo_cliente_visualizar);
        nome_cliente_visualizar = findViewById(R.id.nome_cliente_visualizar);
        razao_social_visualizar = findViewById(R.id.razao_social_visualizar);
        responsavel_visualizar = findViewById(R.id.responsavel_visualizar);
        telefone_visualizar = findViewById(R.id.telefone_visualizar);
        cpf_cnpj_visualizar = findViewById(R.id.cpf_cnpj_visualizar);
        email_visualizar = findViewById(R.id.email_visualizar);
        endereco_visualizar = findViewById(R.id.endereco_visualizar);
        padrao_entrada_visualizar = findViewById(R.id.padrao_entrada_visualizar);
        amperagem_disjuntor_visualizar = findViewById(R.id.amperagem_disjuntor_visualizar);
        condicao_padrao_visualizar = findViewById(R.id.condicao_padrao_visualizar);
        local_instalacao_visualizar = findViewById(R.id.local_instalacao_visualizar);
        material_telhado_visualizar = findViewById(R.id.material_telhado_visualizar);
        condicao_telhado_visualizar = findViewById(R.id.condicao_telhado_visualizar);
        orientacao_telhado_visualizar = findViewById(R.id.orientacao_telhado_visualizar);
        largura_telhado_visualizar = findViewById(R.id.largura_telhado_visualizar);
        largura_comprimento_visualizar = findViewById(R.id.largura_comprimento_visualizar);
        altura_telhado_visualizar = findViewById(R.id.altura_telhado_visualizar);
        escada_acesso_visualizar = findViewById(R.id.escada_acesso_visualizar);
        andaime_acesso_visualizar = findViewById(R.id.andaime_acesso_visualizar);
        obs_finais_vizualizar = findViewById(R.id.obs_finais_vizualizar);
        obs_finais_textView = findViewById(R.id.obs_finais_textView);
        foto_padrao_textView = findViewById(R.id.foto_padrao_textView);
        foto_orientacao_textView = findViewById(R.id.foto_orientacao_textView);
        foto_acesso_textView = findViewById(R.id.foto_acesso_textView);

        foto_padrao_entrada_visualizar = findViewById(R.id.foto_padrao_entrada_visualizar);
        foto_orientacao_telhado_visualizar = findViewById(R.id.foto_orientacao_telhado_visualizar);
        foto_acesso_telhado_visualizar = findViewById(R.id.foto_acesso_telhado_visualizar);

        fab_download_form = findViewById(R.id.fab_download_form);
    }
}