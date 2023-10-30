package com.devmobile.android.calculadora.model;

public class ExpressionInputCheck {

    public static boolean isOperator(String expression) {
        final String characterInput = String.valueOf(expression.charAt(expression.length() - 1));

        switch (characterInput) {
            case "+":
            case "-":
            case "*":
            case "×":
            case "÷":
            case "/":
            case "^":
            case "(":
            case ")":
            case "√":
            case "sqrt":
            case "!":
            case "%":
            case "log":
            case "sin":
            case "cos":
            case "tan":
            case "tang":
                return true;

            case "π":
            case "pi":
            case "e":
            case "euler":
        }
        return false;
    }
}
