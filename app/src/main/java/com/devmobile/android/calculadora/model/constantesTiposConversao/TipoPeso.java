package com.devmobile.android.calculadora.model.constantesTiposConversao;

public enum TipoPeso {

    MILIGRAMAS(1),
    GRAMAS(2),
    QUILOGRAMAS(3),
    TONELADAS(4),
    LIBRAS(5);

    private int tipoPeso;

    TipoPeso(int tipoPeso) {
        this.tipoPeso = tipoPeso;
    }

    public int getTipoPeso() {
        return tipoPeso;
    }
}
