package com.example.tareasgrupo3pm1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView txtResultado =findViewById(R.id.txtResultado);
        TextView txtOperacion =findViewById(R.id.txtOperacion);

        double resultado = getIntent().getDoubleExtra("resultado", 0);
        String operacion = getIntent().getStringExtra("operacion");

        txtOperacion.setText("Resultado de la " + operacion + ":");
        txtResultado.setText(String.valueOf(resultado));
    }
}