package com.devmobile.android.calculadora.model.constanteCalculadora;

public enum OperadorMatematico {
    ADICAO('+'),
    SUBTRACAO('-'),
    MULTIPLICACAO('*'),
    DIVISAO('/'),
    PORCENTAGEM('%'),
    POTENCIACAO('^'),
    IGUAL('=');

    private final char operadorMatematico;

    OperadorMatematico(char operadorMatematico) {
        this.operadorMatematico = operadorMatematico;
    }

    public char getOperadorMatematico() {
        return operadorMatematico;
    }
}
