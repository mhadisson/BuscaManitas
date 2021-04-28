package com.hardisson.buscamanitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, apellidos, telefono, codigoPostal, direccion;
    Button guardar, salir;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //String email = getIntent().getStringExtra("correo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        String email = getIntent().getStringExtra("correo");
        TextView correoUser = this.findViewById(R.id.textViewCorreo);
        correoUser.setText(email);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        apellidos = (EditText) findViewById(R.id.editTextApellidos);
        telefono = (EditText) findViewById(R.id.editTextPhone);
        codigoPostal = (EditText) findViewById(R.id.editTextCodigoPostal);
        direccion = (EditText) findViewById(R.id.editTextDireccion);

        guardar = (Button) findViewById(R.id.guardarButton);
        guardar.setOnClickListener(this);

        salir = (Button) findViewById(R.id.salirButton);
        salir.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String email = getIntent().getStringExtra("correo");
        String Nombre = nombre.getText().toString();
        String Apellidos = apellidos.getText().toString();
        String Telefono = telefono.getText().toString();
        String CodPostal = codigoPostal.getText().toString();
        String Direccion = direccion.getText().toString();

        switch (v.getId()) {
            case R.id.guardarButton:
                if (validar()) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Nombre", Nombre);
                    user.put("Apellidos", Apellidos);
                    user.put("CodPostal", CodPostal);
                    user.put("Direccion", Direccion);
                    db.collection("users").document(email).set(user);

                    Intent myIntent = new Intent(RegistroActivity.this,MainActivity.class);
                    startActivity(myIntent);
                }
                break;
            case R.id.salirButton:
                FirebaseAuth.getInstance().signOut();
                finish();
                System.exit(0);
                break;
        }
    }

    public boolean validar(){
        boolean retorno=true;
        String nom = nombre.getText().toString();
        String apel = apellidos.getText().toString();
        String cp = codigoPostal.getText().toString();
        if (nom.isEmpty()){
            nombre.setError("Nombre obligatorio");
            retorno = false;
        }
        if (apel.isEmpty()){
            apellidos.setError("Apellidos obligatorio");
            retorno = false;
        }
        if (cp.isEmpty()){
            codigoPostal.setError("Código Postal obligatorio");
            retorno = false;
        }
        return retorno;
    }
}