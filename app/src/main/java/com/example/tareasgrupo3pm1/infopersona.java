package com.example.tareasgrupo3pm1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


public class infopersona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopersona);

        TextView txtResultado = findViewById(R.id.txtResultado);

        String nombre = getIntent().getStringExtra("nombre");
        String apellidos = getIntent().getStringExtra("apellidos");
        int edad = getIntent().getIntExtra("edad", 0);
        String correo = getIntent().getStringExtra("correo");
        String direccion = getIntent().getStringExtra("direccion");

        String info = "Nombre: " + nombre + "\n"
                + "Apellidos: " + apellidos + "\n"
                + "Edad: " + edad + "\n"
                + "Correo: " + correo + "\n"
                + "Direccion: " + direccion;

        txtResultado.setText(info);
    }
}