package com.devmobile.android.calculadora.model.constanteCalculadora;

public enum FuncaoMatematica {
    SENO("sen"),
    COSSENO("cos"),
    TANGENTE("tang"),
    LOGARITMO("log"),
    RADIANO("rad"),
    FATORIAL("x!");

    private final String funcaoMatematica;

    FuncaoMatematica(String funcaoMatematica) {
        this.funcaoMatematica = funcaoMatematica;
    }

    public String getFuncaoMatematica() {
        return funcaoMatematica;
    }
}
