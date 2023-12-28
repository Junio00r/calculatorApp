package com.devmobile.android.calculadora;

import androidx.annotation.NonNull;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Country {
    private static final DecimalFormat decimalFormate = new DecimalFormat();
    private static final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();

    @NonNull
    public static DecimalFormat getDecimalFormatOfCountry() {

        return decimalFormate;
    }

    @NonNull
    public static String getDecimalSymbolSeparator() {
        char symbolDecimal = DecimalFormatSymbols.getInstance().getDecimalSeparator();

        if (symbolDecimal == '.') {
            decimalFormatSymbols.setGroupingSeparator(',');

            return ".";
        }

        decimalFormatSymbols.setGroupingSeparator(',');

        return ",";
    }
}
