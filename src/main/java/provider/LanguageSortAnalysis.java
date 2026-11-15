package provider;

import model.TableAnalysis;
import model.UserInfo;
import model.exception.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe para analise de dados, obtem um usuario aleatoriamente
 * @see TableAnalysis
 */
public class LanguageSortAnalysis implements TableAnalysis<List<UserInfo>> {

    /**
     * Realiza uma analise aleatoria dos dados
     * @param userInfoList Lista de dados a ser analisada
     * @return Elemento aleatorio da lista
     * @see TableAnalysis
     */
    @Override
    public List<UserInfo> analysis(List<UserInfo> userInfoList) {
        if(userInfoList == null || userInfoList.isEmpty())
            throw new InvalidParameterException("'userInfoList' é null ou vazio");

        List<UserInfo> sorted = new ArrayList<>(userInfoList);
        sorted.sort((a, b) -> Double.compare(b.getCredit(), a.getCredit()));
        return sorted;
    }
}
