package com.devmobile.android.calculadora.model.constantesTiposConversao.pressao;

public enum TipoPressao {
    ATMOSFERAS(1),
    QUILOPASCAL(2),
    MILIMETROS_MERCURIO(3),
    PASCALS(4),
    LIBRAS_POR_POLEGADA_QUADRADA(5);

    private int tipoPressao;

    TipoPressao(int tipoPressao) {
        this.tipoPressao = tipoPressao;
    }

    public int getTipoPressao() {
        return tipoPressao;
    }
}
