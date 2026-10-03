package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejorado2JUnit {

    /**
     * Se prueba agregarMaquina:
     * - Al invocar agregarMaquina se debe añadir una Maquina a la lista interna.
     * - La máquina añadida debe conservar nombre, descripción y precio.
     * - El código generado debe comenzar con "M-".
     * Resultado esperado: lista con tamaño 1 y campos coincidentes.
     */
    @Test
    public void agregarMaquina_AgregaMaquinaALaLista_YCamposCorrectos() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Ejecutar: agregar una máquina
        negocio.agregarMaquina("Pilsener", "Rubia", 0.05);

        // Verificar: se agregó exactamente una máquina
        assertEquals(1, negocio.getMaquinas().size(), "Debe existir exactamente una máquina en la lista");

        Maquina m = negocio.getMaquinas().get(0);

        // Verificar campos básicos
        assertEquals("Pilsener", m.getNombreCerveza(), "El nombre de la cerveza debe coincidir");
        assertEquals("Rubia", m.getDescripcion(), "La descripción debe coincidir");
        assertEquals(0.05, m.getPrecioPorMl(), 0.0001, "El precio por ml debe coincidir");

        // Verificar formato del código (comienza con "M-")
        assertTrue(m.getCodigo().startsWith("M-"), "El código debe comenzar con 'M-'");
    }

    /**
     * Se prueba cargarMaquinas:
     * - Dada una lista de máquinas con cantidadActual inicial 0, al invocar cargarMaquinas()
     *   cada máquina debe quedar con cantidadActual = capacidadMaxima - 200.
     * Resultado esperado: todas las máquinas actualizan su cantidadActual correctamente.
     */
    @Test
    public void cargarMaquinas_LlenaTodasLasMaquinas_CantidadActualAjustada() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Crear máquinas manualmente con constructor que fija capacidad por defecto (10000)
        Maquina m1 = new Maquina("A", "descA", 0.05, "M-1");
        Maquina m2 = new Maquina("B", "descB", 0.06, "M-2");

        // Asegurarnos que inicialmente la cantidadActual es 0
        assertEquals(0.0, m1.getCantidadActual(), 0.0001, "Inicialmente cantidadActual debe ser 0");
        assertEquals(0.0, m2.getCantidadActual(), 0.0001, "Inicialmente cantidadActual debe ser 0");

        // Agregar a la lista del negocio
        negocio.getMaquinas().add(m1);
        negocio.getMaquinas().add(m2);

        // Ejecutar: cargar (llenar) todas las máquinas
        negocio.cargarMaquinas();

        // Verificar: cada máquina quedó con capacidadMaxima - 200
        double esperado = m1.getCapacidadMaxima() - 200.0;
        assertEquals(esperado, m1.getCantidadActual(), 0.0001, "m1 debe quedar con capacidadMaxima - 200");
        assertEquals(esperado, m2.getCantidadActual(), 0.0001, "m2 debe quedar con capacidadMaxima - 200");
    }

    /**
     * Se prueba recuperarMaquina:
     * - Si existe una máquina con el código solicitado, debe retornarse esa instancia.
     * - Si no existe, debe retornarse null.
     * Resultado esperado: recuperarMaquina devuelve la máquina correcta y null para códigos inexistentes.
     */
    @Test
    public void recuperarMaquina_RetornaMaquinaPorCodigo_YNullSiNoExiste() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Crear y agregar una máquina con código conocido
        Maquina buscada = new Maquina("Especial", "desc", 0.10, "M-123");
        negocio.getMaquinas().add(buscada);

        // Ejecutar: recuperar por código existente
        Maquina encontrada = negocio.recuperarMaquina("M-123");
        assertEquals(buscada, encontrada, "Debe retornar la misma instancia de Maquina para el código existente");

        // Ejecutar: recuperar por código inexistente
        Maquina noExiste = negocio.recuperarMaquina("M-999");
        assertNull(noExiste, "Debe retornar null cuando no existe una máquina con ese código");
    }
}
