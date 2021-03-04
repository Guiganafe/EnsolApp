package com.example.ensolapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClienteViewModel extends ViewModel {

    private MutableLiveData<String> tipoCliente = new MutableLiveData<>();
    private MutableLiveData<Integer> tipoClientePosition = new MutableLiveData<>();
    private MutableLiveData<String> nomeCliente = new MutableLiveData<>();
    private MutableLiveData<String> razaoSocial = new MutableLiveData<>();
    private MutableLiveData<String> responsavel = new MutableLiveData<>();
    private MutableLiveData<String> telefone = new MutableLiveData<>();
    private MutableLiveData<String> cpf_cnpj = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> endereco = new MutableLiveData<>();

    public LiveData<String> getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente.setValue(tipoCliente);
    }

    public LiveData<Integer> getTipoClientePosition() {
        return tipoClientePosition;
    }

    public void setTipoClientePosition(Integer tipoClientePosition) {
        this.tipoClientePosition.setValue(tipoClientePosition);
    }

    public LiveData<String> getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente.setValue(nomeCliente);
    }

    public LiveData<String> getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial.setValue(razaoSocial);
    }

    public LiveData<String> getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel.setValue(responsavel);
    }

    public LiveData<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.setValue(telefone);
    }

    public LiveData<String> getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj.setValue(cpf_cnpj);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public LiveData<String> getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.setValue(endereco);
    }
}
