package com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento;

public enum TipoComprimento {
    NANOMETROS(1),
    MILIMETROS(2),
    CENTIMETROS(3),
    METROS(4),
    QUILOMETROS(5),
    POLEGADAS(6),
    PES(7),
    JARDAS(8),
    MILHAS(9);

    private int tipoComprimento;

    TipoComprimento(int tipoComprimento) {
        this.tipoComprimento = tipoComprimento;
    }

    public int getTipoComprimento() {
        return tipoComprimento;
    }
}