package com.devmobile.android.calculadora.model.conversores;

import androidx.annotation.NonNull;
import com.devmobile.android.calculadora.Country;
import com.devmobile.android.calculadora.model.RefactorExpression;
import com.devmobile.android.calculadora.model.constantesTiposConversao.TipoComprimento;
import org.jetbrains.annotations.Contract;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class ConversorComprimento
        extends Converter {

    private final TipoComprimento[] enumsUnitiesLength = TipoComprimento.values();
    private final int qtdItems = TipoComprimento.values().length;
    private final String[] abbreviationItems = new String[qtdItems];
    private final String[] nameItems = new String[qtdItems];

    private ConversorComprimento() {
        init();
    }

    @NonNull
    @Contract(" -> new")
    public static ConversorComprimento getInstance() {

        return new ConversorComprimento();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void addTypesOfConverter() {

        for (int i = 0; i < qtdItems; i++) {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("abbreviation", abbreviationItems[i]);
            hashMap.put("name", nameItems[i]);
            this.typesToConverter.add(hashMap);
        }
    }

    @Override
    protected void addAbbreviations() {

        for (int i = 0; i < qtdItems; i++) {
            abbreviationItems[i] = enumsUnitiesLength[i].getAbbreviationItem();
        }
    }

    @Override
    protected void addNameItem() {

        for (int i = 0; i < qtdItems; i++) {
            nameItems[i] = enumsUnitiesLength[i].getFullName();
        }
    }

    public String getValueConverted(@NonNull String value) {
        String valueToDecimalFormat = RefactorExpression.parseToDecimalFormat(value);

        BigDecimal itemFirstSpinnerValueInMeters = enumsUnitiesLength[idItemFirstSpinner].getValueInMetre();
        BigDecimal itemSecondSpinnerValueInMeters = enumsUnitiesLength[idItemSecondSpinner].getValueInMetre();
        BigDecimal resultDivision = itemFirstSpinnerValueInMeters.divide(itemSecondSpinnerValueInMeters, 15, RoundingMode.HALF_EVEN);
        BigDecimal resultConverted = resultDivision.multiply(new BigDecimal(valueToDecimalFormat));

        return Country.getDecimalFormatOfCountry().format(resultConverted);
    }
}
