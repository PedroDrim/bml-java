package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeFormatTest {

    @Test
    public void testSegundosValue() {
        assertEquals(0.001, TimeFormat.SEGUNDOS.getValor(), "1. Devera obter o valor de 'SEGUNDOS' corretamente");
    }

    @Test
    public void testMilissegundosValue() {
        assertEquals(1.0, TimeFormat.MILISSEGUNDOS.getValor(), "2. Devera obter o valor de 'MILISSEGUNDOS' corretamente");
    }

    @Test
    public void testNanossegundosValue() {
        assertEquals(1000.0, TimeFormat.NANOSSEGUNDOS.getValor(), "3. Devera obter o valor de 'NANOSSEGUNDOS' corretamente");
    }
}
