package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadora.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String texto = "";
    private int ultimoResultado = 0;
    private boolean nuevoNumero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignamos listeners a todos los botones
        binding.button0.setOnClickListener(v -> onButtonClick(binding.button0));
        binding.button1.setOnClickListener(v -> onButtonClick(binding.button1));
        binding.button2.setOnClickListener(v -> onButtonClick(binding.button2));
        binding.button3.setOnClickListener(v -> onButtonClick(binding.button3));
        binding.button4.setOnClickListener(v -> onButtonClick(binding.button4));
        binding.button5.setOnClickListener(v -> onButtonClick(binding.button5));
        binding.button6.setOnClickListener(v -> onButtonClick(binding.button6));
        binding.button7.setOnClickListener(v -> onButtonClick(binding.button7));
        binding.button8.setOnClickListener(v -> onButtonClick(binding.button8));
        binding.button9.setOnClickListener(v -> onButtonClick(binding.button9));
        binding.suma.setOnClickListener(v -> onButtonClick(binding.suma));
        binding.resta.setOnClickListener(v -> onButtonClick(binding.resta));
        binding.multiplicacion.setOnClickListener(v -> onButtonClick(binding.multiplicacion));
        binding.division.setOnClickListener(v -> onButtonClick(binding.division));
        binding.clear.setOnClickListener(v -> onButtonClick(binding.clear));
        binding.igual.setOnClickListener(v -> onButtonClick(binding.igual));
        binding.borrar.setOnClickListener(v -> onButtonClick(binding.borrar));
    }

    private void onButtonClick(Button button) {
        // Limpiamos el textview y reseteamos el resultado
        if (button.getId() == R.id.clear) {
            ultimoResultado = 0;
            binding.resultado.setText("0");
            nuevoNumero = false;
        // Maneja el resultado
        } else if (button.getId() == R.id.igual) {
            try {
                // guarda en ultimoResultado el resultado de calcular el string
                ultimoResultado = calculate(texto);
                binding.resultado.setText(String.valueOf(ultimoResultado));
                texto = String.valueOf(ultimoResultado);
                nuevoNumero = true; // operamos desde el último resultado asignado
            } catch (Exception e) {
                binding.resultado.setText("-1"); // En caso de error, muestra -1
            }
        } else if (button.getId() == R.id.borrar) {
            if (!texto.isEmpty()) {
                // Eliminar el último carácter de la cadena
                texto = texto.substring(0, texto.length() - 1);
                // Si después de borrar no queda nada, mostrar 0
                if (texto.isEmpty()) {
                    binding.resultado.setText("0");
                } else {
                    binding.resultado.setText(texto);
                }
            }
        } else {
            String buttonText = button.getText().toString();
            texto += buttonText;
            binding.resultado.setText(texto);
        }
    }

    // Método recursivo que calcula la suma, resta, multiplicación y división
    private int calculate(String s) {
        if (s.contains("+")) {
            // los strings que contengan caracteres como operadores +, *, / tienen que ir con dos \\ delante
            String[] partes = s.split("\\+", 2);
            return calculate(partes[0]) + calculate(partes[1]);
        } else if (s.contains("-")) {
            String[] partes = s.split("-", 2);
            return calculate(partes[0]) - calculate(partes[1]);
        } else if (s.contains("x")) {
            String[] partes = s.split("x", 2);
            return calculate(partes[0]) * calculate(partes[1]);
        } else if (s.contains("%")) {
            String[] partes = s.split("%", 2);
            int divisor = calculate(partes[1]);
            if (divisor == 0) {
                binding.resultado.setText("0");
            }
            return calculate(partes[0]) / divisor;
        } else {
            return Integer.parseInt(s); // Si no hay operadores, simplemente devuelve el número
        }
    }

}