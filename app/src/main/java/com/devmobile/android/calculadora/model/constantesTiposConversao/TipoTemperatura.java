package com.devmobile.android.calculadora.model.constantesTiposConversao;

public enum TipoTemperatura {
    CELSIUS(1),
    FAHRENHEIT(2),
    KELVIN(3);

    private int tipoTemperatura;

    TipoTemperatura(int tipoTemperatura) {
        this.tipoTemperatura = tipoTemperatura;
    }

    public int getTipoTemperatura() {
        return tipoTemperatura;
    }
}