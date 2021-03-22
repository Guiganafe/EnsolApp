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
    private String tipoRamal;
    private String numeroUc;
    private String localInstalacaoModulos;
    private String materialEstruturaTelhado;
    private String condicaoTelhado;
    private String orientacaoTelhado;
    private String larguraTelhado;
    private String comprimentoTelhado;
    private String alturaTelhado;
    private String acessoTelhado;
    private String obsFinais;
    private String tecnicoId;
    private String fotoPadrao;
    private String fotoOrientacaoTelhado;
    private String fotoAcessoTelhado;
    private String fotoLocalInstalacaoInversor;

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

    public String getFotoPadrao() {
        return fotoPadrao;
    }

    public void setFotoPadrao(String foto_padrao) {
        this.fotoPadrao = foto_padrao;
    }

    public String getFotoOrientacaoTelhado() {
        return fotoOrientacaoTelhado;
    }

    public void setFotoOrientacaoTelhado(String foto_orientacao_telhado) {
        this.fotoOrientacaoTelhado = foto_orientacao_telhado;
    }

    public String getFotoAcessoTelhado() {
        return fotoAcessoTelhado;
    }

    public void setFotoAcessoTelhado(String foto_acesso_telhado) {
        this.fotoAcessoTelhado = foto_acesso_telhado;
    }

    public String getTipoRamal() {
        return tipoRamal;
    }

    public void setTipoRamal(String tipoRamal) {
        this.tipoRamal = tipoRamal;
    }

    public String getNumeroUc() {
        return numeroUc;
    }

    public void setNumeroUc(String numeroUc) {
        this.numeroUc = numeroUc;
    }

    public String getAcessoTelhado() {
        return acessoTelhado;
    }

    public void setAcessoTelhado(String acessoTelhado) {
        this.acessoTelhado = acessoTelhado;
    }

    public String getFotoLocalInstalacaoInversor() {
        return fotoLocalInstalacaoInversor;
    }

    public void setFotoLocalInstalacaoInversor(String fotoLocalInstalacaoInversor) {
        this.fotoLocalInstalacaoInversor = fotoLocalInstalacaoInversor;
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
        vt.put("acessoTelhado", getAcessoTelhado());
        vt.put("tecnicoId", getTecnicoId());

        //Campos não obrigatórios
        if(getAmperagemDisjuntosEntrada() != null){
            vt.put("aperagemDisjuntosEntrada", getAmperagemDisjuntosEntrada());
        }

        if(getTipoRamal() != null){
            vt.put("tipoRamal", getAmperagemDisjuntosEntrada());
        }

        if(getNumeroUc() != null){
            vt.put("numeroUc", getAmperagemDisjuntosEntrada());
        }

        if(getCondicaoPadraoEntrada() != null){
            vt.put("condicaoPadraoEntrada", getCondicaoPadraoEntrada());
        }

        if(getCondicaoTelhado() != null){
            vt.put("condicaoTelhado", getCondicaoTelhado());
        }

        if(getLarguraTelhado() != null){
            vt.put("larguraTelhado", getLarguraTelhado());
        }

        if(getComprimentoTelhado() != null){
            vt.put("comprimentoTelhado", getComprimentoTelhado());
        }

        if(getAlturaTelhado() != null){
            vt.put("acessoTelhado", getAlturaTelhado());
        }

        if(getObsFinais() != null){
            vt.put("obsFinais", getObsFinais());
        }

        if(getFotoPadrao() != null){
            vt.put("fotoPadrao", getFotoPadrao());
        }

        if(getFotoOrientacaoTelhado() != null){
            vt.put("fotoOrientacaoTelhado", getFotoOrientacaoTelhado());
        }

        if(getFotoAcessoTelhado() != null){
            vt.put("fotoAcessoTelhado", getFotoAcessoTelhado());
        }

        if(getFotoLocalInstalacaoInversor() != null){
            vt.put("fotoLocalInstalacaoInversor", getAlturaTelhado());
        }

        return vt;
    }
}
