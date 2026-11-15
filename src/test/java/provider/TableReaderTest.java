package provider;

import model.UserInfo;
import model.exception.DataReaderException;
import model.exception.InvalidParameterException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TableReaderTest {

    @Test
    public void mustBeInstanciableByDefault() {
        assertDoesNotThrow(() -> {
            TableReader tableReader = new TableReader("./data/test.csv");
        }, "1. Devera ser instanciavel caso possua valor valido");
    }

    @Test
    public void readMustBeValidByDefault() {
        TableReader tableReader = new TableReader("./data/test.csv");

        List<Double> expected = Stream.of(
                new UserInfo("u1", "p1", 1.0),
                new UserInfo("u2", "p2", 2.0),
                new UserInfo("u3", "p3", 3.0),
                new UserInfo("u4", "p4", 4.0),
                new UserInfo("u5", "p5", 5.0),
                new UserInfo("u6", "p6", 6.0),
                new UserInfo("u7", "p7", 7.0),
                new UserInfo("u8", "p8", 8.0),
                new UserInfo("u9", "p9", 9.0),
                new UserInfo("u10", "p10", 10.0)
        ).map(UserInfo::getCredit).toList();

        List<Double> response = tableReader.read().stream().map(UserInfo::getCredit).toList();
        assertIterableEquals(expected, response, "2. 'read()' devera retornar valor valido por padrao");
    }

    @Test
    public void readMustBeValidWithValidParameters() {
        TableReader tableReader = new TableReader("./data/test.csv");

        List<Double> expected = Stream.of(
                new UserInfo("u2", "p2", 2.0)
        ).map(UserInfo::getCredit).toList();

        List<Double> response = tableReader.read(1, 2).stream().map(UserInfo::getCredit).toList();
        assertIterableEquals(expected, response, "3. 'read()' devera retornar valor valido caso possua valores validos");
    }

    @Test
    public void readMustBeInvalidWithStartNegative() {
        TableReader tableReader = new TableReader("./data/test.csv");
        assertThrows(InvalidParameterException.class, () -> {
            tableReader.read(-2, 1);
        }, "4. 'read()' devera ser invalido caso 'start' seja negativo");
    }

    @Test
    public void readMustBeInvalidWithEndNegative() {
        TableReader tableReader = new TableReader("./data/test.csv");
        assertThrows(InvalidParameterException.class, () -> {
            tableReader.read(2, -1);
        }, "5. 'read()' devera ser invalido caso 'end' seja negativo");
    }

    @Test
    public void readMustBeInvalidWithEndLessThanStart() {
        TableReader tableReader = new TableReader("./data/test.csv");
        assertThrows(InvalidParameterException.class, () -> {
            tableReader.read(2, 1);
        }, "6. 'read()' devera ser invalido caso 'end' seja menor que 'start'");
    }

    @Test
    public void readMustBeInvalidWithStartEqualEnd() {
        TableReader tableReader = new TableReader("./data/test.csv");
        assertThrows(InvalidParameterException.class, () -> {
            tableReader.read(2, 2);
        }, "7. 'read()' devera ser invalido caso 'end' seja igual a 'start'");
    }
}
