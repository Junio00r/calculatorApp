package com.devmobile.android.calculadora;

import android.content.Context;
import android.content.Intent;

import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class ConversorComprimento {
    private final Context context;
    private final ArrayList<HashMap<String, String>> typesToConverter = new ArrayList<>();
    private final TipoComprimento[] enumsComprimento = TipoComprimento.values();
    private final ActivityConverters activityConverters = new ActivityConverters();
    private final int qtdItems = TipoComprimento.values().length;
    private String[] abbreviationItems = new String[qtdItems];
    private String[] nameItems = new String[qtdItems];

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
            abbreviationItems[i] = enumsComprimento[i].getAbbreviationItem();
        }
    }

    private void addNameItem() {
        for (int i = 0; i < qtdItems; i++) {
            nameItems[i] = enumsComprimento[i].getFullName();
        }
    }
}
