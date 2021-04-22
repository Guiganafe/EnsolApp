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

import com.example.ensolapp.Activity.OrcamentoActivity;
import com.example.ensolapp.Activity.VisualizarOrcamento;
import com.example.ensolapp.Adapters.OrcamentoAdapter;
import com.example.ensolapp.Base.OrcamentoBase;
import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OrcamentosFragment extends Fragment implements OrcamentoAdapter.onOrcamentoItemListenner{

    private FloatingActionButton fab_novo_form;
    private TextView tv_dia_selecionado, tv_sem_orcamentos;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano, diaColeta, mesColeta, anoColeta;
    private Calendar calendar;
    private ArrayList<Orcamento> orcamentos;
    private ArrayList<Orcamento> orcamentosPorDia;
    private ArrayList<String> orcamentosId;
    private ArrayList<String> orcamentosPorDiaId;
    private OrcamentoAdapter adapter;
    private RecyclerView recyclerView;

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
        carregarAgendaDoDia(dia, mes, ano);
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

        orcamentos = new ArrayList<>();

        orcamentos = OrcamentoBase.getInstance().getOrcamentos();
        orcamentosId = OrcamentoBase.getInstance().getOrcamentosId();

        if (orcamentos != null && orcamentos.size() > 0) {

            orcamentosPorDia = new ArrayList<>();
            orcamentosPorDiaId = new ArrayList<>();

            for(int i = 0; i < orcamentos.size(); i++){
                if(orcamentos.get(i).getDataSolicitacao().after(dataDoDia.getTime())){
                    if(orcamentos.get(i).getDataSolicitacao().before(dataDiaSeguinte.getTime())){
                        orcamentosPorDia.add(orcamentos.get(i));
                        orcamentosPorDiaId.add(orcamentosId.get(i));
                    }
                }
            }

            OrcamentoBase.getInstance().setOrcamentosPorDia(orcamentosPorDia);
            OrcamentoBase.getInstance().setOrcamentosPorDiaId(orcamentosPorDiaId);

            if(orcamentosPorDia.size() > 0){
                tv_sem_orcamentos.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                OrcamentoBase.getInstance().setOrcamentosPorDia(orcamentosPorDia);
            } else {
                OrcamentoBase.getInstance().resetarOrcamentosPorDia();
                tv_sem_orcamentos.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            ArrayList<Orcamento> orcamentoPorDia = OrcamentoBase.getInstance().getOrcamentosPorDia();

            adapter = new OrcamentoAdapter(orcamentoPorDia, this);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(adapter);
        } else {
            tv_sem_orcamentos.setVisibility(View.VISIBLE);
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
    public void onOrcamentoItemClick(int position) {
        Intent visualizarOrcamento = new Intent(requireActivity(), VisualizarOrcamento.class);
        visualizarOrcamento.putExtra("position", position);
        startActivity(visualizarOrcamento);
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        fab_novo_form = view.findViewById(R.id.fab_add_form_orcamento);
        tv_dia_selecionado = view.findViewById(R.id.tv_dia_selecionado_orcamento);
        tv_sem_orcamentos = view.findViewById(R.id.sem_orcamentos);
        recyclerView = view.findViewById(R.id.orcamentos_recycler_view);
    }
}