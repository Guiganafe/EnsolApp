package com.example.ensolapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ensolapp.Activity.VisitaTecnicaActivity;
import com.example.ensolapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ColetasFragment extends Fragment {

    private FloatingActionButton fab_novo_form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coletas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
    }

    private void onClickController() {
        fab_novo_form.setOnClickListener(v -> novoForm());
    }

    private void novoForm() {
        Intent novo_form = new Intent(requireActivity(), VisitaTecnicaActivity.class);
        startActivity(novo_form);
    }

    private void inicializarComponentes(View view) {
        fab_novo_form = view.findViewById(R.id.fab_add_form);
    }
}

