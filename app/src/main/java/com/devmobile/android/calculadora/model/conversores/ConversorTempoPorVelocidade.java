package com.devmobile.android.calculadora.model.conversores;

import com.devmobile.android.calculadora.model.constanteCalculadora.Numero;
import com.devmobile.android.calculadora.model.constanteCalculadora.Separador;
import com.devmobile.android.calculadora.model.constantesTiposConversao.tempoPorVelocidade.TipoVelocidade;
import com.devmobile.android.calculadora.model.interfaces.CopiarOperacao;
import com.devmobile.android.calculadora.model.interfaces.ExcluirDados;

public class ConversorTempoPorVelocidade implements CopiarOperacao, ExcluirDados {

    private double velocidade;
    private double distancia;
    private double tempoConvertido;
    private Numero numero;
    private Separador separador;
    private TipoVelocidade tipoVelocidade;

    public ConversorTempoPorVelocidade(Numero numero, Separador separador) {
        this.numero = numero;
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

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTempoConvertido() {
        return tempoConvertido;
    }

    public Separador getSeparador() {
        return separador;
    }
}
