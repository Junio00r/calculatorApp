package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constantesTiposConversao.potencia.TipoPotencia;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorPotencia implements CopiarOperacao, ExcluirDados {

    private double valorParaConverter;
    private double valorConvertido;
    private Numero numero;
    private TipoPotencia tipoPotencia;

    public ConversorPotencia(Numero numero) {
        this.numero = numero;
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

    public double getValorParaConverter() {
        return valorParaConverter;
    }

    public void setValorParaConverter(double valorParaConverter) {
        this.valorParaConverter = valorParaConverter;
    }

    public double getValorConvertido() {
        return valorConvertido;
    }

    public void setValorConvertido(double valorConvertido) {
        this.valorConvertido = valorConvertido;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

}

