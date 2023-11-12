package com.devmobile.android.calculadora;

import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;
import com.devmobile.android.calculadora.model.interfaces.OnItemSpinnerListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    protected String getValueConverted(String valueToConverted) {

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        BigDecimal itemFirstSpinnerValueInMeters = enumsUnitiesLength[idItemFirstSpinner].getValueInMetre();
        BigDecimal itemSecondSpinnerValueInMeters = enumsUnitiesLength[idItemSecondSpinner].getValueInMetre();
        BigDecimal resultDivision = itemFirstSpinnerValueInMeters.divide(itemSecondSpinnerValueInMeters, 15, RoundingMode.HALF_EVEN);
        BigDecimal resultConverted = resultDivision.multiply(new BigDecimal(valueToConverted));

        return decimalFormat.format(resultConverted);
    }
}
