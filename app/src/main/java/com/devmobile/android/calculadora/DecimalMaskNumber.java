package com.devmobile.android.calculadora;

import androidx.annotation.NonNull;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;

public abstract class DecimalMaskNumber {
    private static final NumberFormat numberFormatOfCountry = NumberFormat.getInstance();

    @NonNull
    public static String setMask(@NonNull String input) {
        String numberNoSymbols;
        String symbolsNoNumbers;
        String[] conjunctOfNumbersWithMask;
        String expressionReformatted = input;

        if (input.length() > 3) {
            numberNoSymbols = input.replaceAll("[^0-9,.]", " ");
            symbolsNoNumbers = input.replaceAll("[0-9,.]", "");

            String[] conjunctOfNumbers = splitConjutoOfNumbers(numberNoSymbols, " ");
            String[] conjunctOfSymbols = splitConjutoOfSymbols(symbolsNoNumbers, "");

            conjunctOfNumbersWithMask = addNumMaskInConjunctOfNumbers(conjunctOfNumbers);
            expressionReformatted = restoreExpressionWithMask(conjunctOfNumbersWithMask, conjunctOfSymbols);

        }

        return expressionReformatted;
    }

    /**
     *
     * @param conjuntOfNumbersNoMask conjucts of numbers with mask
     * @return conjunct of numbers with mask.
     */
    private static String[] addNumMaskInConjunctOfNumbers(@NonNull String[] conjuntOfNumbersNoMask) {
        Number number;

        for (int i = 0; i < conjuntOfNumbersNoMask.length; i++) {

            try {
                number = numberFormatOfCountry.parse(conjuntOfNumbersNoMask[i]);
                BigDecimal mask = new BigDecimal(number.toString());

                conjuntOfNumbersNoMask[i] = Country.getDecimalFormatOfCountry().format(mask);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return conjuntOfNumbersNoMask;
    }

    /**
     *
     * @param conjunctoOfNumbersWithMask are numbers that been separados em numbers group
     * @param conjuntOfSymbols are as sybols such +-* e etc
     * @return expression reformultated of same way when the user insert, but, this time the numbers
     * have mask.
     */
    @NonNull
    private static String restoreExpressionWithMask(@NonNull String[] conjunctoOfNumbersWithMask,
                                                    @NonNull String[] conjuntOfSymbols) {
        StringBuilder expressionReformalated = new StringBuilder();
        System.out.println(conjuntOfSymbols.length);

        for (int i = 0; (i <= conjunctoOfNumbersWithMask.length) || (i <= conjuntOfSymbols.length) ; i++) {

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
     *
     * @param stringToSplit string to separate the numbers
     * @param split character that will be use to separates
     * @return a array conjunct of numbers on the expression.
     */
    @NonNull
    private static String[] splitConjutoOfNumbers(@NonNull String stringToSplit, String split) {

        return stringToSplit.split(split);
    }

    /**
     *
     * @param stringToSplit string to separate the symbols
     * @param split character that will be use to separates
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
