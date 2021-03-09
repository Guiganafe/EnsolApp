package com.example.ensolapp.Models;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private String tipoCliente;
    private String nomeCliente;
    private String razaoSocial;
    private String responsavel;
    private String telefone;
    private String cpf_cnpj;
    private String email;
    private String endereco;

    public Cliente() {
    }

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

    public Map<String, Object> toMap(){

        Map<String, Object> cliente = new HashMap<>();
        //Campos obrigatórios
        cliente.put("nomeCliente", getNomeCliente());
        cliente.put("telefone", getTelefone());
        cliente.put("endereco", getEndereco());

        //Campos não obrigatórios
        if(!getTipoCliente().isEmpty()){
            cliente.put("tipoCliente", getTipoCliente());
        }

        if(!getRazaoSocial().isEmpty()){
            cliente.put("razaoSocial", getRazaoSocial());
        }

        if(!getResponsavel().isEmpty()){
            cliente.put("responsavel", getResponsavel());
        }

        if(!getCpf_cnpj().isEmpty()){
            cliente.put("cpf_cnpj", getCpf_cnpj());
        }

        if(!getEmail().isEmpty()){
            cliente.put("email", getEmail());
        }

        return cliente;
    }
}
