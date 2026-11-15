package provider;

import model.TableAnalysis;
import model.UserInfo;
import model.exception.InvalidParameterException;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe para ordenação MergeSort
 * @see model.TableAnalysis
 */
public class MergeSortAnalysis implements TableAnalysis<List<UserInfo>> {

    /**
     * Realiza uma ordenação MergeSort
     * @param userInfoList Lista de dados a ser analisada
     * @return Lista ordenada
     * @throws InvalidParameterException Caso a lista seja vazia
     */
    @Override
    public List<UserInfo> analysis(List<UserInfo> userInfoList) {
        if (userInfoList.isEmpty())
            throw new InvalidParameterException("'userInfoList' é vazio");
        return mergeSort(userInfoList);
    }

    /**
     * Inicia o MergeSort
     * @param array Lista a ser ordenada
     * @return Lista ordenada
     */
    private List<UserInfo> mergeSort(List<UserInfo> array) {
        if (array.size() <= 1) return array;

        int meio = array.size() / 2;
        List<UserInfo> esquerda = this.mergeSort(array.subList(0, meio));
        List<UserInfo> direita = this.mergeSort(array.subList(meio, array.size()));

        return this.merge(esquerda, direita);
    }

    /**
     * Método responsável por unir os vetores
     * @param esquerda Lista da esquerda
     * @param direita Lista da direita
     * @return Lista unificada
     */
    private List<UserInfo> merge(List<UserInfo> esquerda, List<UserInfo> direita) {
        List<UserInfo> response = new ArrayList<>();
        int indexEsquerda = 0;
        int indexDireita = 0;

        while (indexEsquerda < esquerda.size() && indexDireita < direita.size()) {
            if (esquerda.get(indexEsquerda).getCredit() > direita.get(indexDireita).getCredit()) {
                response.add(esquerda.get(indexEsquerda));
                indexEsquerda++;
            } else {
                response.add(direita.get(indexDireita));
                indexDireita++;
            }
        }

        response.addAll(esquerda.subList(indexEsquerda, esquerda.size()));
        response.addAll(direita.subList(indexDireita, direita.size()));

        return response;
    }
}

