package com.devmobile.android.calculadora.model;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import java.util.Locale;

public class ResultTextView {
    private static String LAST_INPUT_CHAR;

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

    private static String getRefactoredInputExpression(String expressionNoRefactor) {
        String expressionRefactoreted = formattedExpression(expressionNoRefactor);

        return expressionRefactoreted;
    }

    private static String formattedExpression(String expressionForRefactor) {

        expressionForRefactor = replaceCharacter(expressionForRefactor,"×", "*");
        expressionForRefactor = replaceCharacter(expressionForRefactor,"÷", "/");
        expressionForRefactor = replaceCharacter(expressionForRefactor,"π", String.valueOf(Math.PI));
        expressionForRefactor = replaceCharacter(expressionForRefactor,"e", String.valueOf(Math.E));

        if (String.valueOf(expressionForRefactor.charAt(expressionForRefactor.length() - 1)).equals("%")) {

            switch (LAST_INPUT_CHAR) {
                case "+":
                case "-":
                    expressionForRefactor = replaceCharacter(expressionForRefactor, LAST_INPUT_CHAR, LAST_INPUT_CHAR + "(" );
                    expressionForRefactor = replaceCharacter(expressionForRefactor,"%", "*10/100)" );
                    break;
                case "÷":
                case "/":
                case "×":
                case "*":
                    expressionForRefactor = replaceCharacter(expressionForRefactor, LAST_INPUT_CHAR, LAST_INPUT_CHAR + "(" );
                    expressionForRefactor = replaceCharacter(expressionForRefactor,"%", "/100" );
                    break;
            }

        }

        if (isEUA()) {
            expressionForRefactor = replaceCharacter(expressionForRefactor,",", ".");
        } else {
            expressionForRefactor = replaceCharacter(expressionForRefactor,".", ",");
        }

        return expressionForRefactor;
    }

    private static String formattedFloatingResult(Object resultExpression) {
        final double resultExpressionConvertedFloating = (double) resultExpression;

        if (resultExpressionConvertedFloating == (int) resultExpressionConvertedFloating) {
            return String.valueOf((int) resultExpressionConvertedFloating);
        }

        return String.valueOf(resultExpressionConvertedFloating);
    }

    public static void lastEspecialCharacter(String character) {

        LAST_INPUT_CHAR = character;
    }

    public static String replaceCharacter(String expression, String oldString, String newString) {

        return expression.replace(oldString, newString);
    }

    public static boolean isEUA() {
        
        return Locale.getDefault().getCountry().equals("US");
    }
}
