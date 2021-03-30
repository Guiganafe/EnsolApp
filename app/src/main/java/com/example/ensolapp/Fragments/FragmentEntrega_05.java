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

import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.EntregaViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentEntrega_05 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private TextInputLayout obs_finais, ressalvas_telhado;
    private EntregaViewModel entregaViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entregaViewModel =  new ViewModelProvider(requireActivity()).get(EntregaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_05, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        textWatcherController();
        loadViewModelController();
        onClickController();
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);
    }

    private void validarDados(View view) {
        boolean valido = true;

        if (valido){
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentEntrega_05_to_fragmentEntrega_06);
        }
    }

    private void loadViewModelController() {
        String  obs = entregaViewModel.getObsFinais().getValue(),
                ressalvas = entregaViewModel.getRessalvasTelhado().getValue();

        if(!TextUtils.isEmpty(obs)){
            obs_finais.getEditText().setText(obs);
        }

        if (!TextUtils.isEmpty(ressalvas)){
            ressalvas_telhado.getEditText().setText(ressalvas);
        }
    }

    private void textWatcherController() {
        obs_finais.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entregaViewModel.setObsFinais(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ressalvas_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entregaViewModel.setRessalvasTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_entrega_voltar_passo_4);
        btn_avancar = view.findViewById(R.id.btn_entrega_avancar_passo_6);
        obs_finais = view.findViewById(R.id.edt_entrega_obs_finais);
        ressalvas_telhado = view.findViewById(R.id.edt_entrega_ressalva_telhado);
    }
}