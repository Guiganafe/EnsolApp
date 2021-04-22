package com.example.ensolapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ensolapp.Base.EntregaBase;
import com.example.ensolapp.Base.OrcamentoBase;
import com.example.ensolapp.Base.VisitaTecnicaBase;
import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VisitaTecnicaBase.getInstance();
        EntregaBase.getInstance();
        OrcamentoBase.getInstance();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}