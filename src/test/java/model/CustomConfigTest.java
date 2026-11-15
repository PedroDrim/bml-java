package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomConfigTest {

    @Test
    public void testNewInstance() {
        CustomConfig config = new CustomConfig(new String[0], "p1");
        assertInstanceOf(CustomConfig.class, config, "1. Devera ser instanciavel corretamente");
    }

    @Test
    public void testGetInputFilenameList() {
        String[] expected = new String[0];
        CustomConfig config = new CustomConfig(expected, "p1");
        assertEquals(expected, config.getINPUT_FILENAME_LIST(), "2. 'INPUT_FILENAME_LIST' devera ser obtido corretamente");
    }

    @Test
    public void testGeOutputFilename() {
        String expected = "output";
        CustomConfig config = new CustomConfig(new String[0], expected);
        assertEquals(expected, config.getOUTPUT_FILENAME(), "3. 'OUTPUT_FILENAME' devera ser executado corretamente");
    }
}
