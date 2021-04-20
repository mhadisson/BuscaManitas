package com.hardisson.buscamanitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton home = (ImageButton) findViewById(R.id.homeButton);
        home.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent myIntent = new Intent(HomeActivity.this,AuthActivity.class);
        startActivity(myIntent);
    }
}