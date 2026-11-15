import model.*;
import model.exception.InvalidParameterException;
import provider.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

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
        TableAnalysis<Double[]> summaryAnalysis = new SummaryAnalysis();

        for (int index = 0; index < inputList.length; index++) {
            String input = inputList[index];
            System.out.println("[START] Arquivo: " + index);
            //==================================================
            // Leitura dos dados
            System.out.println("\t[LOG] Read");
            benchmark.start("Read@" + index);
            TableReader tableReader = new TableReader(input);
            tableReader.open();
            List<UserInfo> userInfoList = tableReader.read();
            benchmark.end("Read@" + index);
            //==================================================
            // Analise dos dados (Summary)
            System.out.println("\t[LOG] Summary");
            benchmark.start("SummaryAnalysis@" + index);
            Double[] summary = summaryAnalysis.analysis(userInfoList);
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

    public static CustomConfig loadProperties(String configFile) throws IOException {
        String jsonString = Files.readString(Path.of(configFile), StandardCharsets.UTF_8);
        CustomConfig customConfig = parseJsonToConfig(jsonString);
        return customConfig;
    }

    public static CustomConfig parseJsonToConfig(String json) {
        // Remove espaços extras, quebras de linha e mantém o formato correto
        String cleanedJson = json.replace("\n", "").replace("\r", "").replace(" ", "");

        // Extração manual dos valores
        Pattern inputListRegex = Pattern.compile("\"INPUT_FILENAME_LIST\":\\[(.*?)\\]");
        Pattern outputFileRegex = Pattern.compile("\"OUTPUT_FILENAME\":\"(.*?)\"");

        // Encontrando correspondências
        Matcher inputListMatch = inputListRegex.matcher(cleanedJson);
        Matcher outputFileMatch = outputFileRegex.matcher(cleanedJson);

        // Convertendo INPUT_FILENAME_LIST para String[]
        String[] inputFileNameList = new String[0];
        if (inputListMatch.find()) {
            String[] parts = inputListMatch.group(1).split(",", -1); // Divide pelos elementos da lista
            inputFileNameList = new String[parts.length];
            for (int i = 0; i < parts.length; i++) {
                inputFileNameList[i] = removeSurrounding(parts[i].trim(), "\""); // Remove espaços e aspas
            }
        }

        // Extraindo OUTPUT_FILENAME
        String outputFileName = outputFileMatch.find() ? outputFileMatch.group(1) : "";

        return new CustomConfig(inputFileNameList, outputFileName);
    }

    private static String removeSurrounding(String value, String delimiter) {
        if (value.length() >= 2 * delimiter.length()
                && value.startsWith(delimiter) && value.endsWith(delimiter)) {
            return value.substring(delimiter.length(), value.length() - delimiter.length());
        }
        return value;
    }
}

