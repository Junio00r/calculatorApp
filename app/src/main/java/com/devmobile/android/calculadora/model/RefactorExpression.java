package com.devmobile.android.calculadora.model;

import androidx.annotation.NonNull;
import java.text.DecimalFormat;
import java.util.Locale;

public class RefactorExpression {
    public static String LAST_INPUT_CHAR;
    private static DecimalFormat decimalFormat = null;
    @NonNull
    public static String getRefactoredInputExpression(String expressionNoRefactor) {

        return getFormattedExpression(expressionNoRefactor);
    }

    @NonNull
    public static String getFormattedExpression(String expressionForRefactor) {

        expressionForRefactor = expressionForRefactor.replace("×", "*");
        expressionForRefactor = expressionForRefactor.replace("÷", "/");
        expressionForRefactor = expressionForRefactor.replace("x!", "!");
        expressionForRefactor = expressionForRefactor.replace("e", "*10^");
        expressionForRefactor = expressionForRefactor.replace("E", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("euler", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("π", String.valueOf(Math.PI));
        expressionForRefactor = expressionForRefactor.replace("pi", String.valueOf(Math.PI));

        if (String.valueOf(expressionForRefactor.charAt(expressionForRefactor.length() - 1)).equals("%")) {

            switch (LAST_INPUT_CHAR) {
                case "+":
                case "-":
                    expressionForRefactor = expressionForRefactor.replace("%", "*10/100" );
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

        return expressionForRefactor;
    }

    public static String getDecimalFormatted(Object obj) {

        if (decimalFormat == null) {
            if (isEUA()) {
                decimalFormat = new DecimalFormat("#,###.#####");
            } else {
                decimalFormat = new DecimalFormat("#.###,#####");
            }
        }

        return decimalFormat.format(obj);
    }

    public static boolean isEUA() {

        return Locale.getDefault().getCountry().equals("US");
    }
}
