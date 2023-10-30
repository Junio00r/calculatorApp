package com.devmobile.android.calculadora.model;

import java.util.Locale;

public class RefactorExpression {
    public static String LAST_INPUT_CHAR;

    public static String getRefactoredInputExpression(String expressionNoRefactor) {

        return formattedExpression(expressionNoRefactor);
    }

    public static String formattedExpression(String expressionForRefactor) {

        expressionForRefactor = expressionForRefactor.replace("×", "*");
        expressionForRefactor = expressionForRefactor.replace("÷", "/");
        expressionForRefactor = expressionForRefactor.replace("x!", "!");
        expressionForRefactor = expressionForRefactor.replace("π", String.valueOf(Math.PI));
        expressionForRefactor = expressionForRefactor.replace("pi", String.valueOf(Math.PI));
        expressionForRefactor = expressionForRefactor.replace("e", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("euler", String.valueOf(Math.E));

        if (String.valueOf(expressionForRefactor.charAt(expressionForRefactor.length() - 1)).equals("%")) {

            switch (LAST_INPUT_CHAR) {
                case "+":
                case "-":
                    expressionForRefactor = expressionForRefactor.replace(LAST_INPUT_CHAR, LAST_INPUT_CHAR + "(" );
                    expressionForRefactor = expressionForRefactor.replace("%", "*10/100)" );
                    break;
                case "÷":
                case "/":
                case "×":
                case "*":
                    expressionForRefactor = expressionForRefactor.replace(LAST_INPUT_CHAR, LAST_INPUT_CHAR + "(" );
                    expressionForRefactor = expressionForRefactor.replace("%", "/100" );
                    break;
            }
        }

        if (isEUA()) {
            expressionForRefactor = expressionForRefactor.replace(",", ".");
        } else {
            expressionForRefactor = expressionForRefactor.replace(".", ",");
        }

        return expressionForRefactor;
    }

    public static String formattedFloatingResult(Object resultExpression) {
        final double resultExpressionConvertedFloating = (double) resultExpression;

        if (resultExpressionConvertedFloating == (int) resultExpressionConvertedFloating) {
            return String.valueOf((int) resultExpressionConvertedFloating);
        }

        return String.valueOf(resultExpressionConvertedFloating);
    }

    public static boolean isEUA() {

        return Locale.getDefault().getCountry().equals("US");
    }
}
