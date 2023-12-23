package com.devmobile.android.calculadora;

import androidx.annotation.NonNull;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Country {
    private static final DecimalFormat decimalFormate = new DecimalFormat();

    @NonNull
    public static DecimalFormat getDecimalFormatOfCountry() {
        decimalFormate.setMaximumFractionDigits(5);

        return decimalFormate;
    }

    @NonNull
    public static String getDecimalSymbolSeparator() {
        char symbolDecimal = DecimalFormatSymbols.getInstance().getDecimalSeparator();

        return (symbolDecimal == '.') ? "." : ",";
    }
}
