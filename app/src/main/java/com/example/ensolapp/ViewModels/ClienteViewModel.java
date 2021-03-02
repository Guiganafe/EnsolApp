package com.example.ensolapp.ViewModels;

import androidx.lifecycle.MutableLiveData;

public class ClienteViewModel {
    private MutableLiveData<String> tipoCliente = new MutableLiveData<>();
    private MutableLiveData<String> nomeCliente = new MutableLiveData<>();
    private MutableLiveData<String> razaoSocial = new MutableLiveData<>();
    private MutableLiveData<String> responsavel = new MutableLiveData<>();
    private MutableLiveData<String> telefone = new MutableLiveData<>();
    private MutableLiveData<String> cpf_cnpj = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> endereco = new MutableLiveData<>();

    public ClienteViewModel(MutableLiveData<String> tipoCliente, MutableLiveData<String> nomeCliente,
                            MutableLiveData<String> razaoSocial, MutableLiveData<String> responsavel,
                            MutableLiveData<String> telefone, MutableLiveData<String> cpf_cnpj,
                            MutableLiveData<String> email, MutableLiveData<String> endereco) {
        this.tipoCliente = tipoCliente;
        this.nomeCliente = nomeCliente;
        this.razaoSocial = razaoSocial;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.endereco = endereco;
    }

    public MutableLiveData<String> getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente.setValue(tipoCliente);
    }

    public MutableLiveData<String> getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente.setValue(nomeCliente);
    }

    public MutableLiveData<String> getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial.setValue(razaoSocial);
    }

    public MutableLiveData<String> getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel.setValue(responsavel);
    }

    public MutableLiveData<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.setValue(telefone);
    }

    public MutableLiveData<String> getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj.setValue(cpf_cnpj);
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public MutableLiveData<String> getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.setValue(endereco);
    }
}
