package com.devmobile.android.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Converter {
    protected final ArrayList<HashMap<String, String>> typesToConverter = new ArrayList<>();
    protected String firstSpinnerItemSelected;
    protected String secondSpinnerItemSelected;
    protected Integer idItemFirstSpinner;
    protected Integer idItemSecondSpinner;
    protected String dataInsert;

    public Converter() {

        init();
    }

    protected void init() {

        addAbbreviations();
        addNameItem();
        addTypesOfConverter();
    }

    protected void addTypesOfConverter() {  }

    protected void addAbbreviations() {  }

    protected void addNameItem() {  }

    protected void addSpinnerSelected(String firstSpinnerItemSelected, Integer idItemFirstSpinner
            , String secondSpinnerItemSelected, Integer idItemSecondSpinner) {

        this.firstSpinnerItemSelected = firstSpinnerItemSelected;
        this.idItemFirstSpinner = idItemFirstSpinner;
        this.secondSpinnerItemSelected = secondSpinnerItemSelected;
        this.idItemSecondSpinner = idItemSecondSpinner;

    }

    protected String getValueConverted(String valueToConverted) {

        return "";
    }

    // Getters
    protected ArrayList<HashMap<String, String>> getTypesToConverter() {

        return typesToConverter;
    }

    protected String getFirstSpinnerItemSelected() {

        return firstSpinnerItemSelected;
    }

    protected String getSecondSpinnerItemSelected() {

        return secondSpinnerItemSelected;
    }

    protected int getIdItemFirstSpinner() {

        return idItemFirstSpinner;
    }

    protected int getIdItemSecondSpinner() {

        return idItemSecondSpinner;
    }
}
