package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableAnalysisTest {

    public static class TableAnalysisMock implements TableAnalysis<Double> {
        @Override
        public Double analysis(List<UserInfo> userInfoList) {
            return 0.0;
        }
    }

    @Test
    public void testMustBeInstanciable() {
        assertInstanceOf(TableAnalysis.class, new TableAnalysisMock(), "1. Integracao com 'TableAnalysis'");
    }
}
