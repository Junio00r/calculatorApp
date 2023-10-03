package com.devmobile.android.calculadora.model.interfaces;

import java.util.LinkedList;

public interface Historico {

    LinkedList<String> historicoGeral(LinkedList<String> mostraTodoHistoricoDaSecao);
    LinkedList<String> historicoPorSelecao(LinkedList<String> retrocedeUltimoCalculoFeito);
}