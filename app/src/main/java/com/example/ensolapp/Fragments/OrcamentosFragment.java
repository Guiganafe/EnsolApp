package com.example.ensolapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ensolapp.Activity.EntregaActivity;
import com.example.ensolapp.Activity.OrcamentoActivity;
import com.example.ensolapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Locale;

public class OrcamentosFragment extends Fragment {

    private FloatingActionButton fab_novo_form;
    private TextView tv_dia_selecionado, tv_sem_entregas;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano, diaColeta, mesColeta, anoColeta;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orcamentos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        definirData();
    }

    private void definirData() {
        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH), mes = cal.get(Calendar.MONTH) + 1, ano = cal.get(Calendar.YEAR);
        tv_dia_selecionado.setText(String.format(Locale.getDefault(),"%02d/%02d/%d", dia, mes, ano));
        //carregarAgendaDoDia(dia, mes, ano);
    }

    private void onClickController() {
        fab_novo_form.setOnClickListener(v -> novoForm());
        tv_dia_selecionado.setOnClickListener(v -> selecionarData());
    }

    private void novoForm() {
        Intent novo_form = new Intent(requireActivity(), OrcamentoActivity.class);
        startActivity(novo_form);
    }

    private void selecionarData() {
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        tv_dia_selecionado.setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    diaColeta = dayOfMonth;
                    mesColeta = month;
                    anoColeta = year;
                    tv_dia_selecionado.setText(String.format(Locale.getDefault(),"%02d/%02d/%04d", dayOfMonth, month+1, year));
                    //carregarAgendaDoDia(diaColeta, mesColeta+1, anoColeta);
                }
            },ano, mes, dia);
            datePickerDialog.show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        //carregarAgendaDoDia(dia, mes+1, ano);
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        fab_novo_form = view.findViewById(R.id.fab_add_form_orcamento);
        tv_dia_selecionado = view.findViewById(R.id.tv_dia_selecionado_orcamento);
        tv_sem_entregas = view.findViewById(R.id.sem_orcamentos);
    }
}