package com.example.tareasgrupo3pm1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numero1, numero2;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir;
    private OperacionesMatematicas operaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);

        operaciones =new OperacionesMatematicas();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarInputs()) {
                    double num1 = Double.parseDouble(numero1.getText().toString());
                    double num2 = Double.parseDouble(numero2.getText().toString());
                    double resultado = 0;
                    String operacion = "";

                    try {
                        int id = v.getId();
                        if (id == R.id.btnSumar) {
                            resultado = operaciones.sumar(num1, num2);
                            operacion = "Suma";
                        } else if (id == R.id.btnRestar) {
                            resultado = operaciones.restar(num1, num2);
                            operacion = "Resta";
                        } else if (id == R.id.btnMultiplicar) {
                            resultado = operaciones.multiplicar(num1, num2);
                            operacion = "Multiplicación";
                        } else if (id == R.id.btnDividir) {
                            resultado = operaciones.dividir(num1, num2);
                            operacion = "División";
                        }

                        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
                        intent.putExtra("resultado", resultado);
                        intent.putExtra("operacion", operacion);
                        startActivity(intent);

                    } catch (IllegalArgumentException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        btnSumar.setOnClickListener(listener);
        btnRestar.setOnClickListener(listener);
        btnMultiplicar.setOnClickListener(listener);
        btnDividir.setOnClickListener(listener);
    }

    private boolean validarInputs() {
        String n1 = numero1.getText().toString();
        String n2 = numero2.getText().toString();

        if (n1.isEmpty() || n2.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos números", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Double.parseDouble(n1);
            Double.parseDouble(n2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese solo valores numéricos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

