package com.example.tareasgrupo3pm1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class EditarPersonaActivity extends AppCompatActivity {

    EditText etNombre, etApellidos, etEdad, etCorreo, etDireccion;
    Button btnActualizar, btnEliminar;
    DBHelper dbHelper;
    int personaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etEdad = findViewById(R.id.etEdad);
        etCorreo = findViewById(R.id.etCorreo);
        etDireccion = findViewById(R.id.etDireccion);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        dbHelper = new DBHelper(this);
        personaId = getIntent().getIntExtra("id", -1);

        cargarDatos();

        btnActualizar.setOnClickListener(v -> actualizarPersona());
        btnEliminar.setOnClickListener(v -> eliminarPersona());
    }

    private void cargarDatos() {
        Cursor cursor = dbHelper.obtenerPersonas();
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) == personaId) {
                etNombre.setText(cursor.getString(1));
                etApellidos.setText(cursor.getString(2));
                etEdad.setText(String.valueOf(cursor.getInt(3)));
                etCorreo.setText(cursor.getString(4));
                etDireccion.setText(cursor.getString(5));
                break;
            }
        }
        cursor.close();
    }

    private void actualizarPersona() {
        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String correo = etCorreo.getText().toString();
        String direccion = etDireccion.getText().toString();

        boolean actualizado = dbHelper.actualizarPersona(personaId, nombre, apellidos, edad, correo, direccion);
        if (actualizado) {
            Toast.makeText(this, "Persona actualizada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarPersona() {
        boolean eliminado = dbHelper.eliminarPersona(personaId);
        if (eliminado) {
            Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}