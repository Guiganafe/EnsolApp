package com.example.ensolapp.Models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisitaTecnica {
    private Date dataVisita;
    private Map<String, Object> cliente;
    private String padraoEntrada;
    private String amperagemDisjuntosEntrada;
    private String condicaoPadraoEntrada;
    private String localInstalacaoModulos;
    private String materialEstruturaTelhado;
    private String condicaoTelhado;
    private String orientacaoTelhado;
    private String larguraTelhado;
    private String comprimentoTelhado;
    private String alturaTelhado;
    private String acessoEscada;
    private String acessoAndaime;
    private String obsFinais;
    private String tecnicoId;

    public VisitaTecnica() {
    }

    public Date getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita = dataVisita;
    }

    public String getPadraoEntrada() {
        return padraoEntrada;
    }

    public void setPadraoEntrada(String padraoEntrada) {
        this.padraoEntrada = padraoEntrada;
    }

    public String getAmperagemDisjuntosEntrada() {
        return amperagemDisjuntosEntrada;
    }

    public void setAmperagemDisjuntosEntrada(String aperagemDisjuntosEntrada) {
        this.amperagemDisjuntosEntrada = aperagemDisjuntosEntrada;
    }

    public String getCondicaoPadraoEntrada() {
        return condicaoPadraoEntrada;
    }

    public void setCondicaoPadraoEntrada(String condicaoPadraoEntrada) {
        this.condicaoPadraoEntrada = condicaoPadraoEntrada;
    }

    public String getLocalInstalacaoModulos() {
        return localInstalacaoModulos;
    }

    public void setLocalInstalacaoModulos(String localInstalacaoModulos) {
        this.localInstalacaoModulos = localInstalacaoModulos;
    }

    public String getMaterialEstruturaTelhado() {
        return materialEstruturaTelhado;
    }

    public void setMaterialEstruturaTelhado(String materialEstruturaTelhado) {
        this.materialEstruturaTelhado = materialEstruturaTelhado;
    }

    public String getCondicaoTelhado() {
        return condicaoTelhado;
    }

    public void setCondicaoTelhado(String condicaoTelhado) {
        this.condicaoTelhado = condicaoTelhado;
    }

    public String getOrientacaoTelhado() {
        return orientacaoTelhado;
    }

    public void setOrientacaoTelhado(String orientacaoTelhado) {
        this.orientacaoTelhado = orientacaoTelhado;
    }

    public String getLarguraTelhado() {
        return larguraTelhado;
    }

    public void setLarguraTelhado(String larguraTelhado) {
        this.larguraTelhado = larguraTelhado;
    }

    public String getComprimentoTelhado() {
        return comprimentoTelhado;
    }

    public void setComprimentoTelhado(String comprimentoTelhado) {
        this.comprimentoTelhado = comprimentoTelhado;
    }

    public String getAlturaTelhado() {
        return alturaTelhado;
    }

    public void setAlturaTelhado(String alturaTelhado) {
        this.alturaTelhado = alturaTelhado;
    }

    public String getAcessoEscada() {
        return acessoEscada;
    }

    public void setAcessoEscada(String acessoEscada) {
        this.acessoEscada = acessoEscada;
    }

    public String getAcessoAndaime() {
        return acessoAndaime;
    }

    public void setAcessoAndaime(String acessoAndaime) {
        this.acessoAndaime = acessoAndaime;
    }

    public String getObsFinais() {
        return obsFinais;
    }

    public void setObsFinais(String obsFinais) {
        this.obsFinais = obsFinais;
    }

    public Map<String, Object> getCliente() {
        return cliente;
    }

    public void setCliente(Map<String, Object> cliente) {
        this.cliente = cliente;
    }

    public String getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(String tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Map<String, Object> toMap(){

        Map<String, Object> vt = new HashMap<>();

        //Campos obrigatórios
        vt.put("dataVisita", getDataVisita());
        vt.put("cliente", getCliente());
        vt.put("padraoEntrada", getPadraoEntrada());
        vt.put("localInstalacaoModulos", getLocalInstalacaoModulos());
        vt.put("materialEstruturaTelhado", getMaterialEstruturaTelhado());
        vt.put("orientacaoTelhado", getOrientacaoTelhado());
        vt.put("acessoEscada", getAcessoEscada());
        vt.put("acessoAndaime", getAcessoAndaime());
        vt.put("tecnicoId", getTecnicoId());

        //Campos não obrigatórios
        if(!getAmperagemDisjuntosEntrada().isEmpty()){
            vt.put("aperagemDisjuntosEntrada", getAmperagemDisjuntosEntrada());
        }

        if(!getCondicaoPadraoEntrada().isEmpty()){
            vt.put("condicaoPadraoEntrada", getCondicaoPadraoEntrada());
        }

        if(!getCondicaoTelhado().isEmpty()){
            vt.put("condicaoTelhado", getCondicaoTelhado());
        }

        if(!getLarguraTelhado().isEmpty()){
            vt.put("larguraTelhado", getLarguraTelhado());
        }

        if(!getComprimentoTelhado().isEmpty()){
            vt.put("comprimentoTelhado", getComprimentoTelhado());
        }

        if(!getAlturaTelhado().isEmpty()){
            vt.put("alturaTelhado", getAlturaTelhado());
        }

        if(!getObsFinais().isEmpty()){
            vt.put("obsFinais", getObsFinais());
        }

        return vt;
    }
}
