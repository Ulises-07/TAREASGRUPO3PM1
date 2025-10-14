package com.example.tareasgrupo3pm1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class VerPersonasActivity extends AppCompatActivity {

    LinearLayout layoutContainer;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personas);

        layoutContainer = findViewById(R.id.layoutContainer);
        dbHelper = new DBHelper(this);

        mostrarPersonas();
    }

    private void mostrarPersonas() {
        layoutContainer.removeAllViews();
        Cursor cursor = dbHelper.obtenerPersonas();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String apellidos = cursor.getString(2);
            int edad = cursor.getInt(3);
            String correo = cursor.getString(4);
            String direccion = cursor.getString(5);

            TextView text = new TextView(this);
            text.setText("ID: " + id + "\nNombre: " + nombre + "\nApellidos: " + apellidos +
                    "\nEdad: " + edad + "\nCorreo: " + correo + "\nDirección: " + direccion);
            text.setPadding(10, 10, 10, 10);

            Button btnEditar = new Button(this);
            btnEditar.setText("Editar");
            btnEditar.setOnClickListener(v -> {
                Intent intent = new Intent(VerPersonasActivity.this, EditarPersonaActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            });

            layoutContainer.addView(text);
            layoutContainer.addView(btnEditar);
        }
        cursor.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarPersonas();
    }
}
