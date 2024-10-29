package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadora.databinding.ActivityMainBinding;

/**
 * La clase MainActivity representa la actividad principal de la aplicación de calculadora.
 * Es responsable de gestionar la interfaz de usuario y de actuar como intermediaria entre
 * la interfaz y la lógica de cálculo que reside en la clase {@link Calculator}.
 *
 * @author c-tina
 * @version 1.2.0
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    /** Enlace a la vista de la actividad principal, manejada mediante View Binding */
    ActivityMainBinding binding;
    /** Cadena que almacena el texto de la expresión matemática en construcción */
    private String texto = "";
    /** Variable que almacena el último resultado calculado */
    private int ultimoResultado = 0;
    /** Indica si se está introduciendo un nuevo número después de una operación */
    private boolean nuevoNumero = false;

    /** Instancia de la clase Calculator, responsable de realizar los cálculos */
    private Calculator calculator = new Calculator();

    /**
     * Método que se llama al crear la actividad. Inicializa la interfaz de usuario,
     * configura los insets de la ventana y asigna los listeners a los botones.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        // Configura los insets de la ventana para una experiencia de usuario mejorada
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asigna listeners a todos los botones de la calculadora
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

    /**
     * Método que se llama al hacer clic en un botón de la interfaz.
     * Gestiona el comportamiento de los botones numéricos, de operación y otras funciones.
     *
     * @param button El botón que se pulsa.
     */
    private void onButtonClick(Button button) {
        // Si se presiona el botón de limpiar, restablece el texto y el resultado
        if (button.getId() == R.id.clear) {
            ultimoResultado = 0;
            binding.resultado.setText("0");
            texto = "";
            nuevoNumero = false;
        // Si se presiona el botón de igual, calcula el resultado de la expresión
        } else if (button.getId() == R.id.igual) {
            try {
                // Utiliza Calculator para calcular el resultado de la expresión
                ultimoResultado = calculator.calculate(texto);
                binding.resultado.setText(String.valueOf(ultimoResultado));
                texto = String.valueOf(ultimoResultado);
                nuevoNumero = true; // Marca que se está operando desde el último resultado calculado
            } catch (ArithmeticException e) {
                binding.resultado.setText("-1"); // En caso de error, muestra -1
            }
        // Si se presiona el botón de borrar, elimina el último carácter de la expresión
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
        // Para otros botones (números y operadores), agrega el texto del botón a la expresión
        } else {
            String buttonText = button.getText().toString();
            texto += buttonText;
            binding.resultado.setText(texto);
        }
    }
}