package com.example.tareasgrupo3pm1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class persona extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtCorreo, txtDireccion;
    Button btnEnviar, btnVerRegistros;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnVerRegistros = findViewById(R.id.btnVerRegistros);

        dbHelper = new DBHelper(this);

        btnEnviar.setOnClickListener(v -> enviarinformacion());
        btnVerRegistros.setOnClickListener(v -> {
            startActivity(new Intent(persona.this, VerPersonasActivity.class));
        });
    }

    private void enviarinformacion() {
        String nombre = txtNombre.getText().toString().trim();
        String apellidos = txtApellidos.getText().toString().trim();
        String edadStr = txtEdad.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String direccion = txtDireccion.getText().toString().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || edadStr.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese una edad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        long resultado = dbHelper.insertarPersona(nombre, apellidos, edad, correo, direccion);
        if (resultado != -1) {
            Toast.makeText(this, "Persona guardada correctamente", Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtApellidos.setText("");
            txtEdad.setText("");
            txtCorreo.setText("");
            txtDireccion.setText("");
        } else {
            Toast.makeText(this, "Error al guardar en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }
}