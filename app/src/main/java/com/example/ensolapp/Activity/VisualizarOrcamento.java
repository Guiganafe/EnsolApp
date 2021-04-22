package com.example.ensolapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ensolapp.Base.EntregaBase;
import com.example.ensolapp.Base.OrcamentoBase;
import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDFEntrega;
import com.example.ensolapp.Utils.GerarPDFOrcamento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class VisualizarOrcamento extends AppCompatActivity {

    private Orcamento orcamento;
    private TextView data, nome, telefone, potencia, localizacao;
    private ImageView foto_conta;
    private FloatingActionButton baixar_pdf;
    private int position;
    private Bitmap foto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_orcamento);
        inicializarComponentes();
        carregarDadosController();
        onClickController();
    }

    private void onClickController() {
        baixar_pdf.setOnClickListener(view -> {
            try {
                GerarPDFOrcamento.gerarPDF(this, orcamento, foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void carregarDadosController() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        orcamento = OrcamentoBase.getInstance().getOrcamentosPorDia().get(position);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(orcamento.getDataSolicitacao());
        data.setText(date);

        nome.setText(orcamento.getNomeCliente());
        telefone.setText(orcamento.getContato());
        potencia.setText(orcamento.getPotenciaDesejada());
        localizacao.setText(orcamento.getLocalizacao());

        foto_conta.setBackground(null);
        foto_conta.setPadding(0, 0, 0, 0);
        Glide.with(this).load(orcamento.getFotoContaUrl()).into(foto_conta);
        //Salva a imagem em Bitmap no Model de fotos
        Glide.with(this).asBitmap().load(orcamento.getFotoContaUrl()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                foto = resource;
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    private void inicializarComponentes() {
        data = findViewById(R.id.data_solicitacao_visualizar_orcamento);
        nome = findViewById(R.id.nome_cliente_visualizar_orcamento);
        telefone = findViewById(R.id.telefone_visualizar_orcamento);
        potencia = findViewById(R.id.potencia_desejada_visualizar_orcamento);
        localizacao = findViewById(R.id.localizacao_visualizar_orcamento);
        foto_conta = findViewById(R.id.foto_conta_visualizar_orcamento);
        baixar_pdf = findViewById(R.id.fab_download_form_orcamento);
    }
}