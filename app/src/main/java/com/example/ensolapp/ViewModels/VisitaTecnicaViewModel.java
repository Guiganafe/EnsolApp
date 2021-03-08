package com.example.ensolapp.ViewModels;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class VisitaTecnicaViewModel extends ViewModel {

    private MutableLiveData<String> dataVisita = new MutableLiveData<>();
    //Dados sobre o ambiente
    private MutableLiveData<String> padraoEntrada = new MutableLiveData<>();
    private MutableLiveData<Integer> padraoEntradaPosition = new MutableLiveData<>();
    private MutableLiveData<String> aperagemDisjuntosEntrada = new MutableLiveData<>();
    private MutableLiveData<String> condicaoPadraoEntrada = new MutableLiveData<>();
    private MutableLiveData<Integer> condicaoPadraoEntradaPositon = new MutableLiveData<>();
    private MutableLiveData<String> localInstalacaoModulos = new MutableLiveData<>();
    private MutableLiveData<Integer> localInstalacaoModulosPosition = new MutableLiveData<>();
    private MutableLiveData<String> materialEstruturaTelhado = new MutableLiveData<>();
    private MutableLiveData<Integer> materialEstruturaTelhadoPosition = new MutableLiveData<>();
    private MutableLiveData<String> condicaoTelhado = new MutableLiveData<>();
    private MutableLiveData<Integer> condicaoTelhadoPosition = new MutableLiveData<>();
    private MutableLiveData<String> orientacaoTelhado = new MutableLiveData<>();
    private MutableLiveData<Integer> orientacaoTelhadoPosition = new MutableLiveData<>();
    private MutableLiveData<String> larguraTelhado = new MutableLiveData<>();
    private MutableLiveData<String> comprimentoTelhado = new MutableLiveData<>();
    private MutableLiveData<String> alturaTelhado = new MutableLiveData<>();
    private MutableLiveData<String> acessoEscada = new MutableLiveData<>();
    private MutableLiveData<String> acessoAndaime = new MutableLiveData<>();
    private MutableLiveData<String> obsFinais = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoPadraoEntrada = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoOrientacaoTelhado = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoAcessoTelhado = new MutableLiveData<>();

    public LiveData<String> getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita.setValue(dataVisita);
    }

    public LiveData<String> getPadraoEntrada() {
        return padraoEntrada;
    }

    public void setPadraoEntrada(String padraoEntrada) {
        this.padraoEntrada.setValue(padraoEntrada);
    }

    public LiveData<String> getAperagemDisjuntosEntrada() {
        return aperagemDisjuntosEntrada;
    }

    public void setAperagemDisjuntosEntrada(String aperagemDisjuntosEntrada) {
        this.aperagemDisjuntosEntrada.setValue(aperagemDisjuntosEntrada);
    }

    public LiveData<String> getCondicaoPadraoEntrada() {
        return condicaoPadraoEntrada;
    }

    public void setCondicaoPadraoEntrada(String conficaoPadraoEntrada) {
        this.condicaoPadraoEntrada.setValue(conficaoPadraoEntrada);
    }

    public LiveData<String> getLocalInstalacaoModulos() {
        return localInstalacaoModulos;
    }

    public void setLocalInstalacaoModulos(String localInstalacaoModulos) {
        this.localInstalacaoModulos.setValue(localInstalacaoModulos);
    }

    public LiveData<String> getMaterialEstruturaTelhado() {
        return materialEstruturaTelhado;
    }

    public void setMaterialEstruturaTelhado(String materialEstruturaTelhado) {
        this.materialEstruturaTelhado.setValue(materialEstruturaTelhado);
    }

    public LiveData<String> getCondicaoTelhado() {
        return condicaoTelhado;
    }

    public void setCondicaoTelhado(String condicaoTelhado) {
        this.condicaoTelhado.setValue(condicaoTelhado);
    }

    public LiveData<String> getOrientacaoTelhado() {
        return orientacaoTelhado;
    }

    public void setOrientacaoTelhado(String orientacaoTelhado) {
        this.orientacaoTelhado.setValue(orientacaoTelhado);
    }

    public LiveData<String> getLarguraTelhado() {
        return larguraTelhado;
    }

    public void setLarguraTelhado(String larguraTelhado) {
        this.larguraTelhado.setValue(larguraTelhado);
    }

    public LiveData<String> getComprimentoTelhado() {
        return comprimentoTelhado;
    }

    public void setComprimentoTelhado(String comprimentoTelhado) {
        this.comprimentoTelhado.setValue(comprimentoTelhado);
    }

    public LiveData<String> getAlturaTelhado() {
        return alturaTelhado;
    }

    public void setAlturaTelhado(String alturaTelhado) {
        this.alturaTelhado.setValue(alturaTelhado);
    }

    public LiveData<String> getAcessoEscada() {
        return acessoEscada;
    }

    public void setAcessoEscada(String acessoEscada) {
        this.acessoEscada.setValue(acessoEscada);
    }

    public LiveData<String> getAcessoAndaime() {
        return acessoAndaime;
    }

    public void setAcessoAndaime(String acessoAndaime) {
        this.acessoAndaime.setValue(acessoAndaime);
    }

    public LiveData<String> getObsFinais() {
        return obsFinais;
    }

    public void setObsFinais(String obsFinais) {
        this.obsFinais.setValue(obsFinais);
    }

    public LiveData<Integer> getPadraoEntradaPosition() {
        return padraoEntradaPosition;
    }

    public void setPadraoEntradaPosition(Integer padraoEntradaPosition) {
        this.padraoEntradaPosition.setValue(padraoEntradaPosition);
    }

    public LiveData<Integer> getCondicaoPadraoEntradaPositon() {
        return condicaoPadraoEntradaPositon;
    }

    public void setCondicaoPadraoEntradaPositon(Integer conficaoPadraoEntradaPositon) {
        this.condicaoPadraoEntradaPositon.setValue(conficaoPadraoEntradaPositon);
    }

    public LiveData<Integer> getLocalInstalacaoModulosPosition() {
        return localInstalacaoModulosPosition;
    }

    public void setLocalInstalacaoModulosPosition(Integer localInstalacaoModulosPosition) {
        this.localInstalacaoModulosPosition.setValue(localInstalacaoModulosPosition);
    }

    public LiveData<Integer> getMaterialEstruturaTelhadoPosition() {
        return materialEstruturaTelhadoPosition;
    }

    public void setMaterialEstruturaTelhadoPosition(Integer materialEstruturaTelhadoPosition) {
        this.materialEstruturaTelhadoPosition.setValue(materialEstruturaTelhadoPosition);
    }

    public LiveData<Integer> getCondicaoTelhadoPosition() {
        return condicaoTelhadoPosition;
    }

    public void setCondicaoTelhadoPosition(Integer condicaoTelhadoPosition) {
        this.condicaoTelhadoPosition.setValue(condicaoTelhadoPosition);
    }

    public LiveData<Integer> getOrientacaoTelhadoPosition() {
        return orientacaoTelhadoPosition;
    }

    public void setOrientacaoTelhadoPosition(Integer orientacaoTelhadoPosition) {
        this.orientacaoTelhadoPosition.setValue(orientacaoTelhadoPosition);
    }

    public LiveData<Bitmap> getFotoPadraoEntrada() {
        return fotoPadraoEntrada;
    }

    public void setFotoPadraoEntrada(Bitmap fotoPadraoEntrada) {
        this.fotoPadraoEntrada.setValue(fotoPadraoEntrada);
    }

    public LiveData<Bitmap> getFotoOrientacaoTelhado() {
        return fotoOrientacaoTelhado;
    }

    public void setFotoOrientacaoTelhado(Bitmap fotoOrientacaoTelhado) {
        this.fotoOrientacaoTelhado.setValue(fotoOrientacaoTelhado);
    }

    public LiveData<Bitmap> getFotoAcessoTelhado() {
        return fotoAcessoTelhado;
    }

    public void setFotoAcessoTelhado(Bitmap fotoAcessoTelhado) {
        this.fotoAcessoTelhado.setValue(fotoAcessoTelhado);
    }
}
