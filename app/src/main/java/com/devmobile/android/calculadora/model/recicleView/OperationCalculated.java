package com.devmobile.android.calculadora.model.recicleView;

public class OperationCalculated {
    private final String expression;
    private final String resultExpression;

    public OperationCalculated(String expression, String resultExpression) {
        this.expression = expression;
        this.resultExpression = resultExpression;
    }

    public String getExpression() {
        return expression;
    }

    public String getResultExpression() {
        return resultExpression;
    }
}
