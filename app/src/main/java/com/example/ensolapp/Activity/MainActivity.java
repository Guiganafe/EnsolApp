package com.example.ensolapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ensolapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_novo_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        onClickController();
    }

    private void onClickController() {
        fab_novo_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoForm();
            }
        });
    }

    private void novoForm() {
        Intent novo_form = new Intent(this, VisitaTecnicaActivity.class);
        startActivity(novo_form);
    }

    private void inicializarComponentes() {
        fab_novo_form = (FloatingActionButton) findViewById(R.id.fab_add_form);
    }
}