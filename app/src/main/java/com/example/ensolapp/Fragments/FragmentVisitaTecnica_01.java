package com.example.ensolapp.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.ensolapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class FragmentVisitaTecnica_01 extends Fragment {

    private Button btn_cancelar, btn_avancar;
    private TextInputLayout edt_data, edt_nome, edt_razao_social, edt_responsavel,
                            edt_telefone, edt_cpf_cnpj, edt_email, edt_endereco;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano, diaColeta, mesColeta, anoColeta;
    private Calendar calendar;

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
        timeController();
    }

    private void timeController() {
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        edt_data.setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    diaColeta = dayOfMonth;
                    mesColeta = month;
                    anoColeta = year;
                    edt_data.getEditText().setText(String.format(Locale.getDefault(),"%02d/%02d/%04d", dayOfMonth, month+1, year));
                }
            },ano, mes, dia);
            datePickerDialog.show();
        });
    }

    private void onClickController() {
        btn_cancelar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_fragmentVisitaTecnica_01_to_fragmentVisitaTecnica_02));
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        btn_cancelar = view.findViewById(R.id.btn_vs_cancelar);
        btn_avancar = view.findViewById(R.id.btn_vs_avancar_passo_2);
        edt_data = view.findViewById(R.id.edt_vt_data);
        edt_nome = view.findViewById(R.id.edt_vt_nome_cliente);
        edt_razao_social = view.findViewById(R.id.edt_vt_razao_social);
        edt_responsavel = view.findViewById(R.id.edt_vt_responsavel);
        edt_telefone = view.findViewById(R.id.edt_vt_telefone);
        edt_cpf_cnpj = view.findViewById(R.id.edt_vt_cpf_cnpj);
        edt_email = view.findViewById(R.id.edt_vt_email);
        edt_endereco = view.findViewById(R.id.edt_vt_endereco);
    }
}