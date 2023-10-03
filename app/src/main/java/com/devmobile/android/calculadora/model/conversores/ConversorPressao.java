package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.constantesTiposConversao.pressao.TipoPressao;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorPressao implements CopiarOperacao, ExcluirDados {

    private double presaoParaConverter;
    private double pressaoConvertida;
    private Numero numero;
    private TipoPressao tipoPressao;

    public ConversorPressao(Numero numero) {
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

    public double getPresaoParaConverter() {
        return presaoParaConverter;
    }

    public void setPresaoParaConverter(double presaoParaConverter) {
        this.presaoParaConverter = presaoParaConverter;
    }

    public double getPressaoConvertida() {
        return pressaoConvertida;
    }

}
