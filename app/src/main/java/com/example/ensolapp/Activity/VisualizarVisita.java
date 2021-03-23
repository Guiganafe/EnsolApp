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
    private LinearLayout layout_tipo_cliente, layout_razao_social,
            layout_responsavel, layout_cpf_cnpj, layout_email,
            layout_amperagem_disjuntor, layout_condicao_padrao, layout_condicao_telhado,
            layout_largura_telhado, layout_comprimento_telhado, layout_altura_telhado;
    private TextView data_visita, tipo_cliente, nome_cliente, razao_social,
            responsavel, telefone, cpf_cnpj, email, endereco,
            padrao_entrada, amperagem_disjuntor, condicao_padrao, local_instalacao,
            material_telhado, condicao_telhado, orientacao_telhado, largura_telhado,
            largura_comprimento, altura_telhado, acesso_telhado, tipo_ramal, numero_uc,
            obs_finais_vizualizar, obs_finais_textView, foto_padrao_textView, foto_orientacao_textView, foto_acesso_textView, foto_inversor_textView;
    private ImageView foto_padrao_entrada, foto_orientacao_telhado, foto_acesso_telhado, foto_inversor;
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

        nome_cliente.setText(visitaTecnica.getCliente().get("nomeCliente").toString());
        telefone.setText(visitaTecnica.getCliente().get("telefone").toString());
        endereco.setText(visitaTecnica.getCliente().get("endereco").toString());

        if(visitaTecnica.getCliente().get("tipoCliente") != null){
            tipo_cliente.setText(visitaTecnica.getCliente().get("tipoCliente").toString());
        } else {
            layout_tipo_cliente.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("razaoSocial") != null){
            razao_social.setText(visitaTecnica.getCliente().get("razaoSocial").toString());
        } else {
            layout_razao_social.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("responsavel") != null){
            responsavel.setText(visitaTecnica.getCliente().get("responsavel").toString());
        } else {
            layout_responsavel.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("cpf_cnpj") != null){
            cpf_cnpj.setText(visitaTecnica.getCliente().get("cpf_cnpj").toString());
        } else {
            layout_cpf_cnpj.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCliente().get("email") != null){
            email.setText(visitaTecnica.getCliente().get("email").toString());
        } else {
            layout_email.setVisibility(View.GONE);
        }

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(visitaTecnica.getDataVisita());
        data_visita.setText(date);

        padrao_entrada.setText(visitaTecnica.getPadraoEntrada());

        local_instalacao.setText(visitaTecnica.getLocalInstalacaoModulos());

        material_telhado.setText(visitaTecnica.getMaterialEstruturaTelhado());

        orientacao_telhado.setText(visitaTecnica.getOrientacaoTelhado());

        numero_uc.setText(visitaTecnica.getNumeroUc());

        tipo_ramal.setText(visitaTecnica.getTipoRamal());

        acesso_telhado.setText(visitaTecnica.getAcessoTelhado());

        if(visitaTecnica.getAmperagemDisjuntosEntrada() != null){
            amperagem_disjuntor.setText(visitaTecnica.getAmperagemDisjuntosEntrada());
        } else {
            layout_amperagem_disjuntor.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCondicaoPadraoEntrada() != null){
            condicao_padrao.setText(visitaTecnica.getCondicaoPadraoEntrada());
        } else {
            layout_condicao_padrao.setVisibility(View.GONE);
        }

        if(visitaTecnica.getCondicaoTelhado() != null){
            condicao_telhado.setText(visitaTecnica.getCondicaoTelhado());
        } else {
            layout_condicao_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getLarguraTelhado() != null){
            largura_telhado.setText(visitaTecnica.getLarguraTelhado());
        } else {
            layout_largura_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getComprimentoTelhado() != null){
            largura_comprimento.setText(visitaTecnica.getComprimentoTelhado());
        } else {
            layout_comprimento_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnica.getAlturaTelhado() != null){
            altura_telhado.setText(visitaTecnica.getAlturaTelhado());
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
            foto_padrao_entrada.setBackground(null);
            foto_padrao_entrada.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoPadrao()).into(foto_padrao_entrada);
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
            foto_padrao_entrada.setVisibility(View.GONE);
            foto_padrao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnica.getFotoOrientacaoTelhado() != null){
            foto_orientacao_telhado.setBackground(null);
            foto_orientacao_telhado.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoOrientacaoTelhado()).into(foto_orientacao_telhado);
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
            foto_orientacao_telhado.setVisibility(View.GONE);
            foto_orientacao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnica.getFotoAcessoTelhado() != null){
            foto_acesso_telhado.setBackground(null);
            foto_acesso_telhado.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoAcessoTelhado()).into(foto_acesso_telhado);
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
            foto_acesso_telhado.setVisibility(View.GONE);
            foto_acesso_textView.setVisibility(View.GONE);
        }

        if(visitaTecnica.getFotoLocalInstalacaoInversor() != null){
            foto_inversor.setBackground(null);
            foto_inversor.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnica.getFotoLocalInstalacaoInversor()).into(foto_inversor);
            //Salva a imagem em Bitmap no Model de fotos
            Glide.with(this).asBitmap().load(visitaTecnica.getFotoLocalInstalacaoInversor()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    fotos.setFotoLocalInstalacaoInversor(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        } else {
            foto_inversor.setVisibility(View.GONE);
            foto_inversor_textView.setVisibility(View.GONE);
        }
    }

    private void inicializarComponentes() {
        layout_tipo_cliente =  findViewById(R.id.layout_tipo_cliente);
        layout_razao_social =  findViewById(R.id.layout_razao_social);
        layout_responsavel =  findViewById(R.id.layout_responsavel);
        layout_cpf_cnpj =  findViewById(R.id.layout_cpf_cnpj);
        layout_email =  findViewById(R.id.layout_email);
        layout_amperagem_disjuntor =  findViewById(R.id.layout_amperagem_disjuntor);
        layout_condicao_padrao =  findViewById(R.id.layout_condicao_padrao);
        layout_condicao_telhado =  findViewById(R.id.layout_condicao_telhado);
        layout_largura_telhado =  findViewById(R.id.layout_largura_telhado);
        layout_comprimento_telhado =  findViewById(R.id.layout_comprimento_telhado);
        layout_altura_telhado =  findViewById(R.id.layout_altura_telhado);

        data_visita = findViewById(R.id.data_visita_visualizar);
        tipo_cliente = findViewById(R.id.tipo_cliente_visualizar);
        nome_cliente = findViewById(R.id.nome_cliente_visualizar);
        razao_social = findViewById(R.id.razao_social_visualizar);
        responsavel = findViewById(R.id.responsavel_visualizar);
        telefone = findViewById(R.id.telefone_visualizar);
        cpf_cnpj = findViewById(R.id.cpf_cnpj_visualizar);
        email = findViewById(R.id.email_visualizar);
        endereco = findViewById(R.id.endereco_visualizar);
        padrao_entrada = findViewById(R.id.padrao_entrada_visualizar);
        amperagem_disjuntor = findViewById(R.id.amperagem_disjuntor_visualizar);
        condicao_padrao = findViewById(R.id.condicao_padrao_visualizar);
        local_instalacao = findViewById(R.id.local_instalacao_visualizar);
        material_telhado = findViewById(R.id.material_telhado_visualizar);
        condicao_telhado = findViewById(R.id.condicao_telhado_visualizar);
        orientacao_telhado = findViewById(R.id.orientacao_telhado_visualizar);
        largura_telhado = findViewById(R.id.largura_telhado_visualizar);
        largura_comprimento = findViewById(R.id.largura_comprimento_visualizar);
        altura_telhado = findViewById(R.id.altura_telhado_visualizar);
        obs_finais_vizualizar = findViewById(R.id.obs_finais_vizualizar);
        obs_finais_textView = findViewById(R.id.obs_finais_textView);
        foto_padrao_textView = findViewById(R.id.foto_padrao_textView);
        foto_orientacao_textView = findViewById(R.id.foto_orientacao_textView);
        foto_acesso_textView = findViewById(R.id.foto_acesso_textView);
        acesso_telhado = findViewById(R.id.acesso_telhado_visualizar);
        tipo_ramal = findViewById(R.id.tipo_ramal_visualizar);
        numero_uc = findViewById(R.id.numero_uc_visualizar);
        foto_inversor_textView = findViewById(R.id.foto_inversor_textView_visualizar);

        foto_padrao_entrada = findViewById(R.id.foto_padrao_entrada_visualizar);
        foto_orientacao_telhado = findViewById(R.id.foto_orientacao_telhado_visualizar);
        foto_acesso_telhado = findViewById(R.id.foto_acesso_telhado_visualizar);
        foto_inversor = findViewById(R.id.foto_inversor_visualizar);

        fab_download_form = findViewById(R.id.fab_download_form);
    }
}