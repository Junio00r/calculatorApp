package com.devmobile.android.calculadora;

import android.content.Context;
import android.content.Intent;

import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;
import com.devmobile.android.calculadora.model.interfaces.OnItemSpinnerListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class ConversorComprimento
        implements OnItemSpinnerListener
        , DataInsertEditTextConverter {
    private Context context;
    private final ArrayList<HashMap<String, String>> typesToConverter = new ArrayList<>();
    private final TipoComprimento[] enumsLength = TipoComprimento.values();
    private final ActivityConverters activityConverters = new ActivityConverters();;
    private final int qtdItems = TipoComprimento.values().length;
    private final String[] abbreviationItems = new String[qtdItems];
    private final String[] nameItems = new String[qtdItems];
    private String firstSpinnerItemSelected;
    private String secondSpinnerItemSelected;
    private String dataToConverter;
    public ConversorComprimento(Context context) {
        this.context = context;

        init();
    }

    private void init() {
        Intent intent = new Intent(context, ActivityConverters.class);
        context.startActivity(intent);

        addAbbreviations();
        addNameItem();
        addTypesOfConverter();

        activityConverters.putItem(typesToConverter);
        activityConverters.addSpinnerItemSelected(this);
        activityConverters.addDataInsertEditTextConverter(this);
    }

    public void convertValue(Double value) {

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
            abbreviationItems[i] = enumsLength[i].getAbbreviationItem();
        }
    }

    private void addNameItem() {
        for (int i = 0; i < qtdItems; i++) {
            nameItems[i] = enumsLength[i].getFullName();
        }
    }

    @Override
    public void spinnerItemSelected(String firstSpinnerItemSelected, String secondSpinnerItemSelected) {
        this.firstSpinnerItemSelected = firstSpinnerItemSelected;
        this.secondSpinnerItemSelected = secondSpinnerItemSelected;
    }

    @Override
    public void dataInsertInEditTextToConverter(String dataToConverter) {
        this.dataToConverter = dataToConverter;
    }

    private void converterValueEditText() {
        this.secondSpinnerItemSelected = "itemSpinner";
        this.dataToConverter = "data";

        TipoComprimento.valueOf(secondSpinnerItemSelected);
    }
}
