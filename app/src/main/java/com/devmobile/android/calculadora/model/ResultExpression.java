package com.devmobile.android.calculadora.model;

import static com.devmobile.android.calculadora.model.RefactorExpression.formattedFloatingResult;
import static com.devmobile.android.calculadora.model.RefactorExpression.getRefactoredInputExpression;
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

public class ResultExpression {

    public static String calculateResultOperation(String inputExpression) {
        final Jep jep = new Jep();
        final String inputExpressionRefactored = getRefactoredInputExpression(inputExpression);

        try {

            jep.parse(inputExpressionRefactored);
            Object resultExpression = jep.evaluate();

            return formattedFloatingResult(resultExpression);

        } catch (JepException e) {
            return "Invalid Expression!";
        }
    }
}
