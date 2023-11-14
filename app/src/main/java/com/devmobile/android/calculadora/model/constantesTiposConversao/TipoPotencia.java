package com.devmobile.android.calculadora.model.constantesTiposConversao;

public enum TipoPotencia {
    WATTS(1),
    QUILOWATTS(2),
    CAVALOS_VAPOR(3),
    LIBRAS_MINUTO(4);

    private int tipoPotencia;

    TipoPotencia(int tipoPotencia) {
        this.tipoPotencia = tipoPotencia;
    }

    public int getTipoPotencia() {
        return tipoPotencia;
    }
}