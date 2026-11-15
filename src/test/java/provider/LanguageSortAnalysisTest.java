package provider;

import model.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageSortAnalysisTest {

    @Test
    public void mustBeInstanciableByDefault() {
        assertDoesNotThrow(() -> {
            LanguageSortAnalysis analysis = new LanguageSortAnalysis();
        }, "1. Devera ser valido por padrao");
    }

    @Test
    public void analysisMustBeValidWithValidParameters() {
        LanguageSortAnalysis analysis = new LanguageSortAnalysis();
        List<UserInfo> value = List.of(
                new UserInfo("u2", "p2", 2.0),
                new UserInfo("u1", "p1", 1.0),
                new UserInfo("u3", "p3", 3.0)
        );

        List<Double> expected = Stream.of(
                new UserInfo("u3", "p3", 3.0),
                new UserInfo("u2", "p2", 2.0),
                new UserInfo("u1", "p1", 1.0)
        ).map(UserInfo::getCredit).toList();

        List<Double> response = analysis.analysis(value).stream().map(UserInfo::getCredit).toList();
        assertIterableEquals(expected, response, "2. 'analysis' devera ser valido caso possua valores validos");
    }
}
