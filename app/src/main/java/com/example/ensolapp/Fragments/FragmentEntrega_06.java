package com.example.ensolapp.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Cliente;
import com.example.ensolapp.Models.Entrega;
import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.FotosEntrega;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDF;
import com.example.ensolapp.Utils.GerarPDFEntrega;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.EntregaViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentEntrega_06 extends Fragment {

    private ClienteViewModel clienteViewModel;
    private EntregaViewModel entregaViewModel;
    private FotosEntrega fotosEntrega;
    private Button voltar, finalizar;
    private TextView tipo_cliente_confirmar_entrega, razao_social_confirmar, responsavel_confirmar, cpf_cnpj_confirmar,
            email_confirmar, estrutura_fixacao_textView_confirmar, modulos_instalados_textView_confirmar,
            string_box_instalada_aberta_textView_confirmar, string_quadro_instalado_textView_confirmar,
            ponto_conexao_textView_confirmar, layout_final_textView_confirmar, placas_Seguranca_textView_confirmar,
            obs_finais_textView_confirmar, obs_finais_confirmar, ressalvas_textView_confirmar, ressalvas_confirmar,
            tensao_string_quadro_textView_confirmar, data_visita_confirmar, nome_cliente_confirmar, telefone_confirmar,
            medidas_tensao_textView_confirmar,
            endereco_confirmar;
    private LinearLayout layout_tipo_cliente_confirmar_entrega, layout_razao_social_confirmar, layout_responsavel_confirmar,
            layout_cpf_cnpj_confirmar, layout_email_confirmar;
    private ImageView estrutura_fixacao_confirmar, modulos_instalados_confirmar, string_box_instalada_aberta_confirmar,
            medidas_tensao_confirmar, string_quadro_instalado_confirmar, tensao_string_quadro_confirmar,
            ponto_conexao_confirmar, layout_final_confirmar, placas_Seguranca_confirmar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_06, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entregaViewModel = new ViewModelProvider(requireActivity()).get(EntregaViewModel.class);
        clienteViewModel = new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        carregadDadosController();
        onCLickController();
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }
    }

    private void onCLickController() {
        voltar.setOnClickListener(view -> getActivity().onBackPressed());
        finalizar.setOnClickListener(view -> {
            try {
                salvar();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void salvar() throws ParseException {
        Entrega entrega = new Entrega();
        Cliente cliente = new Cliente();
        fotosEntrega = new FotosEntrega();

        //Dados obrigatórios
        cliente.setNomeCliente(clienteViewModel.getNomeCliente().getValue());
        cliente.setTelefone(clienteViewModel.getTelefone().getValue());
        cliente.setEndereco(clienteViewModel.getEndereco().getValue());

        //Dados não obrigatórios
        if(clienteViewModel.getTipoCliente().getValue() != null){
            cliente.setTipoCliente(clienteViewModel.getTipoCliente().getValue());
        }

        if(clienteViewModel.getRazaoSocial().getValue() != null){
            cliente.setRazaoSocial(clienteViewModel.getRazaoSocial().getValue());
        }

        if(clienteViewModel.getResponsavel().getValue() != null){
            cliente.setResponsavel(clienteViewModel.getResponsavel().getValue());
        }

        if(clienteViewModel.getCpf_cnpj().getValue() != null){
            cliente.setCpf_cnpj(clienteViewModel.getCpf_cnpj().getValue());
        }

        if(clienteViewModel.getEmail().getValue() != null){
            cliente.setEmail(clienteViewModel.getEmail().getValue());
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(entregaViewModel.getDataVisita().getValue());
        Calendar dataDoDia = Calendar.getInstance(Locale.getDefault());
        data.setHours(dataDoDia.getTime().getHours());
        data.setMinutes(dataDoDia.getTime().getMinutes());

        //Dados obrigatórios
        entrega.setDataVisita(data);
        entrega.setCliente(cliente.toMap());
        entrega.setTecnicoId(FirebaseService.getFirebaseUser().getUid());

        if(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue() != null){
            fotosEntrega.setFotoEstruturaFixacaoTelhado(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue());
            entrega.setFotoEstruturaFixacaoTelhadoUrl(entregaViewModel.getFotoEstruturaFixacaoTelhadoUrl().getValue());
        }

        if(entregaViewModel.getFotoModulosTelhado().getValue() != null){
            fotosEntrega.setFotoModulosTelhado(entregaViewModel.getFotoModulosTelhado().getValue());
            entrega.setFotoModulosTelhadoUrl(entregaViewModel.getFotoModulosTelhadoUrl().getValue());
        }

        if(entregaViewModel.getFotoStringBoxAberta().getValue() != null){
            fotosEntrega.setFotoStringBoxAberta(entregaViewModel.getFotoStringBoxAberta().getValue());
            entrega.setFotoStringBoxAbertaUrl(entregaViewModel.getFotoStringBoxAbertaUrl().getValue());
        }

        if(entregaViewModel.getFotoMedidaTensaoConectado().getValue() != null){
            fotosEntrega.setFotoMedidaTensaoConectado(entregaViewModel.getFotoMedidaTensaoConectado().getValue());
            entrega.setFotoMedidaTensaoConectadoUrl(entregaViewModel.getFotoMedidaTensaoConectadoUrl().getValue());
        }

        if(entregaViewModel.getFotoStringBoxInstalada().getValue() != null){
            fotosEntrega.setFotoStringBoxInstalada(entregaViewModel.getFotoStringBoxInstalada().getValue());
            entrega.setFotoStringBoxInstaladaUrl(entregaViewModel.getFotoStringBoxInstaladaUrl().getValue());
        }

        if(entregaViewModel.getFotoTensaoStringBox().getValue() != null){
            fotosEntrega.setFotoTensaoStringBox(entregaViewModel.getFotoTensaoStringBox().getValue());
            entrega.setFotoTensaoStringBoxUrl(entregaViewModel.getFotoTensaoStringBoxUrl().getValue());
        }

        if(entregaViewModel.getFotoPontoConexaoCa().getValue() != null){
            fotosEntrega.setFotoPontoConexaoCa(entregaViewModel.getFotoPontoConexaoCa().getValue());
            entrega.setFotoPontoConexaoCaUrl(entregaViewModel.getFotoPontoConexaoCaUrl().getValue());
        }

        if(entregaViewModel.getFotoFinalInstalacaoKit().getValue() != null){
            fotosEntrega.setFotoFinalInstalacaoKit(entregaViewModel.getFotoFinalInstalacaoKit().getValue());
            entrega.setFotoFinalInstalacaoKitUrl(entregaViewModel.getFotoFinalInstalacaoKitUrl().getValue());
        }

        if(entregaViewModel.getFotoPlacaSeguranca().getValue() != null){
            fotosEntrega.setFotoPlacaSeguranca(entregaViewModel.getFotoPlacaSeguranca().getValue());
            entrega.setFotoPlacaSegurancaUrl(entregaViewModel.getFotoPlacaSegurancaUrl().getValue());
        }

        if(entregaViewModel.getObsFinais().getValue() != null){
            entrega.setObsFinais(entregaViewModel.getObsFinais().getValue());
        }

        if(entregaViewModel.getRessalvasTelhado().getValue() != null){
            entrega.setRessalvasTelhado(entregaViewModel.getRessalvasTelhado().getValue());
        }

        baixarPermissao(entrega, fotosEntrega);

        db.collection("entregas")
                .add(entrega.toMap())
                .addOnSuccessListener(documentReference -> {

                })
                .addOnFailureListener(e -> {

                });

        requireActivity().finish();
    }

    private void baixarPermissao(Entrega entrega, FotosEntrega fotosEntrega) {
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        } else {
            try{
                GerarPDFEntrega.gerarPDF(requireActivity(), entrega, fotosEntrega);
            }catch (FileNotFoundException fe){
                fe.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void carregadDadosController() {
        nome_cliente_confirmar.setText(clienteViewModel.getNomeCliente().getValue());
        telefone_confirmar.setText(clienteViewModel.getTelefone().getValue());
        endereco_confirmar.setText(clienteViewModel.getEndereco().getValue());

        if(clienteViewModel.getTipoCliente().getValue() != null){
            tipo_cliente_confirmar_entrega.setText(clienteViewModel.getTipoCliente().getValue());
        } else {
            layout_tipo_cliente_confirmar_entrega.setVisibility(View.GONE);
        }

        if(clienteViewModel.getRazaoSocial().getValue() != null){
            razao_social_confirmar.setText(clienteViewModel.getRazaoSocial().getValue());
        } else {
            layout_razao_social_confirmar.setVisibility(View.GONE);
        }

        if(clienteViewModel.getResponsavel().getValue() != null){
            responsavel_confirmar.setText(clienteViewModel.getResponsavel().getValue());
        } else {
            layout_responsavel_confirmar.setVisibility(View.GONE);
        }

        if(clienteViewModel.getCpf_cnpj().getValue() != null){
            cpf_cnpj_confirmar.setText(clienteViewModel.getCpf_cnpj().getValue());
        } else {
            layout_cpf_cnpj_confirmar.setVisibility(View.GONE);
        }

        if(clienteViewModel.getEmail().getValue() != null){
            email_confirmar.setText(clienteViewModel.getEmail().getValue());
        } else {
            layout_email_confirmar.setVisibility(View.GONE);
        }

        data_visita_confirmar.setText(entregaViewModel.getDataVisita().getValue());

        if(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue() != null){
            estrutura_fixacao_confirmar.setBackground(null);
            estrutura_fixacao_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue()).into(estrutura_fixacao_confirmar);
        } else {
            estrutura_fixacao_confirmar.setVisibility(View.GONE);
            estrutura_fixacao_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoModulosTelhado().getValue() != null){
            modulos_instalados_confirmar.setBackground(null);
            modulos_instalados_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoModulosTelhado().getValue()).into(modulos_instalados_confirmar);
        } else {
            modulos_instalados_confirmar.setVisibility(View.GONE);
            modulos_instalados_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoStringBoxAberta().getValue() != null){
            string_box_instalada_aberta_confirmar.setBackground(null);
            string_box_instalada_aberta_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoStringBoxAberta().getValue()).into(string_box_instalada_aberta_confirmar);
        } else {
            string_box_instalada_aberta_confirmar.setVisibility(View.GONE);
            string_box_instalada_aberta_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoMedidaTensaoConectado().getValue() != null){
            medidas_tensao_confirmar.setBackground(null);
            medidas_tensao_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoMedidaTensaoConectado().getValue()).into(medidas_tensao_confirmar);
        } else {
            medidas_tensao_confirmar.setVisibility(View.GONE);
            medidas_tensao_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoStringBoxInstalada().getValue() != null){
            string_quadro_instalado_confirmar.setBackground(null);
            string_quadro_instalado_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoStringBoxInstalada().getValue()).into(string_quadro_instalado_confirmar);
        } else {
            string_quadro_instalado_confirmar.setVisibility(View.GONE);
            string_quadro_instalado_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoTensaoStringBox().getValue() != null){
            tensao_string_quadro_confirmar.setBackground(null);
            tensao_string_quadro_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoTensaoStringBox().getValue()).into(tensao_string_quadro_confirmar);
        } else {
            tensao_string_quadro_confirmar.setVisibility(View.GONE);
            tensao_string_quadro_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoPontoConexaoCa().getValue() != null){
            ponto_conexao_confirmar.setBackground(null);
            ponto_conexao_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoPontoConexaoCa().getValue()).into(ponto_conexao_confirmar);
        } else {
            ponto_conexao_confirmar.setVisibility(View.GONE);
            ponto_conexao_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoFinalInstalacaoKit().getValue() != null){
            layout_final_confirmar.setBackground(null);
            layout_final_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoFinalInstalacaoKit().getValue()).into(layout_final_confirmar);
        } else {
            layout_final_confirmar.setVisibility(View.GONE);
            layout_final_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getFotoPlacaSeguranca().getValue() != null){
            placas_Seguranca_confirmar.setBackground(null);
            placas_Seguranca_confirmar.setPadding(0, 0, 0, 0);
            Glide.with(this).load(entregaViewModel.getFotoPlacaSeguranca().getValue()).into(placas_Seguranca_confirmar);
        } else {
            placas_Seguranca_confirmar.setVisibility(View.GONE);
            placas_Seguranca_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getObsFinais().getValue() != null){
            obs_finais_confirmar.setText(entregaViewModel.getObsFinais().getValue());
        } else {
            obs_finais_confirmar.setVisibility(View.GONE);
            obs_finais_textView_confirmar.setVisibility(View.GONE);
        }

        if(entregaViewModel.getRessalvasTelhado().getValue() != null){
            ressalvas_confirmar.setText(entregaViewModel.getRessalvasTelhado().getValue());
        } else {
            ressalvas_confirmar.setVisibility(View.GONE);
            ressalvas_textView_confirmar.setVisibility(View.GONE);
        }
    }

    public void inicializarComponentes(View view){
        //TextViews
        tipo_cliente_confirmar_entrega = view.findViewById(R.id.tipo_cliente_confirmar_entrega);
        razao_social_confirmar = view.findViewById(R.id.razao_social_confirmar_entrega);
        responsavel_confirmar = view.findViewById(R.id.responsavel_confirmar_entrega);
        cpf_cnpj_confirmar = view.findViewById(R.id.cpf_cnpj_confirmar_entrega);
        email_confirmar = view.findViewById(R.id.email_confirmar_entrega);
        estrutura_fixacao_textView_confirmar = view.findViewById(R.id.estrutura_fixacao_textView_confirmar);
        modulos_instalados_textView_confirmar = view.findViewById(R.id.modulos_instalados_textView_confirmar);
        string_box_instalada_aberta_textView_confirmar = view.findViewById(R.id.string_box_instalada_aberta_textView_confirmar);
        string_quadro_instalado_textView_confirmar = view.findViewById(R.id.string_quadro_instalado_textView_confirmar);
        ponto_conexao_textView_confirmar = view.findViewById(R.id.ponto_conexao_textView_confirmar);
        layout_final_textView_confirmar = view.findViewById(R.id.layout_final_textView_confirmar);
        placas_Seguranca_textView_confirmar = view.findViewById(R.id.placas_Seguranca_textView_confirmar);
        obs_finais_textView_confirmar = view.findViewById(R.id.obs_finais_textView_confirmar);
        obs_finais_confirmar = view.findViewById(R.id.obs_finais_confirmar);
        ressalvas_textView_confirmar = view.findViewById(R.id.ressalvas_textView_confirmar);
        ressalvas_confirmar = view.findViewById(R.id.ressalvas_confirmar);
        tensao_string_quadro_textView_confirmar = view.findViewById(R.id.tensao_string_quadro_textView_confirmar);
        nome_cliente_confirmar = view.findViewById(R.id.nome_cliente_confirmar_entrega);
        data_visita_confirmar = view.findViewById(R.id.data_visita_confirmar_entrega);
        telefone_confirmar = view.findViewById(R.id.telefone_confirmar_entrega);
        endereco_confirmar = view.findViewById(R.id.endereco_confirmar_entrega);
        medidas_tensao_textView_confirmar = view.findViewById(R.id.medidas_tensao_textView_confirmar);

        //Layouts
        layout_tipo_cliente_confirmar_entrega = view.findViewById(R.id.layout_tipo_cliente_confirmar_entrega);
        layout_razao_social_confirmar = view.findViewById(R.id.layout_razao_social_confirmar_entrega);
        layout_responsavel_confirmar = view.findViewById(R.id.layout_responsavel_confirmar_entrega);
        layout_cpf_cnpj_confirmar = view.findViewById(R.id.layout_cpf_cnpj_confirmar_entrega);
        layout_email_confirmar = view.findViewById(R.id.layout_email_confirmar_entrega);

        //ImageViews
        estrutura_fixacao_confirmar = view.findViewById(R.id.estrutura_fixacao_confirmar);
        modulos_instalados_confirmar = view.findViewById(R.id.modulos_instalados_confirmar);
        string_box_instalada_aberta_confirmar = view.findViewById(R.id.string_box_instalada_aberta_confirmar);
        medidas_tensao_confirmar = view.findViewById(R.id.medidas_tensao_confirmar);
        string_quadro_instalado_confirmar = view.findViewById(R.id.string_quadro_instalado_confirmar);
        tensao_string_quadro_confirmar = view.findViewById(R.id.tensao_string_quadro_confirmar);
        ponto_conexao_confirmar = view.findViewById(R.id.ponto_conexao_confirmar);
        layout_final_confirmar = view.findViewById(R.id.layout_final_confirmar);
        placas_Seguranca_confirmar = view.findViewById(R.id.placas_Seguranca_confirmar);

        //Botões
        voltar = view.findViewById(R.id.btn_entrega_voltar_passo_5);
        finalizar = view.findViewById(R.id.btn_entrega_finalizar);
    }
}