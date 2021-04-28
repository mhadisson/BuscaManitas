package com.hardisson.buscamanitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button registrar, acceder;
    private FirebaseAuth mAuth;
    AlertDialog.Builder val;
    private Object AlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        registrar = (Button) findViewById(R.id.signUpButton);
        registrar.setOnClickListener(this);
        acceder = (Button) findViewById(R.id.loginButton);
        acceder.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View v) {
        String correo = email.getText().toString();
        String contraseña = password.getText().toString();
        switch (v.getId()) {
            case R.id.signUpButton:
                if(validar()){
                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent registroIntent = new Intent(AuthActivity.this,RegistroActivity.class);
                                        registroIntent.putExtra("correo", correo);
                                        startActivity(registroIntent);
                                    }else{
                                        showAlert();
                                    }
                                }
                            });
                }
                break;
            case R.id.loginButton:
                if (validar()){
                    mAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent myIntent = new Intent(AuthActivity.this,MainActivity.class);
                                        startActivity(myIntent);
                                    }else{
                                        showAlert();
                                    }
                                }
                            });
                }
                break;
            default:
                break;

        }

    }


    public boolean validar(){
        boolean retorno=true;
        String em = email.getText().toString();
        String pas = password.getText().toString();
        if (em.isEmpty()){
            email.setError("El email no puede estar vacío");
            retorno = false;
        }
        if (pas.isEmpty()){
            password.setError("La contraseña no puede estar vacía");
            retorno = false;
        }
        return retorno;
    }

    public void showAlert(){
        AlertDialog.Builder val =  new AlertDialog.Builder(AuthActivity.this);

        ((AlertDialog.Builder) val).setMessage("Se ha producido un error de autenticación del usuario");
        ((AlertDialog.Builder) val).setPositiveButton("Aceptar", null);

        AlertDialog error = val.create();
        error.setTitle("Error");
        error.show();
    }
}