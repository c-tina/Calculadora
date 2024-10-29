package com.example.calculadora;

import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas unitarias para la clase {@link Calculator}.
 * Verifica el correcto funcionamiento de las operaciones aritméticas básicas
 * (suma, multiplicación) y el manejo de errores de sintaxis en la entrada.
 * Comprueba que la calculadora respete la precedencia de operadores.
 *
 * @author c-tina
 * @version 1.2.0
 * @since 1.2.0
 */
public class CalculatorTest {
    private Calculator calc;

    /**
     * Configura la instancia de {@link Calculator} antes de cada prueba.
     */
    @Before
    public void setUp(){
        calc = new Calculator();
        System.out.println("Ready for testing.");
    }

    /**
     * Limpia los recursos después de cada prueba.
     */
    @After
    public void tearDown(){
        System.out.println("Done with testing.");
    }

    /**
     * Prueba la suma de dos operandos. Verifica que la operación 5 + 3
     * devuelve el resultado esperado de 8.
     */
    @Test
    public void testAdd2Operands() {
        int total = calc.calculate("5+3");
        assertEquals("X + Y operations not working correctly", 8, total);
    }

    /**
     * Prueba la suma de tres operandos. Verifica que la operación 4 + 3 + 1
     * devuelve el resultado esperado de 8.
     */
    @Test
    public void testAdd1Operand() {
        int total = calc.calculate("4+3+1");
        assertEquals("+X operations not working correctly", 8, total);
    }

    /**
     * Prueba la suma de cuatro operandos. Verifica que la operación 4 + 4 + 4 + 4
     * devuelve el resultado esperado de 16.
     */
    @Test
    public void testAdd4Operand(){
        int total = calc.calculate("4+4+4+4");
        assertEquals("4+4+4+4 should equal 16", 16, total);
    }

    /**
     * Prueba la resta de dos operandos. Verifica que la operación 5-3
     * devuelve el resultado esperado de 2.
     */
    @Test
    public void testSubstraction2Operands(){
        int total = calc.calculate("5-3");
        assertEquals("5-3 should equal 2", 2, total);
    }

    /**
     * Prueba la multiplicación de dos operandos. Verifica que la operación 4 * 2
     * devuelve el resultado esperado de 8.
     */
    @Test
    public void testMult2Operands() {
        int total = calc.calculate("4x2");
        assertEquals("4*X operations not working correctly", 8, total);
    }

    /**
     * Prueba una operación de multiplicación simple. Verifica que la operación 2 * 3
     * devuelve el resultado esperado de 6.
     */
    @Test
    public void testMultiplication() {
        int total = calc.calculate("2x3");
        assertEquals("2*3 should equal 6", 6, total);
    }

    /**
     * Prueba una operación de multiplicación compleja con varios operandos.
     * Verifica que la operación 1 * 2 * 8 devuelve el resultado esperado de 16.
     */
    @Test
    public void testComplexMultiplication() {
        int total = calc.calculate("1x2x8");
        assertEquals("1*2*8 should equal 16", 16, total);
    }

    /**
     * Prueba la combinación de multiplicación y suma en una expresión.
     * Verifica que la operación 2 * 2 + 3 respete la precedencia y devuelva 7.
     */
    @Test
    public void testAdditionAndMultiplication() {
        int total = calc.calculate("2x2+3");
        assertEquals("2*2+3 should equal 7", 7, total);
    }

    /**
     * Prueba una expresión mixta de suma y multiplicación. Verifica que la operación
     * 3 + 2 * 2 respete la precedencia y devuelva el resultado esperado de 7.
     */
    @Test
    public void testMixedOperations() {
        int total = calc.calculate("3+2x2");
        assertEquals("3+2*2 should equal 7", 7, total);
    }

    /**
     * Prueba una expresión más larga con suma y multiplicación.
     * Verifica que la operación 3 + 2 * 2 + 4 respete la precedencia y devuelva 11.
     */
    @Test
    public void testLongMixedOperations() {
        int total = calc.calculate("3+2x2+4");
        assertEquals("3+2*2+4 should equal 11", 11, total);
    }

    /**
     * Prueba para verificar la operación de división.
     * Calcula el resultado de la expresión "10%5" y verifica que el valor sea 2.
     */
    @Test
    public void testDivision() {
        int total = calc.calculate("10%5");
        assertEquals("10%5 should equal 2", 2, total);
    }

    /**
     * Prueba para verificar el comportamiento de la división por cero.
     * Devuelve -1 cuando se intenta dividir un número entre cero.
     */
    @Test
    public void testDivision0() {
        int total = calc.calculate("10%0");
        assertEquals("10%0 should equal -1", -1, total);
    }

    /**
     * Prueba para verificar la operación combinada de división y multiplicación.
     * Calcula el resultado de la expresión "10%5x2" y verifica que el valor sea 4.
     */
    @Test
    public void testDivandMult() {
        int total = calc.calculate("10%5x2");
        assertEquals("10%5*2 should equal 4", 4, total);
    }

    /**
     * Prueba para verificar el cálculo con múltiples operaciones (multiplicación, resta, división y suma).
     * Calcula el resultado de la expresión "25x3-10%5x4+7" y verifica que el valor sea 74.
     */
    @Test
    public void testAllOperations() {
        int total = calc.calculate("25x3-10%5x4+7");
        assertEquals("25x3-10%5x4+7 should equal 74", 74, total);
    }

    /**
     * Prueba la detección de errores de sintaxis. Verifica que una expresión con
     * error como "5++3" devuelve -1.
     */
    @Test
    public void testSintaxisError(){
        int total = calc.calculate("5++3");
        assertEquals("Error de sintaxis. Debe devolver -1", -1, total);
    }
}
