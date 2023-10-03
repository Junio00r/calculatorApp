package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.constantesTiposConversao.peso.TipoPeso;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorPeso implements CopiarOperacao, ExcluirDados {

    private Double pesoParaConverter;
    private Double pesoConvertido;
    private Numero numero;
    private TipoPeso tipoPeso;

    public ConversorPeso(Numero numero) {
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

    public Double getPesoParaConverter() {
        return pesoParaConverter;
    }

    public void setPesoParaConverter(Double pesoParaConverter) {
        this.pesoParaConverter = pesoParaConverter;
    }

    public Double getPesoConvertido() {
        return pesoConvertido;
    }

}
