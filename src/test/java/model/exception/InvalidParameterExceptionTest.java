package model.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidParameterExceptionTest {

    @Test
    public void testMustBeInstanciable() {
        String mensagemEsperada = "teste";
        InvalidParameterException exception = new InvalidParameterException(mensagemEsperada);

        assertEquals(mensagemEsperada, exception.getMessage(), "1. Devera ser instanciado corretamente");
    }

    @Test
    public void testMustBeThrowable() {
        String mensagemErro = "teste";

        assertThrows(InvalidParameterException.class, () -> {
            throw new InvalidParameterException(mensagemErro);
        }, "2. Devera ser lancado corretamente");
    }
}
