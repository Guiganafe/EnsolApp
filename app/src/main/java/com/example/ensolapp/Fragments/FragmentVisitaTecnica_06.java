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
import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDF;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentVisitaTecnica_06 extends Fragment {

    private VisitaTecnicaViewModel visitaTecnicaViewModel;
    private ClienteViewModel clienteViewModel;
    private Fotos fotos;
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
    private Button voltar_passo_5, finalizar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clienteViewModel =  new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        visitaTecnicaViewModel = new ViewModelProvider(requireActivity()).get(VisitaTecnicaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_06, container, false);
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
        voltar_passo_5.setOnClickListener(view -> getActivity().onBackPressed());
        finalizar.setOnClickListener(view -> {
            try {
                salvar();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void carregadDadosController() {

        nome_cliente.setText(clienteViewModel.getNomeCliente().getValue());
        telefone.setText(clienteViewModel.getTelefone().getValue());
        endereco.setText(clienteViewModel.getEndereco().getValue());

        if(clienteViewModel.getTipoCliente().getValue() != null){
            tipo_cliente.setText(clienteViewModel.getTipoCliente().getValue());
        } else {
            layout_tipo_cliente.setVisibility(View.GONE);
        }

        if(clienteViewModel.getRazaoSocial().getValue() != null){
            razao_social.setText(clienteViewModel.getRazaoSocial().getValue());
        } else {
            layout_razao_social.setVisibility(View.GONE);
        }

        if(clienteViewModel.getResponsavel().getValue() != null){
            responsavel.setText(clienteViewModel.getResponsavel().getValue());
        } else {
            layout_responsavel.setVisibility(View.GONE);
        }

        if(clienteViewModel.getCpf_cnpj().getValue() != null){
            cpf_cnpj.setText(clienteViewModel.getCpf_cnpj().getValue());
        } else {
            layout_cpf_cnpj.setVisibility(View.GONE);
        }

        if(clienteViewModel.getEmail().getValue() != null){
            email.setText(clienteViewModel.getEmail().getValue());
        } else {
            layout_email.setVisibility(View.GONE);
        }

        data_visita.setText(visitaTecnicaViewModel.getDataVisita().getValue());

        padrao_entrada.setText(visitaTecnicaViewModel.getPadraoEntrada().getValue());

        local_instalacao.setText(visitaTecnicaViewModel.getLocalInstalacaoModulos().getValue());

        material_telhado.setText(visitaTecnicaViewModel.getMaterialEstruturaTelhado().getValue());

        orientacao_telhado.setText(visitaTecnicaViewModel.getOrientacaoTelhado().getValue());

        numero_uc.setText(visitaTecnicaViewModel.getNumeroUc().getValue());

        tipo_ramal.setText(visitaTecnicaViewModel.getTipoRamal().getValue());

        acesso_telhado.setText(visitaTecnicaViewModel.getAcessoTelhado().getValue());

        if(visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue() != null){
            amperagem_disjuntor.setText(visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue());
        } else {
            layout_amperagem_disjuntor.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getCondicaoPadraoEntrada().getValue() != null){
            condicao_padrao.setText(visitaTecnicaViewModel.getCondicaoPadraoEntrada().getValue());
        } else {
            layout_condicao_padrao.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getCondicaoTelhado().getValue() != null){
            condicao_telhado.setText(visitaTecnicaViewModel.getCondicaoTelhado().getValue());
        } else {
            layout_condicao_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getLarguraTelhado().getValue() != null){
            largura_telhado.setText(visitaTecnicaViewModel.getLarguraTelhado().getValue());
        } else {
            layout_largura_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getComprimentoTelhado().getValue() != null){
            largura_comprimento.setText(visitaTecnicaViewModel.getComprimentoTelhado().getValue());
        } else {
            layout_comprimento_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getAlturaTelhado().getValue() != null){
            altura_telhado.setText(visitaTecnicaViewModel.getAlturaTelhado().getValue());
        } else {
            layout_altura_telhado.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getObsFinais().getValue() != null){
            obs_finais_vizualizar.setText(visitaTecnicaViewModel.getObsFinais().getValue());
        } else {
            obs_finais_vizualizar.setVisibility(View.GONE);
            obs_finais_textView.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue() != null){
            foto_padrao_entrada.setBackground(null);
            foto_padrao_entrada.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue()).into(foto_padrao_entrada);
        } else {
            foto_padrao_entrada.setVisibility(View.GONE);
            foto_padrao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue() != null){
            foto_orientacao_telhado.setBackground(null);
            foto_orientacao_telhado.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue()).into(foto_orientacao_telhado);
        } else {
            foto_orientacao_telhado.setVisibility(View.GONE);
            foto_orientacao_textView.setVisibility(View.GONE);
        }

        if(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue() != null){
            foto_acesso_telhado.setBackground(null);
            foto_acesso_telhado.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue()).into(foto_acesso_telhado);
        } else {
            foto_acesso_telhado.setVisibility(View.GONE);
            foto_acesso_textView.setVisibility(View.GONE);
        }


        if(visitaTecnicaViewModel.getFotoLocalInstalacaoInversor().getValue() != null){
            foto_inversor.setBackground(null);
            foto_inversor.setPadding(0, 0, 0, 0);
            Glide.with(this).load(visitaTecnicaViewModel.getFotoLocalInstalacaoInversor().getValue()).into(foto_inversor);
        } else {
            foto_inversor.setVisibility(View.GONE);
            foto_inversor_textView.setVisibility(View.GONE);
        }
    }

    private void salvar() throws ParseException {

        VisitaTecnica visitaTecnica = new VisitaTecnica();
        Cliente cliente = new Cliente();

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
        Date data = formato.parse(visitaTecnicaViewModel.getDataVisita().getValue());
        Calendar dataDoDia = Calendar.getInstance(Locale.getDefault());
        data.setHours(dataDoDia.getTime().getHours());
        data.setMinutes(dataDoDia.getTime().getMinutes());

        //Dados obrigatórios
        visitaTecnica.setDataVisita(data);
        visitaTecnica.setCliente(cliente.toMap());
        visitaTecnica.setPadraoEntrada(visitaTecnicaViewModel.getPadraoEntrada().getValue());
        visitaTecnica.setLocalInstalacaoModulos(visitaTecnicaViewModel.getLocalInstalacaoModulos().getValue());
        visitaTecnica.setMaterialEstruturaTelhado(visitaTecnicaViewModel.getMaterialEstruturaTelhado().getValue());
        visitaTecnica.setOrientacaoTelhado(visitaTecnicaViewModel.getOrientacaoTelhado().getValue());
        visitaTecnica.setTecnicoId(FirebaseService.getFirebaseUser().getUid());

        //Dados não obrigatórios
        if(visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue() != null){
            visitaTecnica.setAmperagemDisjuntosEntrada(visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue());
        }

        if(visitaTecnicaViewModel.getCondicaoPadraoEntrada().getValue() != null){
            visitaTecnica.setCondicaoPadraoEntrada(visitaTecnicaViewModel.getCondicaoPadraoEntrada().getValue());
        }

        if(visitaTecnicaViewModel.getCondicaoTelhado().getValue() != null){
            visitaTecnica.setCondicaoTelhado(visitaTecnicaViewModel.getCondicaoTelhado().getValue());
        }

        if(visitaTecnicaViewModel.getLarguraTelhado().getValue() != null){
            visitaTecnica.setLarguraTelhado(visitaTecnicaViewModel.getLarguraTelhado().getValue());
        }

        if(visitaTecnicaViewModel.getComprimentoTelhado().getValue() != null){
            visitaTecnica.setComprimentoTelhado(visitaTecnicaViewModel.getComprimentoTelhado().getValue());
        }

        if(visitaTecnicaViewModel.getAlturaTelhado().getValue() != null){
            visitaTecnica.setAlturaTelhado(visitaTecnicaViewModel.getAlturaTelhado().getValue());
        }

        if(visitaTecnicaViewModel.getObsFinais().getValue() != null){
            visitaTecnica.setObsFinais(visitaTecnicaViewModel.getObsFinais().getValue());
        }

        Fotos fotos = new Fotos();

        if(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue() != null){
            fotos.setFoto_padrao(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue());
            visitaTecnica.setFotoPadrao(visitaTecnicaViewModel.getFotoPadraoEntradaUrl().getValue());
        }

        if(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue() != null){
            fotos.setFoto_acesso_telhado(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue());
            visitaTecnica.setFotoAcessoTelhado(visitaTecnicaViewModel.getFotoAcessoTelhadoUrl().getValue());
        }

        if(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue() != null){
            fotos.setFoto_orientacao_telhado(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue());
            visitaTecnica.setFotoOrientacaoTelhado(visitaTecnicaViewModel.getFotoOrientacaoTelhadoUrl().getValue());
        }

        if(visitaTecnicaViewModel.getFotoLocalInstalacaoInversor().getValue() != null){
            fotos.setFotoLocalInstalacaoInversor(visitaTecnicaViewModel.getFotoLocalInstalacaoInversor().getValue());
            visitaTecnica.setFotoLocalInstalacaoInversor(visitaTecnicaViewModel.getFotoLocalInstalacaoInversorUrl().getValue());
        }

        baixarPermissao(visitaTecnica, fotos);

        db.collection("visitas_tecnicas")
                .add(visitaTecnica.toMap())
                .addOnSuccessListener(documentReference -> {

                })
                .addOnFailureListener(e -> {

                });

        requireActivity().finish();
    }

    private void baixarPermissao(VisitaTecnica visitaTecnica, Fotos fotos) {
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        } else {
            try{
                GerarPDF.gerarPDF(requireActivity(), visitaTecnica, fotos);
            }catch (FileNotFoundException fe){
                fe.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inicializarComponentes(View view) {
        layout_tipo_cliente = view.findViewById(R.id.layout_tipo_cliente_confirmar);
        layout_razao_social = view.findViewById(R.id.layout_razao_social_confirmar);
        layout_responsavel = view.findViewById(R.id.layout_responsavel_confirmar);
        layout_cpf_cnpj = view.findViewById(R.id.layout_cpf_cnpj_confirmar);
        layout_email = view.findViewById(R.id.layout_email_confirmar);
        layout_amperagem_disjuntor = view.findViewById(R.id.layout_amperagem_disjuntor_confirmar);
        layout_condicao_padrao = view.findViewById(R.id.layout_condicao_padrao_confirmar);
        layout_condicao_telhado = view.findViewById(R.id.layout_condicao_telhado_confirmar);
        layout_largura_telhado = view.findViewById(R.id.layout_largura_telhado_confirmar);
        layout_comprimento_telhado = view.findViewById(R.id.layout_comprimento_telhado_confirmar);
        layout_altura_telhado = view.findViewById(R.id.layout_altura_telhado_confirmar);

        data_visita = view.findViewById(R.id.data_visita_confirmar);
        tipo_cliente = view.findViewById(R.id.tipo_cliente_confirmar);
        nome_cliente = view.findViewById(R.id.nome_cliente_confirmar);
        razao_social = view.findViewById(R.id.razao_social_confirmar);
        responsavel = view.findViewById(R.id.responsavel_confirmar);
        telefone = view.findViewById(R.id.telefone_confirmar);
        cpf_cnpj = view.findViewById(R.id.cpf_cnpj_confirmar);
        email = view.findViewById(R.id.email_confirmar);
        endereco = view.findViewById(R.id.endereco_confirmar);
        padrao_entrada = view.findViewById(R.id.padrao_entrada_confirmar);
        amperagem_disjuntor = view.findViewById(R.id.amperagem_disjuntor_confirmar);
        condicao_padrao = view.findViewById(R.id.condicao_padrao_confirmar);
        local_instalacao = view.findViewById(R.id.local_instalacao_confirmar);
        material_telhado = view.findViewById(R.id.material_telhado_confirmar);
        condicao_telhado = view.findViewById(R.id.condicao_telhado_confirmar);
        orientacao_telhado = view.findViewById(R.id.orientacao_telhado_confirmar);
        largura_telhado = view.findViewById(R.id.largura_telhado_confirmar);
        largura_comprimento = view.findViewById(R.id.largura_comprimento_confirmar);
        altura_telhado = view.findViewById(R.id.altura_telhado_confirmar);
        obs_finais_vizualizar = view.findViewById(R.id.obs_finais_confirmar);
        obs_finais_textView = view.findViewById(R.id.obs_finais_textView_confirmar);
        foto_padrao_textView = view.findViewById(R.id.foto_padrao_textView_confirmar);
        foto_orientacao_textView = view.findViewById(R.id.foto_orientacao_textView_confirmar);
        foto_acesso_textView = view.findViewById(R.id.foto_acesso_textView_confirmar);
        foto_inversor_textView = view.findViewById(R.id.foto_inversor_textView_confirmar);
        acesso_telhado = view.findViewById(R.id.acesso_telhado_confirmar);
        tipo_ramal = view.findViewById(R.id.tipo_ramal_confirmar);
        numero_uc = view.findViewById(R.id.numero_uc_confirmar);

        foto_padrao_entrada = view.findViewById(R.id.foto_padrao_entrada_confirmar);
        foto_orientacao_telhado = view.findViewById(R.id.foto_orientacao_telhado_confirmar);
        foto_acesso_telhado = view.findViewById(R.id.foto_acesso_telhado_confirmar);
        foto_inversor = view.findViewById(R.id.foto_inversor_confirmar);

        voltar_passo_5 = view.findViewById(R.id.btn_vs_voltar_passo_5);
        finalizar = view.findViewById(R.id.btn_vs_finalizar);
    }
}