package com.devmobile.android.calculadora;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;
import com.devmobile.android.calculadora.model.interfaces.OnItemSpinnerListener;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class ConversorComprimento
        implements OnItemSpinnerListener
        , DataInsertEditTextConverter {
    private Context context;
    private final ArrayList<HashMap<String, String>> typesToConverter = new ArrayList<>();
    private final TipoComprimento[] enumsUnitiesLength = TipoComprimento.values();
    private final int qtdItems = TipoComprimento.values().length;
    private final String[] abbreviationItems = new String[qtdItems];
    private final String[] nameItems = new String[qtdItems];
    private String firstSpinnerItemSelected;
    private String secondSpinnerItemSelected;
    private int idItemFirstSpinner;
    private int idItemSecondSpinner;
    private ActivityConverters activityConvertersInstance;


    public ConversorComprimento(Context context) {
        this.context = context;

        init();
    }

    private void init() {
        Intent intent = new Intent(context, ActivityConverters.class);
        context.startActivity(intent);
        Class<?> myClass;

        try {

            myClass = Class.forName("ConversorComprimento");
            myClass.getDeclaredConstructor().newInstance();

            Object teste = myClass.getMethod("toStringtt").invoke(ConversorComprimento.this);
            teste.toString();

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | InstantiationException e) {

        }

        addAbbreviations();
        addNameItem();
        addTypesOfConverter();

//        activityConvertersInstance.putItem(typesToConverter);
//        activityConvertersInstance.addSpinnerItemSelected(this);
//        activityConvertersInstance.addDataInsertEditTextConverter(this);
    }


    private void addTypesOfConverter() {

        for (int i = 0; i < qtdItems; i++) {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("abbreviation", abbreviationItems[i]);
            hashMap.put("name", nameItems[i]);
            typesToConverter.add(hashMap);
        }

    }

    private void addAbbreviations() {
        for (int i = 0; i < qtdItems; i++) {
            abbreviationItems[i] = enumsUnitiesLength[i].getAbbreviationItem();
        }
    }

    private void addNameItem() {
        for (int i = 0; i < qtdItems; i++) {
            nameItems[i] = enumsUnitiesLength[i].getFullName();
        }
    }

    @Override
    public void spinnerItemSelected(String firstSpinnerItemSelected, int idItemFirstSpinner
            , String secondSpinnerItemSelected, int idItemSecondSpinner) {

        this.firstSpinnerItemSelected = firstSpinnerItemSelected;
        this.secondSpinnerItemSelected = secondSpinnerItemSelected;
        this.idItemFirstSpinner = idItemFirstSpinner;
        this.idItemSecondSpinner = idItemSecondSpinner;
    }

    @Override
    public void dataInsertInEditTextToConverter(String dataToConverter) {

        this.converterValueEditText(dataToConverter);
    }

    private void converterValueEditText(String dataInput) {
        BigDecimal itemFirstSpinnerValueInMeters = enumsUnitiesLength[idItemFirstSpinner].getValueInMetre();
        BigDecimal itemSecondSpinnerValueInMeters = enumsUnitiesLength[idItemSecondSpinner].getValueInMetre();
        BigDecimal resultDivision = itemFirstSpinnerValueInMeters.divide(itemSecondSpinnerValueInMeters, 15, RoundingMode.HALF_EVEN);
        BigDecimal resultFinal = resultDivision.multiply(new BigDecimal(dataInput));

        this.passValueConverted(resultFinal.toString());
    }

    private void passValueConverted(String valueConverted) {
        activityConvertersInstance.putDataInputConverted(valueConverted);
    }

    public void toStringtt() {
        System.out.println("teste");
    }
}
