package com.example.ensolapp.Fragments;

import android.app.Activity;
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

public class FragmentVisitaTecnica_01 extends Fragment {

    private Button btn_cancelar, btn_avancar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_01, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
    }

    private void onClickController() {
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragmentVisitaTecnica_01_to_fragmentVisitaTecnica_02);
            }
        });
    }

    private void inicializarComponentes(View view) {
        btn_cancelar = (Button) view.findViewById(R.id.btn_vs_cancelar);
        btn_avancar = (Button) view.findViewById(R.id.btn_vs_avancar_passo_2);
    }
}