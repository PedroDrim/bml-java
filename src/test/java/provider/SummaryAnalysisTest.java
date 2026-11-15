package provider;

import model.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SummaryAnalysisTest {

    @Test
    public void mustBeInstanciableByDefault() {
        assertDoesNotThrow(() -> {
            SummaryAnalysis summaryAnalysis = new SummaryAnalysis();
        }, "1. Devera ser valido por padrao");
    }

    @Test
    public void analysisMustBeValidWithValidParameters() {
        SummaryAnalysis summaryAnalysis = new SummaryAnalysis();
        List<UserInfo> value = List.of(
                new UserInfo("u2", "p2", 2.0),
                new UserInfo("u1", "p1", 1.0),
                new UserInfo("u3", "p3", 3.0)
        );

        double[] expected = {1.0, 3.0, 2.0};
        double[] response = summaryAnalysis.analysis(value);
        assertArrayEquals(expected, response, "2. 'analysis' devera ser valido caso possua valores validos");
    }
}
