package com.example.ensolapp.Models;

import android.graphics.Bitmap;

public class Fotos {
    private Bitmap foto_padrao, foto_acesso_telhado, foto_orientacao_telhado;

    public Fotos() {
    }

    public Fotos(Bitmap foto_padrao, Bitmap foto_acesso_telhado, Bitmap foto_orientacao_telhado) {
        this.foto_padrao = foto_padrao;
        this.foto_acesso_telhado = foto_acesso_telhado;
        this.foto_orientacao_telhado = foto_orientacao_telhado;
    }

    public Bitmap getFoto_padrao() {
        return foto_padrao;
    }

    public void setFoto_padrao(Bitmap foto_padrao) {
        this.foto_padrao = foto_padrao;
    }

    public Bitmap getFoto_acesso_telhado() {
        return foto_acesso_telhado;
    }

    public void setFoto_acesso_telhado(Bitmap foto_acesso_telhado) {
        this.foto_acesso_telhado = foto_acesso_telhado;
    }

    public Bitmap getFoto_orientacao_telhado() {
        return foto_orientacao_telhado;
    }

    public void setFoto_orientacao_telhado(Bitmap foto_orientacao_telhado) {
        this.foto_orientacao_telhado = foto_orientacao_telhado;
    }
}
