package com.devmobile.android.calculadora.model.constanteCalculadora;

public enum ConstanteMatematica{
    EULER('e'),
    RAIZQUADRADA('√'),
    PI('π');

    private final char constanteMatematica;
    ConstanteMatematica(char constanteMatematica) {
        this.constanteMatematica = constanteMatematica;
    }

    public char getConstanteMatematica() {
        return constanteMatematica;
    }

}
