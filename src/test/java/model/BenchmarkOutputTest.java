package model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BenchmarkOutputTest {

    public static class BenchmarkOutputMock implements BenchmarkOutput {
        @Override
        public void start(String tag) {
        }

        @Override
        public void end(String tag) {
        }

        @Override
        public double result(String tag, TimeFormat format) {
            return 0.0;
        }

        @Override
        public Map<String, Double> result(TimeFormat format) {
            return new HashMap<>();
        }

        @Override
        public void export(String fileName, TimeFormat format) {
        }
    }

    @Test
    public void testMustBeInstanciable() {
        assertInstanceOf(BenchmarkOutput.class, new BenchmarkOutputMock(), "1. Integracao com 'BenchmarkOutput'");
    }

}
