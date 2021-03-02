package com.example.ensolapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout edt_email, edt_senha;
    private Button btn_logar;

    /**
     * Instancia do firebase
     */
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        onClickController();
    }

    private void onClickController() {
        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });
    }

    private void logar() {
        String email, senha;

        email = edt_email.getEditText().getText().toString();
        senha = edt_senha.getEditText().getText().toString();

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)) {
            edt_email.setError("Insira um e-mail");
            edt_senha.setError("Insira uma senha");
            return;
        }else if(TextUtils.isEmpty(email)){
            edt_email.setError("Insira um e-mail");
            return;
        }else if(TextUtils.isEmpty(senha)){
            edt_senha.setError("Insira uma senha");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    final FirebaseUser userEmailAndPassword = firebaseAuth.getCurrentUser();
                    updateUI(userEmailAndPassword);
                }else{
                    Toast.makeText(LoginActivity.this, "Falha no login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(main);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseService.getFirebaseAuth();
    }

    private void inicializarComponentes() {
        edt_email = (TextInputLayout) findViewById(R.id.edt_login_email);
        edt_senha = (TextInputLayout) findViewById(R.id.edt_login_senha);
        btn_logar = (Button) findViewById(R.id.btn_login_entrar);
    }
}