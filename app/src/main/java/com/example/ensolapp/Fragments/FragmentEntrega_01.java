package com.example.ensolapp.Fragments;

import android.app.DatePickerDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.EntregaViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class FragmentEntrega_01 extends Fragment {

    private Button btn_cancelar, btn_avancar;
    private TextInputLayout edt_data, edt_nome, edt_razao_social, edt_responsavel,
            edt_telefone, edt_cpf_cnpj, edt_email, edt_endereco;
    private RadioGroup rg_tipo_cliente;
    private RadioButton rb_checked;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano;
    private Calendar calendar;
    private ClienteViewModel clienteViewModel;
    private EntregaViewModel entregaViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clienteViewModel =  new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        entregaViewModel =  new ViewModelProvider(requireActivity()).get(EntregaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_01, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        timeController();
        textWatcherController();
        loadViewModelController();
        radioGroupController(view);
    }

    private void radioGroupController(View view) {
        rg_tipo_cliente.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            clienteViewModel.setTipoCliente(textoRB);
            clienteViewModel.setTipoClientePosition(position);
        });
    }

    private void loadViewModelController() {
        String data = entregaViewModel.getDataVisita().getValue(),
                nome_cliente = clienteViewModel.getNomeCliente().getValue(),
                razao_social = clienteViewModel.getRazaoSocial().getValue(),
                responsavel = clienteViewModel.getResponsavel().getValue(),
                telefone = clienteViewModel.getTelefone().getValue(),
                cpf_cnpj = clienteViewModel.getCpf_cnpj().getValue(),
                email = clienteViewModel.getEmail().getValue(),
                endereco = clienteViewModel.getEndereco().getValue();

        if(!TextUtils.isEmpty(data)){
            edt_data.getEditText().setText(data);
        }

        if (!TextUtils.isEmpty(nome_cliente)){
            edt_nome.getEditText().setText(nome_cliente);
        }

        if (!TextUtils.isEmpty(razao_social)){
            edt_razao_social.getEditText().setText(razao_social);
        }

        if (!TextUtils.isEmpty(responsavel)){
            edt_responsavel.getEditText().setText(responsavel);
        }

        if (!TextUtils.isEmpty(telefone)){
            edt_telefone.getEditText().setText(telefone);
        }

        if (!TextUtils.isEmpty(cpf_cnpj)){
            edt_cpf_cnpj.getEditText().setText(cpf_cnpj);
        }

        if (!TextUtils.isEmpty(email)){
            edt_email.getEditText().setText(email);
        }

        if (!TextUtils.isEmpty(endereco)){
            edt_endereco.getEditText().setText(endereco);
        }

        if(clienteViewModel.getTipoClientePosition().getValue() != null){
            rg_tipo_cliente.check(rg_tipo_cliente.getChildAt(clienteViewModel.getTipoClientePosition().getValue()).getId());
        }
    }

    private void textWatcherController() {
        edt_nome.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setNomeCliente(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_razao_social.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setRazaoSocial(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_responsavel.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setResponsavel(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_telefone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setTelefone(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_cpf_cnpj.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setCpf_cnpj(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_endereco.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteViewModel.setEndereco(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void timeController() {
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        edt_data.getEditText().setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
                String data = String.format(Locale.getDefault(),"%02d/%02d/%04d", dayOfMonth, month+1, year);
                edt_data.getEditText().setText(data);
                entregaViewModel.setDataVisita(data);
            },ano, mes, dia);
            datePickerDialog.show();
        });
    }

    private void onClickController() {
        btn_cancelar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(entregaViewModel.getDataVisita().getValue() == null){
            edt_data.setError("Insira a data");
            valido = false;
        } else if(clienteViewModel.getNomeCliente().getValue() == null){
            edt_nome.setError("Insira o nome do cliente");
            valido = false;
        } else if(clienteViewModel.getTelefone().getValue() == null){
            edt_telefone.setError("Insira um número válido");
            valido = false;
        } else if(clienteViewModel.getEndereco().getValue() == null){
            edt_endereco.setError("Insira um endereço");
            valido = false;
        }

        if (valido){
            edt_data.setError(null);
            edt_nome.setError(null);
            edt_telefone.setError(null);
            edt_endereco.setError(null);
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentEntrega_01_to_fragmentEntrega_02);
        }
    }

    private void inicializarComponentes(View view) {
        calendar = Calendar.getInstance();
        btn_cancelar = view.findViewById(R.id.btn_entrega_cancelar);
        btn_avancar = view.findViewById(R.id.btn_entrega_avancar_passo_2);
        edt_data = view.findViewById(R.id.edt_entrega_data);
        edt_nome = view.findViewById(R.id.edt_entrega_nome_cliente);
        edt_razao_social = view.findViewById(R.id.edt_entrega_razao_social);
        edt_responsavel = view.findViewById(R.id.edt_entrega_responsavel);
        edt_telefone = view.findViewById(R.id.edt_entrega_telefone);
        edt_cpf_cnpj = view.findViewById(R.id.edt_entrega_cpf_cnpj);
        edt_email = view.findViewById(R.id.edt_entrega_email);
        edt_endereco = view.findViewById(R.id.edt_entrega_endereco);
        rg_tipo_cliente = view.findViewById(R.id.rg_entrega_tipo_cliente);
    }
}