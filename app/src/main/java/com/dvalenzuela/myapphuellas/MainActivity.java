package com.dvalenzuela.myapphuellas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar;
    TextView linkIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }
    private void asignarReferencias() {
        btnGuardar = findViewById(R.id.btnRegitstrar);
        linkIniciarSesion = findViewById(R.id.linkIniciarSesion);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuarios.class);
                startActivity(intent);
            }
        });

        linkIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, RegistrarUsuarios.class);
                //startActivity(intent);
            }
        });

    }


}