package com.devmobile.android.calculadora.model;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

public class ResultExpression {

    public static String calculateResultOperation(String inputExpression) throws JepException {
        final Jep jep = new Jep();
        final String inputExpressionRefactored = RefactorExpression.getRefactoredInputExpression(inputExpression);

        jep.parse(inputExpressionRefactored);
        Object resultExpression = jep.evaluate();

        return RefactorExpression.getDecimalFormatted(resultExpression);
    }
}
