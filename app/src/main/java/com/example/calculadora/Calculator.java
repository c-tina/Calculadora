package com.example.calculadora;

/**
 * La clase Calculator se encarga de realizar operaciones matemáticas básicas,
 * como la suma, resta, multiplicación y división.
 *
 * Esta clase es utilizada por {@link MainActivity} para delegar la lógica de
 * los cálculos y mantener la interfaz de usuario más limpia y manejable.
 *
 * @author c-tina
 * @version 1.2.0
 * @since 1.2.0
 */

public class Calculator {
    /**
     * Calcula el resultado de una expresión aritmética simple contenida en un string.
     * Este método admite operaciones de suma, resta, multiplicación y división, y
     * respeta la prioridad de las operaciones al dividir primero en multiplicación y división.
     *
     * @param s La expresión aritmética a calcular, como una cadena de texto.
     * @return El resultado de la expresión evaluada como un entero. Devuelve -1 si la
     *         expresión contiene operadores consecutivos o si ocurre una división por cero.
     */
    public int calculate(String s) {
        // Devuelve -1 si hay dos o más operadores consecutivos
        if (s.matches(".*[+\\-x%]{2,}.*")) {
            return -1;
        }
        if (s.contains("+")) {
            // Los strings que contengan caracteres como operadores +, *, / deben llevar dos \\ delante
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
                return -1;
            }
            return calculate(partes[0]) / divisor;
        } else {
            // Si no hay operadores, simplemente devuelve el número
            return Integer.parseInt(s);
        }
    }
}
