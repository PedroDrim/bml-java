package provider;

import model.TableAnalysis;
import model.UserInfo;
import model.exception.InvalidParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para ordenacao quickSort
 * @see TableAnalysis
 */
public class QuickSortAnalysis implements TableAnalysis<List<UserInfo>> {

    /**
     * Realiza uma analise ordenacao quickSort
     * @param userInfoList Lista de dados a ser analisada
     * @return Elemento aleatorio da lista
     * @see TableAnalysis
     */
    @Override
    public List<UserInfo> analysis(List<UserInfo> userInfoList) {
        if(userInfoList == null || userInfoList.isEmpty())
            throw new InvalidParameterException("'userInfoList' é null ou vazio");

        return this.quickSort(userInfoList);
    }

    /**
     * Iniciando quickSort
     * @param array Vetor a ser ordenado
     */
    private List<UserInfo> quickSort(List<UserInfo> array) {
        if(array.size() <= 1) return array;

        // Obtendo posição central
        UserInfo pivot = array.get(array.size() / 2);

        // Separando vetores
        List<UserInfo> menores = array.stream().filter(instance -> instance.getCredit() < pivot.getCredit()).toList();
        List<UserInfo> iguais = array.stream().filter(instance -> instance.getCredit() == pivot.getCredit()).toList();
        List<UserInfo> maiores = array.stream().filter(instance -> instance.getCredit() > pivot.getCredit()).toList();

        List<UserInfo> response = new ArrayList<>();
        response.addAll(this.quickSort(maiores));
        response.addAll(iguais);
        response.addAll(this.quickSort(menores));
        return response;
    }

}
