package com.devmobile.android.calculadora.model;

import androidx.annotation.NonNull;
import com.devmobile.android.calculadora.Country;
import java.text.DecimalFormat;
import java.text.ParseException;

public class RefactorExpression {
    public static String LAST_INPUT_CHAR;

    @NonNull
    public static String getRefactoredInputExpression(String expressionNoRefactor) {

        return getFormattedExpression(expressionNoRefactor);
    }

    @NonNull
    public static String getFormattedExpression(String expressionForRefactor) {

        expressionForRefactor = parseToDecimalFormat(expressionForRefactor);
        expressionForRefactor = expressionForRefactor.replace("×", "*");
        expressionForRefactor = expressionForRefactor.replace("÷", "/");
        expressionForRefactor = expressionForRefactor.replace("x!", "!");
        expressionForRefactor = expressionForRefactor.replace("e", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("E", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("euler", String.valueOf(Math.E));
        expressionForRefactor = expressionForRefactor.replace("π", String.valueOf(Math.PI));
        expressionForRefactor = expressionForRefactor.replace("pi", String.valueOf(Math.PI));

        if (String.valueOf(expressionForRefactor.charAt(expressionForRefactor.length() - 1)).equals("%")) {

            switch (LAST_INPUT_CHAR) {
                case "+":
                case "-":
                    expressionForRefactor = expressionForRefactor.replace("%", "*10/100");
                    break;
                case "÷":
                case "/":
                case "×":
                case "*":
                    expressionForRefactor = expressionForRefactor.replace(LAST_INPUT_CHAR, LAST_INPUT_CHAR + "(");
                    expressionForRefactor = expressionForRefactor.replace("%", "/100");
                    break;
            }

        }

        return expressionForRefactor;
    }

    public static String getDecimalFormatted(Object obj) {

        return Country.getDecimalFormatOfCountry().format(obj);
    }

    @NonNull
    public static String parseToDecimalFormat(@NonNull String value) {

        char lastChar = value.charAt(value.length() - 1);

        if (Country.getDecimalSymbolSeparator().equals(".")) {

            value = value.replace(",", "");
        } else {

            if (!(lastChar == ',' || lastChar == '.')) {

                value = value.replace(".", "").replaceAll(",", ".");
            }

        }

        return value;
    }
}
