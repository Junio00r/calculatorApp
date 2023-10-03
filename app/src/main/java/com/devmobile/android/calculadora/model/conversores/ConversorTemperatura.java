package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.constantesTiposConversao.temperatura.TipoTemperatura;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorTemperatura implements CopiarOperacao, ExcluirDados {

    private Double temperaturaParaConverter;
    private Double temperaturaConvertida;
    private Numero numeros;
    private Separador separador;
    private TipoTemperatura tipoTemperatura;

    public ConversorTemperatura(Numero numeros, Separador separador) {
        this.numeros = numeros;
        this.separador = separador;
    }

    @Override
    public void copiarOperacao() {

    }

    @Override
    public void excluirCaractere() {

    }

    @Override
    public void excluirTodaEntrada() {

    }

    public Double getTemperaturaParaConverter() {
        return temperaturaParaConverter;
    }

    public void setTemperaturaParaConverter(Double temperaturaParaConverter) {
        this.temperaturaParaConverter = temperaturaParaConverter;
    }

    public Double getTemperaturaConvertida() {
        return temperaturaConvertida;
    }

    public Separador getSeparador() {
        return separador;
    }

}

