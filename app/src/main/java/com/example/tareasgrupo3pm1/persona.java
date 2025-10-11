package com.example.tareasgrupo3pm1;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class persona extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtCorreo;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarinformacion();
            }
        });

    }

    private void enviarinformacion(){
        String nombre = txtNombre.getText().toString().trim();
        String apellidos = txtApellidos.getText().toString().trim();
        String edadStr = txtEdad.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();

        Intent intent = new Intent(persona.this, infopersona.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        intent.putExtra("edad", edadStr);
        intent.putExtra("correo", correo);
        startActivity(intent);
    }
}