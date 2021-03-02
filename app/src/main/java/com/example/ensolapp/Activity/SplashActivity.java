package com.example.ensolapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseService.getFirebaseAuth();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(firebaseUser != null){

        }

        Handler hander = new Handler();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 3000);
    }

    private void mostrarMainActivity() {
        if(firebaseUser != null){
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }else{
            Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }
}