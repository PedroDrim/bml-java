import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.*;
import model.exception.InvalidParameterException;
import provider.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Classe principal responsavel por executar as analises e o benchmark
 */
public class Main {

    /**
     * Ponto de entrada da aplicacao
     * @param args Argumentos iniciais, sendo o primeiro o caminho do arquivo de configuracao
     * @throws IOException Caso ocorra erro na leitura dos arquivos
     * @throws InvalidParameterException Caso nenhum argumento seja informado
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) throw new InvalidParameterException("Parametros iniciais nao encontrados");
        String configFile = args[0];
        CustomConfig properties = loadProperties(configFile);

        String[] inputList = properties.getINPUT_FILENAME_LIST();
        String output = properties.getOUTPUT_FILENAME();

        BenchmarkOutput benchmark = new BenchmarkMeasure();
        TableAnalysis<List<UserInfo>> mergeSortAnalysis = new MergeSortAnalysis();
        TableAnalysis<List<UserInfo>> quickSortAnalysis = new QuickSortAnalysis();
        TableAnalysis<List<UserInfo>> languageSortAnalysis = new LanguageSortAnalysis();
        TableAnalysis<double[]> summaryAnalysis = new SummaryAnalysis();

        for (int index = 0; index < inputList.length; index++) {
            String input = inputList[index];
            System.out.println("[START] Arquivo: " + index);
            //==================================================
            // Leitura dos dados
            System.out.println("\t[LOG] Read");
            benchmark.start("Read@" + index);
            TableReader tableReader = new TableReader(input);
            List<UserInfo> userInfoList = tableReader.read();
            benchmark.end("Read@" + index);
            //==================================================
            // Analise dos dados (Summary)
            System.out.println("\t[LOG] Summary");
            benchmark.start("SummaryAnalysis@" + index);
            double[] summary = summaryAnalysis.analysis(userInfoList);
            benchmark.end("SummaryAnalysis@" + index);
            //==================================================
            // Analise dos dados (Merge)
            System.out.println("\t[LOG] Merge");
            benchmark.start("MergeAnalysis@" + index);
            List<UserInfo> mergeValue = mergeSortAnalysis.analysis(userInfoList);
            benchmark.end("MergeAnalysis@" + index);
            //==================================================
            // Analise dos dados (Quick)
            System.out.println("\t[LOG] Quick");
            benchmark.start("QuickAnalyse@" + index);
            List<UserInfo> quickValue = quickSortAnalysis.analysis(userInfoList);
            benchmark.end("QuickAnalyse@" + index);
            //==================================================
            // Analise dos dados (Language)
            System.out.println("\t[LOG] Language");
            benchmark.start("LanguageAnalyse@" + index);
            List<UserInfo> languageValue = languageSortAnalysis.analysis(userInfoList);
            benchmark.end("LanguageAnalyse@" + index);
            //==================================================
            System.out.println("[END] Arquivo: " + index);
        }

        benchmark.export(output, TimeFormat.MILISSEGUNDOS);
    }

    /**
     * Carrega as configuracoes a partir de um arquivo json
     * @param configFile Caminho do arquivo de configuracao
     * @return Configuracoes carregadas
     * @throws IOException Caso ocorra erro na leitura do arquivo
     */
    public static CustomConfig loadProperties(String configFile) throws IOException {
        String jsonString = Files.readString(Path.of(configFile), StandardCharsets.UTF_8);
        CustomConfig customConfig = parseJsonToConfig(jsonString);
        return customConfig;
    }

    /**
     * Converte o conteudo json em um objeto de configuracao
     * (usa a arvore do Gson, sem reflexao, para funcionar tambem em native image)
     * @param json Conteudo json a ser interpretado
     * @return Configuracoes extraidas do json
     * @throws InvalidParameterException Caso alguma chave obrigatoria nao exista no json
     */
    public static CustomConfig parseJsonToConfig(String json) {
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

        if (!root.has("INPUT_FILENAME_LIST"))
            throw new InvalidParameterException("'INPUT_FILENAME_LIST' nao encontrado na configuracao");
        if (!root.has("OUTPUT_FILENAME"))
            throw new InvalidParameterException("'OUTPUT_FILENAME' nao encontrado na configuracao");

        JsonArray inputArray = root.getAsJsonArray("INPUT_FILENAME_LIST");
        String[] inputFileNameList = new String[inputArray.size()];
        for (int i = 0; i < inputFileNameList.length; i++) {
            inputFileNameList[i] = inputArray.get(i).getAsString();
        }

        String outputFileName = root.get("OUTPUT_FILENAME").getAsString();

        return new CustomConfig(inputFileNameList, outputFileName);
    }
}