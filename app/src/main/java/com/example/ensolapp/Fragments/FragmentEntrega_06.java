package com.example.ensolapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.EntregaViewModel;

public class FragmentEntrega_06 extends Fragment {

    private ClienteViewModel clienteViewModel;
    private EntregaViewModel entregaViewModel;
    private Button voltar, finalizar;
    private TextView tipo_cliente_confirmar_entrega;
    private LinearLayout layout_tipo_cliente_confirmar_entrega;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_06, container, false);
    }
}