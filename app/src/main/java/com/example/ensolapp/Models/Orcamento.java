package com.example.ensolapp.Models;

import android.text.TextUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Orcamento {
    private Date dataSolicitacao;
    private String nomeCliente;
    private String contato;
    private String potenciaDesejada;
    private String localizacao;
    private String fotoContaUrl;
    private String observacao;
    private String tecnicoId;

    public Orcamento() {
    }

    public Orcamento(String nomeCliente, String contato, String potenciaDesejada, String localizacao, String fotoContaUrl, String observacao) {
        this.nomeCliente = nomeCliente;
        this.contato = contato;
        this.potenciaDesejada = potenciaDesejada;
        this.localizacao = localizacao;
        this.fotoContaUrl = fotoContaUrl;
        this.observacao = observacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getPotenciaDesejada() {
        return potenciaDesejada;
    }

    public void setPotenciaDesejada(String potenciaDesejada) {
        this.potenciaDesejada = potenciaDesejada;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getFotoContaUrl() {
        return fotoContaUrl;
    }

    public void setFotoContaUrl(String fotoContaUrl) {
        this.fotoContaUrl = fotoContaUrl;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(String tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Map<String, Object> toMap(){

        Map<String, Object> orcamento = new HashMap<>();

        orcamento.put("dataSolicitacao", getDataSolicitacao());
        orcamento.put("nomeCliente", getNomeCliente());
        orcamento.put("contato", getContato());
        orcamento.put("potenciaDesejada", getPotenciaDesejada());
        orcamento.put("localizacao", getLocalizacao());
        orcamento.put("fotoContaUrl", getFotoContaUrl());
        if(!TextUtils.isEmpty(getObservacao())){
            orcamento.put("observacao", getObservacao());
        }
        orcamento.put("tecnicoId", getTecnicoId());

        return orcamento;
    }
}
