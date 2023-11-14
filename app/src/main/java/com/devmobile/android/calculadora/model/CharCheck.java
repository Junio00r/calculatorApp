package com.devmobile.android.calculadora.model;

import androidx.annotation.NonNull;

public class CharCheck {

    public static boolean lastCharIsOperator(@NonNull String lastChar) {

        switch (lastChar) {
            case "+":
            case "-":
            case "*":
            case "×":
            case "÷":
            case "/":
            case "^":
            case "√":
            case "sqrt":
            case "!":
                return true;

            case "(":
            case ")":
            case "%":
            case "π":
            case "pi":
            case "e":
            case "euler":
            case "log":
            case "sin":
            case "cos":
            case "tan":
            case "tang":
        }
        return false;
    }
}
