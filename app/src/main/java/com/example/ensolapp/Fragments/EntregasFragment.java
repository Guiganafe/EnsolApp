package com.example.ensolapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ensolapp.Activity.EntregaActivity;
import com.example.ensolapp.Activity.VisualizarEntrega;
import com.example.ensolapp.Activity.VisualizarVisita;
import com.example.ensolapp.Adapters.EntregasAdapter;
import com.example.ensolapp.Base.EntregaBase;
import com.example.ensolapp.Models.Entrega;
import com.example.ensolapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EntregasFragment extends Fragment implements EntregasAdapter.onEntregaItemListenner {

    private FloatingActionButton fab_novo_form;
    private TextView tv_dia_selecionado, tv_sem_entregas;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano, diaColeta, mesColeta, anoColeta;
    private Calendar calendar;
    private ArrayList<Entrega> entregas;
    private ArrayList<Entrega> entregasPorDia;
    private ArrayList<String> entregasId;
    private ArrayList<String>entregasPorDiaId;
    private EntregasAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entregas, container, false);
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
        carregarAgendaDoDia(dia, mes, ano);
    }

    private void onClickController() {
        fab_novo_form.setOnClickListener(v -> novoForm());
        tv_dia_selecionado.setOnClickListener(v -> selecionarData());
    }

    private void novoForm() {
        Intent novo_form = new Intent(requireActivity(), EntregaActivity.class);
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
                    carregarAgendaDoDia(diaColeta, mesColeta+1, anoColeta);
                }
            },ano, mes, dia);
            datePickerDialog.show();
        });
    }

    public void carregarAgendaDoDia(int dia, int mes, int ano){
        final Calendar dataDoDia = Calendar.getInstance(Locale.getDefault()), dataDiaSeguinte = Calendar.getInstance(Locale.getDefault());
        dataDoDia.set(ano, mes-1, dia, 0, 0, 0);
        dataDiaSeguinte.set(ano, mes-1, dia, 23 ,59, 59);

        entregas = new ArrayList<>();

        entregas = EntregaBase.getInstance().getEntregas();
        entregasId = EntregaBase.getInstance().getEntregasId();

        if (entregas != null && entregas.size() > 0) {

            entregasPorDia = new ArrayList<>();
            entregasPorDiaId = new ArrayList<>();

            for(int i = 0; i < entregas.size(); i++){
                if(entregas.get(i).getDataVisita().after(dataDoDia.getTime())){
                    if(entregas.get(i).getDataVisita().before(dataDiaSeguinte.getTime())){
                        entregasPorDia.add(entregas.get(i));
                        entregasPorDiaId.add(entregasId.get(i));
                    }
                }
            }

            EntregaBase.getInstance().setEntregasPorDia(entregasPorDia);
            EntregaBase.getInstance().setEntregasPorDiaId(entregasPorDiaId);

            if(entregasPorDia.size() > 0){
                tv_sem_entregas.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                EntregaBase.getInstance().setEntregasPorDia(entregasPorDia);
            } else {
                EntregaBase.getInstance().resetarEntregasPorDia();
                tv_sem_entregas.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            ArrayList<Entrega> entregaPorDia = EntregaBase.getInstance().getEntregasPorDia();

            adapter = new EntregasAdapter(entregaPorDia, this);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(adapter);
        } else {
            tv_sem_entregas.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        carregarAgendaDoDia(dia, mes+1, ano);
    }

    @Override
    public void onEntregaItemClick(int position) {
        Intent visualizarEntrega = new Intent(requireActivity(), VisualizarEntrega.class);
        visualizarEntrega.putExtra("position", position);
        startActivity(visualizarEntrega);
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        fab_novo_form = view.findViewById(R.id.fab_add_form_entrega);
        tv_dia_selecionado = view.findViewById(R.id.tv_dia_selecionado_entrega);
        tv_sem_entregas = view.findViewById(R.id.sem_entregas);
        recyclerView = view.findViewById(R.id.entregas_recycler_view);
    }
}