package com.example.ensolapp.ViewModels;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntregaViewModel extends ViewModel {
    private MutableLiveData<String> dataVisita = new MutableLiveData<>();

    private MutableLiveData<String> obsFinais = new MutableLiveData<>();
    private MutableLiveData<String> ressalvasTelhado = new MutableLiveData<>();

    private MutableLiveData<Bitmap> fotoEstruturaFixacaoTelhado = new MutableLiveData<>();
    private MutableLiveData<String> fotoEstruturaFixacaoTelhadoUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoModulosTelhado = new MutableLiveData<>();
    private MutableLiveData<String> fotoModulosTelhadoUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoStringBoxAberta = new MutableLiveData<>();
    private MutableLiveData<String> fotoStringBoxAbertaUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoTensaoStringBox = new MutableLiveData<>();
    private MutableLiveData<String> fotoTensaoStringBoxUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoStringBoxInstalada = new MutableLiveData<>();
    private MutableLiveData<String> fotoStringBoxInstaladaUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoMedidaTensaoConectado = new MutableLiveData<>();
    private MutableLiveData<String> fotoMedidaTensaoConectadoUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoPontoConexaoCa = new MutableLiveData<>();
    private MutableLiveData<String> fotoPontoConexaoCaUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoFinalInstalacaoKit = new MutableLiveData<>();
    private MutableLiveData<String> fotoFinalInstalacaoKitUrl = new MutableLiveData<>();
    private MutableLiveData<Bitmap> fotoPlacaSeguranca = new MutableLiveData<>();
    private MutableLiveData<String> fotoPlacaSegurancaUrl = new MutableLiveData<>();

    public LiveData<String> getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita.setValue(dataVisita);
    }

    public LiveData<String> getObsFinais() {
        return obsFinais;
    }

    public void setObsFinais(String obsFinais) {
        this.obsFinais.setValue(obsFinais);
    }

    public LiveData<String> getRessalvasTelhado() {
        return ressalvasTelhado;
    }

    public void setRessalvasTelhado(String ressalvasTelhado) {
        this.ressalvasTelhado.setValue(ressalvasTelhado);
    }

    public LiveData<Bitmap> getFotoEstruturaFixacaoTelhado() {
        return fotoEstruturaFixacaoTelhado;
    }

    public void setFotoEstruturaFixacaoTelhado(Bitmap fotoEstruturaFixacaoTelhado) {
        this.fotoEstruturaFixacaoTelhado.setValue(fotoEstruturaFixacaoTelhado);
    }

    public LiveData<String> getFotoEstruturaFixacaoTelhadoUrl() {
        return fotoEstruturaFixacaoTelhadoUrl;
    }

    public void setFotoEstruturaFixacaoTelhadoUrl(String fotoEstruturaFixacaoTelhadoUrl) {
        this.fotoEstruturaFixacaoTelhadoUrl.setValue(fotoEstruturaFixacaoTelhadoUrl);
    }

    public LiveData<Bitmap> getFotoModulosTelhado() {
        return fotoModulosTelhado;
    }

    public void setFotoModulosTelhado(Bitmap fotoModulosTelhado) {
        this.fotoModulosTelhado.setValue(fotoModulosTelhado);
    }

    public LiveData<String> getFotoModulosTelhadoUrl() {
        return fotoModulosTelhadoUrl;
    }

    public void setFotoModulosTelhadoUrl(String fotoModulosTelhadoUrl) {
        this.fotoModulosTelhadoUrl.setValue(fotoModulosTelhadoUrl);
    }

    public LiveData<Bitmap> getFotoStringBoxAberta() {
        return fotoStringBoxAberta;
    }

    public void setFotoStringBoxAberta(Bitmap fotoStringBoxAberta) {
        this.fotoStringBoxAberta.setValue(fotoStringBoxAberta);
    }

    public LiveData<String> getFotoStringBoxAbertaUrl() {
        return fotoStringBoxAbertaUrl;
    }

    public void setFotoStringBoxAbertaUrl(String fotoStringBoxAbertaUrl) {
        this.fotoStringBoxAbertaUrl.setValue(fotoStringBoxAbertaUrl);
    }

    public LiveData<Bitmap> getFotoTensaoStringBox() {
        return fotoTensaoStringBox;
    }

    public void setFotoTensaoStringBox(Bitmap fotoTensaoStringBox) {
        this.fotoTensaoStringBox.setValue(fotoTensaoStringBox);
    }

    public LiveData<String> getFotoTensaoStringBoxUrl() {
        return fotoTensaoStringBoxUrl;
    }

    public void setFotoTensaoStringBoxUrl(String fotoTensaoStringBoxUrl) {
        this.fotoTensaoStringBoxUrl.setValue(fotoTensaoStringBoxUrl);
    }

    public LiveData<Bitmap> getFotoStringBoxInstalada() {
        return fotoStringBoxInstalada;
    }

    public void setFotoStringBoxInstalada(Bitmap fotoStringBoxInstalada) {
        this.fotoStringBoxInstalada.setValue(fotoStringBoxInstalada);
    }

    public LiveData<String> getFotoStringBoxInstaladaUrl() {
        return fotoStringBoxInstaladaUrl;
    }

    public void setFotoStringBoxInstaladaUrl(String fotoStringBoxInstaladaUrl) {
        this.fotoStringBoxInstaladaUrl.setValue(fotoStringBoxInstaladaUrl);
    }

    public LiveData<Bitmap> getFotoMedidaTensaoConectado() {
        return fotoMedidaTensaoConectado;
    }

    public void setFotoMedidaTensaoConectado(Bitmap fotoMedidaTensaoConectado) {
        this.fotoMedidaTensaoConectado.setValue(fotoMedidaTensaoConectado);
    }

    public LiveData<String> getFotoMedidaTensaoConectadoUrl() {
        return fotoMedidaTensaoConectadoUrl;
    }

    public void setFotoMedidaTensaoConectadoUrl(String fotoMedidaTensaoConectadoUrl) {
        this.fotoMedidaTensaoConectadoUrl.setValue(fotoMedidaTensaoConectadoUrl);
    }

    public LiveData<Bitmap> getFotoPontoConexaoCa() {
        return fotoPontoConexaoCa;
    }

    public void setFotoPontoConexaoCa(Bitmap fotoPontoConexaoCa) {
        this.fotoPontoConexaoCa.setValue(fotoPontoConexaoCa);
    }

    public LiveData<String> getFotoPontoConexaoCaUrl() {
        return fotoPontoConexaoCaUrl;
    }

    public void setFotoPontoConexaoCaUrl(String fotoPontoConexaoCaUrl) {
        this.fotoPontoConexaoCaUrl.setValue(fotoPontoConexaoCaUrl);
    }

    public LiveData<Bitmap> getFotoFinalInstalacaoKit() {
        return fotoFinalInstalacaoKit;
    }

    public void setFotoFinalInstalacaoKit(Bitmap fotoFinalInstalacaoKit) {
        this.fotoFinalInstalacaoKit.setValue(fotoFinalInstalacaoKit);
    }

    public LiveData<String> getFotoFinalInstalacaoKitUrl() {
        return fotoFinalInstalacaoKitUrl;
    }

    public void setFotoFinalInstalacaoKitUrl(String fotoFinalInstalacaoKitUrl) {
        this.fotoFinalInstalacaoKitUrl.setValue(fotoFinalInstalacaoKitUrl);
    }

    public LiveData<Bitmap> getFotoPlacaSeguranca() {
        return fotoPlacaSeguranca;
    }

    public void setFotoPlacaSeguranca(Bitmap fotoPlacaSeguranca) {
        this.fotoPlacaSeguranca.setValue(fotoPlacaSeguranca);
    }

    public LiveData<String> getFotoPlacaSegurancaUrl() {
        return fotoPlacaSegurancaUrl;
    }

    public void setFotoPlacaSegurancaUrl(String fotoPlacaSegurancaUrl) {
        this.fotoPlacaSegurancaUrl.setValue(fotoPlacaSegurancaUrl);
    }
}
