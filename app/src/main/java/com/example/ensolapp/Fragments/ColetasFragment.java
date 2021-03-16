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

import com.example.ensolapp.Activity.VisitaTecnicaActivity;
import com.example.ensolapp.Activity.VisualizarVisita;
import com.example.ensolapp.Adapters.VisitaTecnicaAdapter;
import com.example.ensolapp.Base.VisitaTecnicaBase;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ColetasFragment extends Fragment implements VisitaTecnicaAdapter.onVisitaTecnicaItemListenner{

    private FloatingActionButton fab_novo_form;
    private TextView tv_dia_selecionado, tv_sem_visitas;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano, diaColeta, mesColeta, anoColeta;
    private Calendar calendar;
    private ArrayList<VisitaTecnica> visitaTecnicas;
    private ArrayList<VisitaTecnica > visitaTecnicasPorDia;
    private ArrayList<String> visitaTecnicasId;
    private ArrayList<String> visitaTecnicasIdPorDia;
    private VisitaTecnicaAdapter adapter;
    //private AdapterVisitaTecnica adapter;
    private RecyclerView recyclerView;

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

        visitaTecnicas = new ArrayList<>();

        visitaTecnicas = VisitaTecnicaBase.getInstance().getVisitaTecnica();
        visitaTecnicasId = VisitaTecnicaBase.getInstance().getVisitaTecnicaId();

        if (visitaTecnicas != null && visitaTecnicas.size() > 0) {

            visitaTecnicasPorDia = new ArrayList<>();
            visitaTecnicasIdPorDia = new ArrayList<>();

            for(int i = 0; i < visitaTecnicas.size(); i++){
                if(visitaTecnicas.get(i).getDataVisita().after(dataDoDia.getTime())){
                    if(visitaTecnicas.get(i).getDataVisita().before(dataDiaSeguinte.getTime())){
                        visitaTecnicasPorDia.add(visitaTecnicas.get(i));
                        visitaTecnicasIdPorDia.add(visitaTecnicasId.get(i));
                    }
                }
            }

            VisitaTecnicaBase.getInstance().setVisitaTecnicaPorDia(visitaTecnicasPorDia);
            VisitaTecnicaBase.getInstance().setVisitaTecnicaIdPorDia(visitaTecnicasIdPorDia);

            if(visitaTecnicasPorDia.size() > 0){
                tv_sem_visitas.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                VisitaTecnicaBase.getInstance().setVisitaTecnicaPorDia(visitaTecnicasPorDia);
            } else {
                VisitaTecnicaBase.getInstance().resetarVisitasTecnicasPorDia();
                tv_sem_visitas.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            ArrayList<VisitaTecnica> visitaTecnicaPorDia = VisitaTecnicaBase.getInstance().getVisitaTecnicaPorDia();

            adapter = new VisitaTecnicaAdapter(visitaTecnicaPorDia, this);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(adapter);
        } else {
            tv_sem_visitas.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void novoForm() {
        Intent novo_form = new Intent(requireActivity(), VisitaTecnicaActivity.class);
        startActivity(novo_form);
    }

    @Override
    public void onVisitaTecnicaItemClick(int position) {
        Intent visualizarVisita = new Intent(requireActivity(), VisualizarVisita.class);
        visualizarVisita.putExtra("position", position);
        startActivity(visualizarVisita);
    }

    @Override
    public void onStart() {
        super.onStart();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        carregarAgendaDoDia(dia, mes+1, ano);
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        fab_novo_form = view.findViewById(R.id.fab_add_form);
        tv_dia_selecionado = view.findViewById(R.id.tv_dia_selecionado);
        recyclerView = view.findViewById(R.id.visitas_tecnicas_recycler_view);
        tv_sem_visitas = view.findViewById(R.id.sem_visitas);
    }
}

