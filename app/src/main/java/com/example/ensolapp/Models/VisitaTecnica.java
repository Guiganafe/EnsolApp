package com.example.ensolapp.Models;

import java.util.Date;

public class VisitaTecnica {
    public Date dataVisita;
    public String padraoEntrada;
    public String aperagemDisjuntosEntrada;
    public String conficaoPadraoEntrada;
    public String localInstalacaoModulos;
    public String materialEstruturaTelhado;
    public String condicaoTelhado;
    public String orientacaoTelhado;
    public String larguraTelhado;
    public String comprimentoTelhado;
    public String alturaTelhado;
    public String acessoEscada;
    public String acessoAndaime;
    public String obsFinais;

    public VisitaTecnica(Date dataVisita, String padraoEntrada, String aperagemDisjuntosEntrada, String conficaoPadraoEntrada,
                         String localInstalacaoModulos, String materialEstruturaTelhado, String condicaoTelhado,
                         String orientacaoTelhado, String larguraTelhado, String comprimentoTelhado,
                         String alturaTelhado, String acessoEscada, String acessoAndaime, String obsFinais) {
        this.dataVisita = dataVisita;
        this.padraoEntrada = padraoEntrada;
        this.aperagemDisjuntosEntrada = aperagemDisjuntosEntrada;
        this.conficaoPadraoEntrada = conficaoPadraoEntrada;
        this.localInstalacaoModulos = localInstalacaoModulos;
        this.materialEstruturaTelhado = materialEstruturaTelhado;
        this.condicaoTelhado = condicaoTelhado;
        this.orientacaoTelhado = orientacaoTelhado;
        this.larguraTelhado = larguraTelhado;
        this.comprimentoTelhado = comprimentoTelhado;
        this.alturaTelhado = alturaTelhado;
        this.acessoEscada = acessoEscada;
        this.acessoAndaime = acessoAndaime;
        this.obsFinais = obsFinais;
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

    public String getAperagemDisjuntosEntrada() {
        return aperagemDisjuntosEntrada;
    }

    public void setAperagemDisjuntosEntrada(String aperagemDisjuntosEntrada) {
        this.aperagemDisjuntosEntrada = aperagemDisjuntosEntrada;
    }

    public String getConficaoPadraoEntrada() {
        return conficaoPadraoEntrada;
    }

    public void setConficaoPadraoEntrada(String conficaoPadraoEntrada) {
        this.conficaoPadraoEntrada = conficaoPadraoEntrada;
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
}
