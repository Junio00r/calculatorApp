package com.devmobile.android.calculadora.model.constanteCalculadora;

public enum Numero {
    ZERO(0),
    UM(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5),
    SEIS(6),
    SETE(7),
    OITO(8),
    NOVE(9);

    private final int numeroDigitado;

    Numero(int numero) {
        this.numeroDigitado = numero;
    }

    public int getNumeroDigitado() {
        return numeroDigitado;
    }
}