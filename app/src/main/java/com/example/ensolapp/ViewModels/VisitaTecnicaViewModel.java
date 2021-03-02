package com.example.ensolapp.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class VisitaTecnicaViewModel extends ViewModel {

    private MutableLiveData<Date> dataVisita = new MutableLiveData<>();
    //Dados sobre o ambiente
    private MutableLiveData<String> padraoEntrada = new MutableLiveData<>();
    private MutableLiveData<String> aperagemDisjuntosEntrada = new MutableLiveData<>();
    private MutableLiveData<String> conficaoPadraoEntrada = new MutableLiveData<>();
    private MutableLiveData<String> localInstalacaoModulos = new MutableLiveData<>();
    private MutableLiveData<String> materialEstruturaTelhado = new MutableLiveData<>();
    private MutableLiveData<String> condicaoTelhado = new MutableLiveData<>();
    private MutableLiveData<String> orientacaoTelhado = new MutableLiveData<>();
    private MutableLiveData<String> larguraTelhado = new MutableLiveData<>();
    private MutableLiveData<String> comprimentoTelhado = new MutableLiveData<>();
    private MutableLiveData<String> alturaTelhado = new MutableLiveData<>();
    private MutableLiveData<String> acessoEscada = new MutableLiveData<>();
    private MutableLiveData<String> acessoAndaime = new MutableLiveData<>();
    private MutableLiveData<String> obsFinais = new MutableLiveData<>();

    public VisitaTecnicaViewModel(MutableLiveData<Date> dataVisita, MutableLiveData<String> padraoEntrada,
                                  MutableLiveData<String> aperagemDisjuntosEntrada, MutableLiveData<String> conficaoPadraoEntrada,
                                  MutableLiveData<String> localInstalacaoModulos, MutableLiveData<String> materialEstruturaTelhado,
                                  MutableLiveData<String> condicaoTelhado, MutableLiveData<String> orientacaoTelhado,
                                  MutableLiveData<String> larguraTelhado, MutableLiveData<String> comprimentoTelhado,
                                  MutableLiveData<String> alturaTelhado, MutableLiveData<String> acessoEscada,
                                  MutableLiveData<String> acessoAndaime, MutableLiveData<String> obsFinais) {
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

    public MutableLiveData<Date> getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita.setValue(dataVisita);
    }

    public MutableLiveData<String> getPadraoEntrada() {
        return padraoEntrada;
    }

    public void setPadraoEntrada(String padraoEntrada) {
        this.padraoEntrada.setValue(padraoEntrada);
    }

    public MutableLiveData<String> getAperagemDisjuntosEntrada() {
        return aperagemDisjuntosEntrada;
    }

    public void setAperagemDisjuntosEntrada(String aperagemDisjuntosEntrada) {
        this.aperagemDisjuntosEntrada.setValue(aperagemDisjuntosEntrada);
    }

    public MutableLiveData<String> getConficaoPadraoEntrada() {
        return conficaoPadraoEntrada;
    }

    public void setConficaoPadraoEntrada(String conficaoPadraoEntrada) {
        this.conficaoPadraoEntrada.setValue(conficaoPadraoEntrada);
    }

    public MutableLiveData<String> getLocalInstalacaoModulos() {
        return localInstalacaoModulos;
    }

    public void setLocalInstalacaoModulos(String localInstalacaoModulos) {
        this.localInstalacaoModulos.setValue(localInstalacaoModulos);
    }

    public MutableLiveData<String> getMaterialEstruturaTelhado() {
        return materialEstruturaTelhado;
    }

    public void setMaterialEstruturaTelhado(String materialEstruturaTelhado) {
        this.materialEstruturaTelhado.setValue(materialEstruturaTelhado);
    }

    public MutableLiveData<String> getCondicaoTelhado() {
        return condicaoTelhado;
    }

    public void setCondicaoTelhado(String condicaoTelhado) {
        this.condicaoTelhado.setValue(condicaoTelhado);
    }

    public MutableLiveData<String> getOrientacaoTelhado() {
        return orientacaoTelhado;
    }

    public void setOrientacaoTelhado(String orientacaoTelhado) {
        this.orientacaoTelhado.setValue(orientacaoTelhado);
    }

    public MutableLiveData<String> getLarguraTelhado() {
        return larguraTelhado;
    }

    public void setLarguraTelhado(String larguraTelhado) {
        this.larguraTelhado.setValue(larguraTelhado);
    }

    public MutableLiveData<String> getComprimentoTelhado() {
        return comprimentoTelhado;
    }

    public void setComprimentoTelhado(String comprimentoTelhado) {
        this.comprimentoTelhado.setValue(comprimentoTelhado);
    }

    public MutableLiveData<String> getAlturaTelhado() {
        return alturaTelhado;
    }

    public void setAlturaTelhado(String alturaTelhado) {
        this.alturaTelhado.setValue(alturaTelhado);
    }

    public MutableLiveData<String> getAcessoEscada() {
        return acessoEscada;
    }

    public void setAcessoEscada(String acessoEscada) {
        this.acessoEscada.setValue(acessoEscada);
    }

    public MutableLiveData<String> getAcessoAndaime() {
        return acessoAndaime;
    }

    public void setAcessoAndaime(String acessoAndaime) {
        this.acessoAndaime.setValue(acessoAndaime);
    }

    public MutableLiveData<String> getObsFinais() {
        return obsFinais;
    }

    public void setObsFinais(String obsFinais) {
        this.obsFinais.setValue(obsFinais);
    }
}
