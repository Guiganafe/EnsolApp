package com.example.ensolapp.Models;

public class Cliente {
    public String tipoCliente;
    public String nomeCliente;
    public String razaoSocial;
    public String responsavel;
    public String telefone;
    public String cpf_cnpj;
    public String email;
    public String endereco;

    public Cliente(String tipoCliente, String nomeCliente, String razaoSocial, String responsavel, String telefone, String cpf_cnpj, String email, String endereco) {
        this.tipoCliente = tipoCliente;
        this.nomeCliente = nomeCliente;
        this.razaoSocial = razaoSocial;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.endereco = endereco;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
