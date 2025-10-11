package com.example.tareasgrupo3pm1;

public class OperacionesMatematicas {
    public double sumar(double a, double b)
    {
        return a + b;
    }
    public double restar(double a, double b)
    {
        return a - b;
    }
    public double multiplicar(double a, double b)
    {
        return a * b;
    }
    public double dividir(double a, double b)
    {
        if (b==0){
            throw new IllegalArgumentException("No se puede dividir entre cero");
        }
        return a / b;
    }
}
