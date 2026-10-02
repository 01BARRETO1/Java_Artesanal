package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;
import com.krakedev.artesanal.Maquina;

public class TestNegocioMejoradoJUnit {

    /**
     * Se prueba que el constructor inicializa la lista de máquinas.
     * Resultado esperado: la lista no es null y está vacía.
     */
    @Test
    public void constructorInicializaListaMaquinasVacia() {
        NegocioMejorado negocio = new NegocioMejorado();
        assertNotNull(negocio.getMaquinas(), "La lista de máquinas no debe ser null");
        assertTrue(negocio.getMaquinas().isEmpty(), "La lista debe estar vacía al inicio");
    }

    /**
     * Se prueba el setter y getter de 'maquinas'.
     * Resultado esperado: al asignar una lista con una máquina, el getter devuelve esa lista.
     */
    @Test
    public void setYGetMaquinas_ListaAsignada_ContieneElementos() {
        NegocioMejorado negocio = new NegocioMejorado();
        ArrayList<Maquina> lista = new ArrayList<>();
        lista.add(new Maquina("Pilsener", "Rubia", 0.05, 5000, "M-1"));
        negocio.setMaquinas(lista);

        assertEquals(lista, negocio.getMaquinas(), "El getter debe devolver la lista asignada");
        assertFalse(negocio.getMaquinas().isEmpty(), "La lista no debe estar vacía");
    }

    /**
     * Se prueba que setMaquinas acepta null.
     * Resultado esperado: getMaquinas devuelve null.
     */
    @Test
    public void setMaquinasConNull_devuelveNull() {
        NegocioMejorado negocio = new NegocioMejorado();
        negocio.setMaquinas(null);
        assertNull(negocio.getMaquinas(), "Al asignar null, el getter debe devolver null");
    }

    /**
     * Se prueba el método generarCodigo.
     * Resultado esperado:
     *  - No devuelve null
     *  - Comienza con "M-"
     *  - La parte numérica está entre 0 y 100 inclusive
     */
    @Test
    public void generarCodigo_FormatoYPerteneceAlRango0a100() {
        NegocioMejorado negocio = new NegocioMejorado();
        String codigo = negocio.generarCodigo();

        assertNotNull(codigo, "El código no debe ser null");
        assertTrue(codigo.startsWith("M-"), "El código debe comenzar con 'M-'");

        String numeroStr = codigo.substring(2);
        try {
            int numero = Integer.parseInt(numeroStr);
            assertTrue(numero >= 0 && numero <= 100, "El número debe estar entre 0 y 100");
        } catch (NumberFormatException e) {
            fail("La parte después de 'M-' debe ser un número entero válido");
        }
    }
}
