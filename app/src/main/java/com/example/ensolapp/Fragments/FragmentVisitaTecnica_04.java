package com.example.ensolapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Cliente;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentVisitaTecnica_04 extends Fragment {

    private Button btn_voltar, btn_finalizar, btn_baixar_pdf;
    private TextInputLayout edt_largura_telhado, edt_comprimento_telhado, edt_altura_telhado,
            edt_acesso_escada, edt_acesso_andaime, edt_obs_finais;
    private ClienteViewModel clienteViewModel;
    private VisitaTecnicaViewModel visitaTecnicaViewModel;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clienteViewModel =  new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        visitaTecnicaViewModel = new ViewModelProvider(requireActivity()).get(VisitaTecnicaViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        textWatcherController();
        loadViewModelController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_04, container, false);
    }


    private void loadViewModelController() {
        String largura_telhado = visitaTecnicaViewModel.getLarguraTelhado().getValue(),
                comprimento_telhado = visitaTecnicaViewModel.getComprimentoTelhado().getValue(),
                altura_telhado = visitaTecnicaViewModel.getAlturaTelhado().getValue(),
                acesso_escada = visitaTecnicaViewModel.getAcessoEscada().getValue(),
                acesso_andaime = visitaTecnicaViewModel.getAcessoAndaime().getValue(),
                obs_finais = visitaTecnicaViewModel.getObsFinais().getValue();

        if (!TextUtils.isEmpty(largura_telhado)){
            edt_largura_telhado.getEditText().setText(largura_telhado);
        }

        if (!TextUtils.isEmpty(comprimento_telhado)){
            edt_comprimento_telhado.getEditText().setText(comprimento_telhado);
        }

        if (!TextUtils.isEmpty(altura_telhado)){
            edt_altura_telhado.getEditText().setText(altura_telhado);
        }

        if (!TextUtils.isEmpty(acesso_escada)){
            edt_acesso_escada.getEditText().setText(acesso_escada);
        }

        if (!TextUtils.isEmpty(acesso_andaime)){
            edt_acesso_andaime.getEditText().setText(acesso_andaime);
        }

        if (!TextUtils.isEmpty(obs_finais)){
            edt_obs_finais.getEditText().setText(obs_finais);
        }
    }

    private void textWatcherController() {
        edt_largura_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setLarguraTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_comprimento_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setComprimentoTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_altura_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAlturaTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_acesso_escada.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAcessoEscada(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_acesso_andaime.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAcessoAndaime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_obs_finais.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setObsFinais(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_finalizar.setOnClickListener(v -> {
            try {
                salvar();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        btn_baixar_pdf.setOnClickListener(v -> baixarPermissao());
    }

    private void baixarPermissao() {
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        } else {
            gerarPDF();
        }
    }

    private void gerarPDF() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        canvas.drawText("Hello ensol app", 40, 50, paint);

        pdfDocument.finishPage(page);

        ContextWrapper cw = new ContextWrapper(requireActivity().getApplicationContext());
        File directory = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        File file = new File(directory, "ensol" + ".pdf");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pdfDocument.writeTo(fileOutputStream);
            Toast.makeText(requireActivity(), "PDF baixado com sucesso", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
        }

        pdfDocument.close();

    }

    private void salvar() throws ParseException {
        boolean valido = true;

        VisitaTecnica visitaTecnica = new VisitaTecnica();
        Cliente cliente = new Cliente();

        cliente.setTipoCliente(clienteViewModel.getTipoCliente().getValue());
        cliente.setNomeCliente(clienteViewModel.getNomeCliente().getValue());
        cliente.setRazaoSocial(clienteViewModel.getRazaoSocial().getValue());
        cliente.setResponsavel(clienteViewModel.getResponsavel().getValue());
        cliente.setTelefone(clienteViewModel.getTelefone().getValue());
        cliente.setCpf_cnpj(clienteViewModel.getCpf_cnpj().getValue());
        cliente.setEmail(clienteViewModel.getEmail().getValue());
        cliente.setEndereco(clienteViewModel.getEndereco().getValue());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(visitaTecnicaViewModel.getDataVisita().getValue());

        visitaTecnica.setDataVisita(data);
        visitaTecnica.setCliente(cliente.toMap());
        visitaTecnica.setPadraoEntrada(visitaTecnicaViewModel.getPadraoEntrada().getValue());
        visitaTecnica.setAperagemDisjuntosEntrada(visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue());
        visitaTecnica.setCondicaoPadraoEntrada(visitaTecnicaViewModel.getCondicaoPadraoEntrada().getValue());
        visitaTecnica.setLocalInstalacaoModulos(visitaTecnicaViewModel.getLocalInstalacaoModulos().getValue());
        visitaTecnica.setMaterialEstruturaTelhado(visitaTecnicaViewModel.getMaterialEstruturaTelhado().getValue());
        visitaTecnica.setCondicaoTelhado(visitaTecnicaViewModel.getCondicaoTelhado().getValue());
        visitaTecnica.setOrientacaoTelhado(visitaTecnicaViewModel.getOrientacaoTelhado().getValue());
        visitaTecnica.setLarguraTelhado(visitaTecnicaViewModel.getLarguraTelhado().getValue());
        visitaTecnica.setComprimentoTelhado(visitaTecnicaViewModel.getComprimentoTelhado().getValue());
        visitaTecnica.setAlturaTelhado(visitaTecnicaViewModel.getAlturaTelhado().getValue());
        visitaTecnica.setAcessoEscada(visitaTecnicaViewModel.getAcessoEscada().getValue());
        visitaTecnica.setAcessoAndaime(visitaTecnicaViewModel.getAcessoAndaime().getValue());
        visitaTecnica.setObsFinais(visitaTecnicaViewModel.getObsFinais().getValue());
        visitaTecnica.setTecnicoId(FirebaseService.getFirebaseUser().getUid());

        if(valido){
            db.collection("visitas_tecnicas")
                    .add(visitaTecnica.toMap())
                    .addOnSuccessListener(documentReference -> {

                    })
                    .addOnFailureListener(e -> {

                    });
            requireActivity().finish();
        }
    }

    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_vs_voltar_passo_2);
        btn_finalizar = view.findViewById(R.id.btn_finalizar);
        btn_baixar_pdf = view.findViewById(R.id.btn_baixar_pdf);
        edt_largura_telhado = view.findViewById(R.id.edt_vt_largura_telhado);
        edt_comprimento_telhado = view.findViewById(R.id.edt_vt_comprimento_telhado);
        edt_altura_telhado = view.findViewById(R.id.edt_vt_altura_telhado);
        edt_acesso_escada = view.findViewById(R.id.edt_vt_acesso_escada);
        edt_acesso_andaime = view.findViewById(R.id.edt_vt_acesso_andaime);
        edt_obs_finais = view.findViewById(R.id.edt_vt_obs_finais);
    }
}