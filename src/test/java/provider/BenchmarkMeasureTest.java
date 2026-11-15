package provider;

import model.TimeFormat;
import model.exception.BenchmarkException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class BenchmarkMeasureTest {

    @Test
    public void mustBeInstanciable() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        assertInstanceOf(BenchmarkMeasure.class, benchmark, "1 - Devera ser instanciavel caso possua valor valido");
    }

    @Test
    public void startMustBeValidWithValidParameters() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        assertDoesNotThrow(() -> {
            benchmark.start("test");
        }, "2 - start() devera ser chamado sem erros caso possua parametros validos");
    }

    @Test
    public void endMustBeValidWithValidParameters() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        assertDoesNotThrow(() -> {
            benchmark.end("test");
        }, "3 - end() devera ser chamado sem erros caso possua parametros validos");
    }

    @Test
    public void resultMustBeValidWithValidParameters() throws InterruptedException {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        benchmark.start("test");
        sleep(10); // Simula tempo de execução
        benchmark.end("test");

        double resultado = benchmark.result("test", TimeFormat.NANOSSEGUNDOS);
        assertTrue(resultado > 0, "4 - result() devera retornar valor valido caso possua parametros validos");
    }

    @Test
    public void resultMustThrowErroWithInvalidParameters() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        assertThrows(BenchmarkException.class, () -> {
            double resultado = benchmark.result("test", TimeFormat.NANOSSEGUNDOS);
        }, "5 - result() devera retornar erro caso a tag nao exista");
    }

    @Test
    public void resultMustThrowErroWithNoStart() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        benchmark.end("test");

        assertThrows(BenchmarkException.class, () -> {
            double resultado = benchmark.result("test", TimeFormat.NANOSSEGUNDOS);
        }, "6 - result() devera retornar erro caso a tag nao possua start");
    }

    @Test
    public void resultMustThrowErroWithNoEnd() {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        benchmark.start("test");

        assertThrows(BenchmarkException.class, () -> {
            double resultado = benchmark.result("test", TimeFormat.NANOSSEGUNDOS);
        }, "7 - result() devera retornar erro caso a tag nao possua end");
    }

    @Test
    public void resultMustBeValidByDefault() throws InterruptedException {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        benchmark.start("T1");
        sleep(10);
        benchmark.end("T1");

        Map<String, Double> resultado = benchmark.result(TimeFormat.NANOSSEGUNDOS);
        assertTrue(!resultado.isEmpty(), "8 - result() devera ser valido por padrao");
    }

    @Test
    public void exportMustBeValidwithValidParameters() throws InterruptedException {
        BenchmarkMeasure benchmark = new BenchmarkMeasure();
        String fileName = "benchmark.json";

        benchmark.start("T1");
        sleep(10);
        benchmark.end("T1");

        benchmark.export(fileName, TimeFormat.NANOSSEGUNDOS);

        File file = new File(fileName);
        assertTrue(file.exists(), "9 - export() devera ser valido caso possua valores validos");
        file.delete(); // Remove o arquivo após o teste
    }
}
