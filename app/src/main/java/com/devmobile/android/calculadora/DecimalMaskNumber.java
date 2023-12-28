package com.devmobile.android.calculadora;

import androidx.annotation.NonNull;
import com.devmobile.android.calculadora.model.RefactorExpression;
import java.math.BigDecimal;
import java.util.Arrays;

public abstract class DecimalMaskNumber {

    @NonNull
    public static String setMask(@NonNull String input) throws NumberFormatException {
        String numberNoSymbols;
        String symbolsNoNumbers;
        String[] conjunctOfNumbersWithMask;
        String expressionReformatted = input;

        if (input.length() > 3) {
            numberNoSymbols = input.replaceAll("[^0-9,.]", " ");
            symbolsNoNumbers = input.replaceAll("[0-9.,]", "");

            String[] conjunctOfNumbers = splitConjutoOfNumbers(numberNoSymbols, " ");
            String[] conjunctOfSymbols = splitConjutoOfSymbols(symbolsNoNumbers, "");

            conjunctOfNumbersWithMask = addNumMaskInConjunctOfNumbers(conjunctOfNumbers);
            expressionReformatted = restoreExpressionWithMask(conjunctOfNumbersWithMask, conjunctOfSymbols);

        }

        return expressionReformatted;
    }

    /**
     * @param conjuntOfNumbersNoMask conjucts of numbers with mask
     * @return conjunct of numbers with mask.
     */
    @NonNull
    private static String[] addNumMaskInConjunctOfNumbers(@NonNull String[] conjuntOfNumbersNoMask)
            throws NumberFormatException {
        String[] conjuntOfNumbersWithMask = new String[conjuntOfNumbersNoMask.length];

        for (int i = 0; i < conjuntOfNumbersNoMask.length; i++) {

            String numberToDecimalFormat = RefactorExpression.parseToDecimalFormat(conjuntOfNumbersNoMask[i]);
            BigDecimal mask = new BigDecimal(numberToDecimalFormat);

            String numberMask = Country.getDecimalFormatOfCountry().format(mask);
            conjuntOfNumbersWithMask[i] = numberMask;

        }

        return conjuntOfNumbersWithMask;
    }

    /**
     * @param conjunctoOfNumbersWithMask are numbers that been separados em numbers group
     * @param conjuntOfSymbols           are as sybols such +-* e etc
     * @return expression reformultated of same way when the user insert, but, this time the numbers
     * have mask.
     */
    @NonNull
    private static String restoreExpressionWithMask(@NonNull String[] conjunctoOfNumbersWithMask,
                                                    @NonNull String[] conjuntOfSymbols) {
        StringBuilder expressionReformalated = new StringBuilder();
        System.out.println(conjuntOfSymbols.length);

        for (int i = 0; (i <= conjunctoOfNumbersWithMask.length) || (i <= conjuntOfSymbols.length); i++) {

            if (i < conjunctoOfNumbersWithMask.length) {

                expressionReformalated.append(conjunctoOfNumbersWithMask[i]);
            }

            if (i < conjuntOfSymbols.length) {

                expressionReformalated.append(conjuntOfSymbols[i]);
            }
        }


        return expressionReformalated.toString();
    }

    /**
     * @param stringToSplit string to separate the numbers
     * @param split         character that will be use to separates
     * @return a array conjunct of numbers on the expression.
     */
    @NonNull
    private static String[] splitConjutoOfNumbers(@NonNull String stringToSplit, String split) {

        return stringToSplit.split(split);
    }

    /**
     * @param stringToSplit string to separate the symbols
     * @param split         character that will be use to separates
     * @return a array of mathematical symbols on the expression.
     */
    @NonNull
    private static String[] splitConjutoOfSymbols(@NonNull String stringToSplit, String split) {

        return Arrays.stream(stringToSplit.split(split))
                .filter(s -> !s.equals(split))
                .toArray(String[]::new);
    }

    @NonNull
    private static String splitNumbersAndSinaisExpression(@NonNull String string) {

        return string.replaceAll("[0-9]", "");
    }

    private static boolean isSeparator(@NonNull String possibleSeparator) {

        return possibleSeparator.equals(",");
    }

    private static boolean isNumber(@NonNull String possibleNumber) {

        return possibleNumber.matches("[0-9]");
    }
}
