package model.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderExceptionTest {

    @Test
    public void testMustBeInstanciable() {
        String mensagemEsperada = "teste";
        DataReaderException exception = new DataReaderException(mensagemEsperada);

        assertEquals(mensagemEsperada, exception.getMessage(), "1. Devera ser instanciado corretamente");
    }

    @Test
    public void testMustBeThrowable() {
        String mensagemErro = "teste";

        assertThrows(DataReaderException.class, () -> {
            throw new DataReaderException(mensagemErro);
        }, "2. Devera ser lancado corretamente");
    }
}
