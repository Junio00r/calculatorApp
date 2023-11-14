package com.devmobile.android.calculadora.model.constantesTiposConversao;

public enum TipoVelocidade {
    QUILOMETROS_HORA(1),
    MILHAS_HORA(2),
    METROS_SEGUNDO(3);

    private int tipoVelocidade;

    TipoVelocidade(int tipoVelocidade) {
        this.tipoVelocidade = tipoVelocidade;
    }

    public int getTipoVelocidade() {
        return tipoVelocidade;
    }
}
