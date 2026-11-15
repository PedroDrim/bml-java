package provider;

import model.SimpleTableAnalysis;
import model.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MeanValueAnalysisTest {

    @Test
    public void testNewInstance() {
        SimpleTableAnalysis mock = new MeanAnalysis();
        assertInstanceOf(MeanAnalysis.class, mock, "1. Devera ser instanciavel");
    }

    @Test
    public void testAnalysisMethod() {
        SimpleTableAnalysis mock = new MeanAnalysis();
        List<UserInfo> list = List.of(
                new UserInfo("u1", "p1", 1.0),
                new UserInfo("u2", "p2", 2.0),
                new UserInfo("u3", "p3", 3.0)
        );
        assertEquals(2.0, mock.analysis(list), "2. 'analysis()' devera ser valido caso possua parametros validos'");
    }
}
