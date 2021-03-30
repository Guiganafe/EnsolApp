package com.example.ensolapp.Models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Entrega {
    private Date dataVisita;
    private Map<String, Object> cliente;
    private String obsFinais;
    private String ressalvasTelhado;
    private String tecnicoId;
    private String fotoEstruturaFixacaoTelhadoUrl;
    private String fotoModulosTelhadoUrl;
    private String fotoStringBoxAbertaUrl;
    private String fotoTensaoStringBoxUrl;
    private String fotoStringBoxInstaladaUrl;
    private String fotoMedidaTensaoConectadoUrl;
    private String fotoPontoConexaoCaUrl;
    private String fotoFinalInstalacaoKitUrl;
    private String fotoPlacaSegurancaUrl;

    public Entrega() {
    }

    public Date getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Map<String, Object> getCliente() {
        return cliente;
    }

    public void setCliente(Map<String, Object> cliente) {
        this.cliente = cliente;
    }

    public String getObsFinais() {
        return obsFinais;
    }

    public void setObsFinais(String obsFinais) {
        this.obsFinais = obsFinais;
    }

    public String getRessalvasTelhado() {
        return ressalvasTelhado;
    }

    public void setRessalvasTelhado(String ressalvasTelhado) {
        this.ressalvasTelhado = ressalvasTelhado;
    }

    public String getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(String tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public String getFotoEstruturaFixacaoTelhadoUrl() {
        return fotoEstruturaFixacaoTelhadoUrl;
    }

    public void setFotoEstruturaFixacaoTelhadoUrl(String fotoEstruturaFixacaoTelhadoUrl) {
        this.fotoEstruturaFixacaoTelhadoUrl = fotoEstruturaFixacaoTelhadoUrl;
    }

    public String getFotoModulosTelhadoUrl() {
        return fotoModulosTelhadoUrl;
    }

    public void setFotoModulosTelhadoUrl(String fotoModulosTelhadoUrl) {
        this.fotoModulosTelhadoUrl = fotoModulosTelhadoUrl;
    }

    public String getFotoStringBoxAbertaUrl() {
        return fotoStringBoxAbertaUrl;
    }

    public void setFotoStringBoxAbertaUrl(String fotoStringBoxAbertaUrl) {
        this.fotoStringBoxAbertaUrl = fotoStringBoxAbertaUrl;
    }

    public String getFotoTensaoStringBoxUrl() {
        return fotoTensaoStringBoxUrl;
    }

    public void setFotoTensaoStringBoxUrl(String fotoTensaoStringBoxUrl) {
        this.fotoTensaoStringBoxUrl = fotoTensaoStringBoxUrl;
    }

    public String getFotoStringBoxInstaladaUrl() {
        return fotoStringBoxInstaladaUrl;
    }

    public void setFotoStringBoxInstaladaUrl(String fotoStringBoxInstaladaUrl) {
        this.fotoStringBoxInstaladaUrl = fotoStringBoxInstaladaUrl;
    }

    public String getFotoMedidaTensaoConectadoUrl() {
        return fotoMedidaTensaoConectadoUrl;
    }

    public void setFotoMedidaTensaoConectadoUrl(String fotoMedidaTensaoConectadoUrl) {
        this.fotoMedidaTensaoConectadoUrl = fotoMedidaTensaoConectadoUrl;
    }

    public String getFotoPontoConexaoCaUrl() {
        return fotoPontoConexaoCaUrl;
    }

    public void setFotoPontoConexaoCaUrl(String fotoPontoConexaoCaUrl) {
        this.fotoPontoConexaoCaUrl = fotoPontoConexaoCaUrl;
    }

    public String getFotoFinalInstalacaoKitUrl() {
        return fotoFinalInstalacaoKitUrl;
    }

    public void setFotoFinalInstalacaoKitUrl(String fotoFinalInstalacaoKitUrl) {
        this.fotoFinalInstalacaoKitUrl = fotoFinalInstalacaoKitUrl;
    }

    public String getFotoPlacaSegurancaUrl() {
        return fotoPlacaSegurancaUrl;
    }

    public void setFotoPlacaSegurancaUrl(String fotoPlacaSegurancaUrl) {
        this.fotoPlacaSegurancaUrl = fotoPlacaSegurancaUrl;
    }

    public Map<String, Object> toMap(){

        Map<String, Object> vt = new HashMap<>();

        //Campos obrigatórios
        vt.put("dataVisita", getDataVisita());
        vt.put("cliente", getCliente());
        vt.put("tecnicoId", getTecnicoId());

        //Campos não obrigatórios
        if(getFotoEstruturaFixacaoTelhadoUrl() != null){
            vt.put("fotoEstruturaFixacaoTelhadoUrl", getFotoEstruturaFixacaoTelhadoUrl());
        }

        if(getFotoModulosTelhadoUrl() != null){
            vt.put("fotoModulosTelhadoUrl", getFotoModulosTelhadoUrl());
        }

        if(getFotoStringBoxAbertaUrl() != null){
            vt.put("fotoStringBoxAberta", getFotoStringBoxAbertaUrl());
        }

        if(getFotoTensaoStringBoxUrl() != null){
            vt.put("fotoTensaoStringBoxUrl", getFotoTensaoStringBoxUrl());
        }

        if(getFotoStringBoxInstaladaUrl() != null){
            vt.put("fotoStringBoxInstaladaUrl", getFotoStringBoxInstaladaUrl());
        }

        if(getFotoMedidaTensaoConectadoUrl() != null){
            vt.put("fotoMedidaTensaoConectadoUrl", getFotoMedidaTensaoConectadoUrl());
        }

        if(getFotoPontoConexaoCaUrl() != null){
            vt.put("fotoPontoConexaoCaUrl", getFotoPontoConexaoCaUrl());
        }

        if(getFotoFinalInstalacaoKitUrl() != null){
            vt.put("fotoFinalInstalacaoKitUrl", getFotoFinalInstalacaoKitUrl());
        }

        if(getFotoPlacaSegurancaUrl() != null){
            vt.put("fotoPlacaSegurancaUrl", getFotoPlacaSegurancaUrl());
        }

        if(getRessalvasTelhado() != null){
            vt.put("ressalvasTelhado", getRessalvasTelhado());
        }

        if(getObsFinais() != null){
            vt.put("obsFinais", getObsFinais());
        }

        return vt;
    }
}
