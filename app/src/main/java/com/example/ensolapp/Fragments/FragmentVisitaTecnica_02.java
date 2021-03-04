package com.example.ensolapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentVisitaTecnica_02 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private TextInputLayout edt_amperagem_disjuntor;
    private RadioGroup rg_padrao_entrada, rg_condicao_padrao_entrada, rg_local_instalacao, rg_material_estrutura,
            rg_condicao_telhado, rg_orientacao_telhado;
    private RadioButton rb_checked;
    private VisitaTecnicaViewModel visitaTecnicaViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visitaTecnicaViewModel = new ViewModelProvider(requireActivity()).get(VisitaTecnicaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_02, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        textWatcherController();
        radioGroupController(view);
        loadViewModelController();
    }

    private void loadViewModelController() {
        String amperagem = visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue();

        if (!TextUtils.isEmpty(amperagem)){
            edt_amperagem_disjuntor.getEditText().setText(amperagem);
        }

        if(visitaTecnicaViewModel.getPadraoEntradaPosition().getValue() != null){
            rg_padrao_entrada.check(rg_padrao_entrada.getChildAt(visitaTecnicaViewModel.getPadraoEntradaPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getCondicaoPadraoEntradaPositon().getValue() != null){
            rg_condicao_padrao_entrada.check(rg_condicao_padrao_entrada.getChildAt(visitaTecnicaViewModel.getCondicaoPadraoEntradaPositon().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getLocalInstalacaoModulosPosition().getValue() != null){
            rg_local_instalacao.check(rg_local_instalacao.getChildAt(visitaTecnicaViewModel.getLocalInstalacaoModulosPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getMaterialEstruturaTelhadoPosition().getValue() != null){
            rg_material_estrutura.check(rg_material_estrutura.getChildAt(visitaTecnicaViewModel.getMaterialEstruturaTelhadoPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getCondicaoTelhadoPosition().getValue() != null){
            rg_condicao_telhado.check(rg_condicao_telhado.getChildAt(visitaTecnicaViewModel.getCondicaoTelhadoPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getOrientacaoTelhadoPosition().getValue() != null){
            rg_orientacao_telhado.check(rg_orientacao_telhado.getChildAt(visitaTecnicaViewModel.getOrientacaoTelhadoPosition().getValue()).getId());
        }
    }

    private void radioGroupController(View view) {
        rg_padrao_entrada.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setPadraoEntrada(textoRB);
            visitaTecnicaViewModel.setPadraoEntradaPosition(position);
        });

        rg_condicao_padrao_entrada.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setCondicaoPadraoEntrada(textoRB);
            visitaTecnicaViewModel.setCondicaoPadraoEntradaPositon(position);
        });

        rg_local_instalacao.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setLocalInstalacaoModulos(textoRB);
            visitaTecnicaViewModel.setLocalInstalacaoModulosPosition(position);
        });

        rg_material_estrutura.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setMaterialEstruturaTelhado(textoRB);
            visitaTecnicaViewModel.setMaterialEstruturaTelhadoPosition(position);
        });

        rg_condicao_telhado.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setCondicaoTelhado(textoRB);
            visitaTecnicaViewModel.setCondicaoTelhadoPosition(position);
        });

        rg_orientacao_telhado.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setOrientacaoTelhado(textoRB);
            visitaTecnicaViewModel.setOrientacaoTelhadoPosition(position);
        });
    }

    private void textWatcherController() {
        edt_amperagem_disjuntor.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAperagemDisjuntosEntrada(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_fragmentVisitaTecnica_02_to_fragmentVisitaTecnica_03));
    }

    private void inicializarComponentes(View view) {
        btn_voltar = (Button) view.findViewById(R.id.btn_vs_voltar_passo_1);
        btn_avancar = (Button) view.findViewById(R.id.btn_vs_avancar_passo_3);
        edt_amperagem_disjuntor = view.findViewById(R.id.edt_vt_amperagem_disjuntor);
        rg_padrao_entrada = view.findViewById(R.id.rg_padrao_entrada);
        rg_condicao_padrao_entrada = view.findViewById(R.id.rg_condicao_padrao_entrada);
        rg_local_instalacao = view.findViewById(R.id.rg_local_instalacao);
        rg_material_estrutura = view.findViewById(R.id.rg_material_estrutura);
        rg_condicao_telhado = view.findViewById(R.id.rg_condicao_telhado);
        rg_orientacao_telhado = view.findViewById(R.id.rg_orientacao_telhado);
    }
}