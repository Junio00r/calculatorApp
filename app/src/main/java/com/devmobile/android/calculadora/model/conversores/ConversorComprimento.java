package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorComprimento implements CopiarOperacao, ExcluirDados{

    private Double valorParaConverter;
    private Double valorConvertido;
    private Numero numeros;
    private Separador separador;
    private TipoComprimento tipoComprimento;

    public ConversorComprimento(Numero numeros, Separador separador) {
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

    public double getValorParaConverter() {
        return valorParaConverter;
    }

    public void setValorParaConverter(double valorParaConverter) {
        this.valorParaConverter = valorParaConverter;
    }

    public double getValorConvertido() {
        return valorConvertido;
    }

    public Separador getSeparador() {
        return separador;
    }
}
