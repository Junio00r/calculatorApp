package com.devmobile.android.calculadora.model.calculadora;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.OperadorMatematico;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.EntradaDados;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

import java.util.LinkedList;


public class CalculadoraPadrao implements CopiarOperacao, ExcluirDados, EntradaDados {

    private Numero numero;
    private OperadorMatematico operadorMatematico;
    private Separador separador;
    private LinkedList<String> historico;


    public CalculadoraPadrao() {
    }

    @Override
    public void copiarOperacao() {

    }

    @Override
    public void excluirCaractere() {

    }

    @Override
    public void excluirTodaEntrada() {

    }

    @Override
    public void inserirEntrada(String entrada) {

    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }
}
