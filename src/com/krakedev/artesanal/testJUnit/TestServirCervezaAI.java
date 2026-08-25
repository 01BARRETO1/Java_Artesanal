package com.krakedev.artesanal.testJUnit;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para el método servirCerveza(double cantidad)
 * Nombre requerido: TestServirCervezaAI
 */
public class TestServirCervezaAI {

    private static final double DELTA = 1e-6;

    /**
     * Caso 1:
     * Valida que cuando la máquina tiene suficiente cerveza y se solicita exactamente
     * la cantidad disponible, se sirva correctamente, la cantidadActual quede en 0
     * y el valor retornado sea cantidad * precioPorMl.
     *
     * Usa el constructor con los cuatro parámetros.
     */
    @Test
    public void servirExactamenteCantidadDisponible() {
        Maquina m = new Maquina("Lager", "Cerveza clara", 0.01, 2000.0);
        // recargar 500 ml (dentro del límite)
        boolean recargoOk = m.recargarCerveza(500.0);
        assertTrue(recargoOk);
        // servir exactamente 500 ml
        double valor = m.servirCerveza(500.0);
        // valor esperado = 500 * 0.01 = 5.0
        assertEquals(5.0, valor, DELTA);
        // cantidadActual debe quedar en 0
        assertEquals(0.0, m.getCantidadActual(), DELTA);
    }

    /**
     * Caso 2:
     * Valida que cuando la máquina tiene más cerveza de la solicitada, se sirva la
     * cantidad pedida, se reduzca cantidadActual en esa cantidad y el valor retornado
     * sea cantidad * precioPorMl.
     *
     * Usa el constructor con los tres parámetros (capacidad por defecto).
     */
    @Test
    public void servirMenosQueDisponibleReduceCantidadActual() {
        Maquina m = new Maquina("IPA", "Cerveza amarga", 0.02);
        // recargar 2000 ml
        boolean recargoOk = m.recargarCerveza(2000.0);
        assertTrue(recargoOk);
        // servir 750 ml
        double valor = m.servirCerveza(750.0);
        // valor esperado = 750 * 0.02 = 15.0
        assertEquals(15.0, valor, DELTA);
        // cantidadActual debe haberse reducido en 750 (2000 - 750 = 1250)
        assertEquals(1250.0, m.getCantidadActual(), DELTA);
    }

    /**
     * Caso 3:
     * Valida que si la máquina no tiene suficiente cerveza para la cantidad solicitada,
     * no sirva nada (retorne 0) y no modifique la cantidadActual.
     *
     * Usa el constructor con los cuatro parámetros.
     */
    @Test
    public void noServirSiNoHaySuficienteCerveza() {
        Maquina m = new Maquina("Stout", "Cerveza oscura", 0.05, 1000.0);
        // recargar 300 ml
        boolean recargoOk = m.recargarCerveza(300.0);
        assertTrue(recargoOk);
        double antes = m.getCantidadActual();
        // intentar servir 500 ml (más de lo disponible)
        double valor = m.servirCerveza(500.0);
        // debe retornar 0
        assertEquals(0.0, valor, DELTA);
        // cantidadActual no debe cambiar
        assertEquals(antes, m.getCantidadActual(), DELTA);
    }

    /**
     * Caso 4:
     * Valida el comportamiento cuando se usa llenarMaquina() para establecer
     * cantidadActual = capacidadMaxima - 100, y luego se sirve una cantidad válida.
     * Verifica que el valor retornado sea correcto y que cantidadActual se reduzca.
     *
     * Usa el constructor con los cuatro parámetros.
     */
    @Test
    public void servirDespuesDeLlenarMaquina() {
        Maquina m = new Maquina("Pilsner", "Cerveza ligera", 0.015, 5000.0);
        // llenarMaquina deja cantidadActual = capacidadMaxima - 100 = 4900
        m.llenarMaquina();
        double antes = m.getCantidadActual();
        // servir 1000 ml
        double valor = m.servirCerveza(1000.0);
        // valor esperado = 1000 * 0.015 = 15.0
        assertEquals(15.0, valor, DELTA);
        // cantidadActual debe haberse reducido en 1000 (4900 - 1000 = 3900)
        assertEquals(antes - 1000.0, m.getCantidadActual(), DELTA);
    }

    /**
     * Caso 5 (borde):
     * Valida que solicitar servir 0 ml no modifique la cantidadActual y retorne 0.
     *
     * Usa el constructor con los tres parámetros.
     */
    @Test
    public void servirCeroNoModificaCantidadYRetornaCero() {
        Maquina m = new Maquina("Amber", "Cerveza ámbar", 0.03);
        // recargar 1000 ml
        boolean recargoOk = m.recargarCerveza(1000.0);
        assertTrue(recargoOk);
        double antes = m.getCantidadActual();
        // servir 0 ml
        double valor = m.servirCerveza(0.0);
        // debe retornar 0
        assertEquals(0.0, valor, DELTA);
        // cantidadActual no debe cambiar
        assertEquals(antes, m.getCantidadActual(), DELTA);
    }
}
