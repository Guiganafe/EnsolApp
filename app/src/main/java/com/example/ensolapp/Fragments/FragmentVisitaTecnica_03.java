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

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Cliente;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentVisitaTecnica_03 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private RadioGroup rg_material_estrutura, rg_condicao_telhado, rg_orientacao_telhado;
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
        return inflater.inflate(R.layout.fragment_visita_tecnica_03, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        radioGroupController(view);
        loadViewModelController();
    }

    private void loadViewModelController() {
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

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_fragmentVisitaTecnica_03_to_fragmentVisitaTecnica_04));
    }

    private void inicializarComponentes(View view) {
        btn_voltar = (Button) view.findViewById(R.id.btn_vs_voltar_passo_2);
        btn_avancar = (Button) view.findViewById(R.id.btn_vs_avancar_passo_4);
        rg_material_estrutura = view.findViewById(R.id.rg_material_estrutura);
        rg_condicao_telhado = view.findViewById(R.id.rg_condicao_telhado);
        rg_orientacao_telhado = view.findViewById(R.id.rg_orientacao_telhado);
    }

}