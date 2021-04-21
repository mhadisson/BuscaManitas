package com.hardisson.buscamanitas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton fontaneros = (ImageButton) findViewById(R.id.fontanerosButton);
        fontaneros.setOnClickListener(this);

        ImageButton electricistas = (ImageButton) findViewById(R.id.electricistasButton);
        electricistas.setOnClickListener(this);

        ImageButton pintores = (ImageButton) findViewById(R.id.pintoresButton);
        pintores.setOnClickListener(this);

        ImageButton obras = (ImageButton) findViewById(R.id.obrasButton);
        obras.setOnClickListener(this);

        Button salir = (Button) findViewById(R.id.logOutButton);
        salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fontanerosButton:
                Toast.makeText(getApplicationContext(), "Fontaneros", Toast.LENGTH_LONG).show();
                break;
            case R.id.electricistasButton:
                Toast.makeText(getApplicationContext(), "Electricistas", Toast.LENGTH_LONG).show();
                break;
            case R.id.pintoresButton:
                Toast.makeText(getApplicationContext(), "Pintores", Toast.LENGTH_LONG).show();
                break;
            case R.id.obrasButton:
                Toast.makeText(getApplicationContext(), "Obras", Toast.LENGTH_LONG).show();
                break;
            case R.id.logOutButton:
                FirebaseAuth.getInstance().signOut();
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}