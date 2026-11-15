package model;

/**
 * Classe de dados responsavel por abstrair o json de configuracao
 */
public class CustomConfig {

    /**
     * Lista com os nomes dos arquivos de entrada
     */
    private final String[] INPUT_FILENAME_LIST;

    /**
     * Nome do arquivo de saida
     */
    private final String OUTPUT_FILENAME;

    /**
     * Construtor público da classe
     * @param INPUT_FILENAME_LIST Lista com os nomes dos arquivos de entrada
     * @param OUTPUT_FILENAME Nome do arquivo de saida
     */
    public CustomConfig(String[] INPUT_FILENAME_LIST, String OUTPUT_FILENAME) {
        this.INPUT_FILENAME_LIST = INPUT_FILENAME_LIST;
        this.OUTPUT_FILENAME = OUTPUT_FILENAME;
    }

    /**
     * Obtem a lista com os nomes dos arquivos de entrada
     * @return Lista com os nomes dos arquivos de entrada
     */
    public String[] getINPUT_FILENAME_LIST() {
        return INPUT_FILENAME_LIST;
    }

    /**
     * Obtem o nome do arquivo de saida
     * @return Nome do arquivo de saida
     */
    public String getOUTPUT_FILENAME() {
        return OUTPUT_FILENAME;
    }
}