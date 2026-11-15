package model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SiimpleTableAnalysisTest {

    @Test
    public void testIntegration() {
        SimpleTableAnalysis mock = new MockSimpleTableAnalysis();
        List<UserInfo> list = Collections.emptyList();
        assertEquals(0.0, mock.analysis(list), "1. Integracao do 'analysis()'");
    }
}

class MockSimpleTableAnalysis implements SimpleTableAnalysis {

    @Override
    public double analysis(List<UserInfo> userInfoList) {
        return 0.0;
    }
}
