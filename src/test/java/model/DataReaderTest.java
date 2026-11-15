package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderTest {

    public static class DataReaderMock implements DataReader {
        @Override
        public List<UserInfo> read() {
            return new ArrayList<>();
        }

        @Override
        public List<UserInfo> read(int startIndex, int endIndex) {
            return new ArrayList<>();
        }
    }

    @Test
    public void testMustBeInstanciable() {
        assertInstanceOf(DataReader.class, new DataReaderMock(), "1. Integracao com 'DataReader'");
    }

}
