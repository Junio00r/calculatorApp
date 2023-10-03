package com.devmobile.android.calculadora.model.constanteCalculadora;

public enum Separador {

    PONTO('.'),
    VIRGULA(',');

    private final char operadorSeparacao;

    Separador(char operadorSeparacao) {
        this.operadorSeparacao = operadorSeparacao;
    }

    public char getOperadorSeparacao() {
        return operadorSeparacao;
    }
}
