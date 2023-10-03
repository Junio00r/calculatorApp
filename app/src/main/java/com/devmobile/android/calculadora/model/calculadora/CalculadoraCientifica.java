package com.devmobile.android.calculadora.model.calculadora;

import com.devmobile.android.calculadora.model.constanteCalculadora.*;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class CalculadoraCientifica implements CopiarOperacao, ExcluirDados {

    private Numero numero;
    private Separador separador;
    private OperadorMatematico operadorMatematico;
    private FuncaoMatematica funcaoMatematica;
    private ConstanteMatematica constanteMatematica;

    public CalculadoraCientifica(Numero numero, Separador separador, OperadorMatematico operadorMatematico, FuncaoMatematica funcaoMatematica, ConstanteMatematica constanteMatematica) {
        this.numero = numero;
        this.separador = separador;
        this.operadorMatematico = operadorMatematico;
        this.funcaoMatematica = funcaoMatematica;
        this.constanteMatematica = constanteMatematica;
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

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    public Separador getSeparador() {
        return separador;
    }


    public OperadorMatematico getOperadorMatematico() {
        return operadorMatematico;
    }

    public void setOperadorMatematico(OperadorMatematico operadorMatematico) {
        this.operadorMatematico = operadorMatematico;
    }

    public FuncaoMatematica getFuncaoMatematica() {
        return funcaoMatematica;
    }

    public void setFuncaoMatematica(FuncaoMatematica funcaoMatematica) {
        this.funcaoMatematica = funcaoMatematica;
    }

    public ConstanteMatematica getConstanteMatematica() {
        return constanteMatematica;
    }

    public void setConstanteMatematica(ConstanteMatematica constanteMatematica) {
        this.constanteMatematica = constanteMatematica;
    }
}