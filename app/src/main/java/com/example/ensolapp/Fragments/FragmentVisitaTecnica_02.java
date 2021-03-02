package com.example.ensolapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ensolapp.R;

public class FragmentVisitaTecnica_02 extends Fragment {

    private Button btn_voltar, btn_avancar;

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
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragmentVisitaTecnica_02_to_fragmentVisitaTecnica_03);
            }
        });
    }

    private void inicializarComponentes(View view) {
        btn_voltar = (Button) view.findViewById(R.id.btn_vs_voltar_passo_1);
        btn_avancar = (Button) view.findViewById(R.id.btn_vs_avancar_passo_3);
    }
}