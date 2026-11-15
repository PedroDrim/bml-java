package model.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BenchmarkExceptionTest {

    @Test
    public void testMustBeInstanciable() {
        String mensagemEsperada = "teste";
        BenchmarkException exception = new BenchmarkException(mensagemEsperada);

        assertEquals(mensagemEsperada, exception.getMessage(), "1. Devera ser instanciado corretamente");
    }

    @Test
    public void testMustBeThrowable() {
        String mensagemErro = "teste";

        assertThrows(BenchmarkException.class, () -> {
            throw new BenchmarkException(mensagemErro);
        }, "2. Devera ser lancado corretamente");
    }
}
