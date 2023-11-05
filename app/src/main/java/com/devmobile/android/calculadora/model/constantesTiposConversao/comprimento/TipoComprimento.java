package com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento;

public enum TipoComprimento {
    FERMI("FM" ,  " - Fermi"),
    ANGSTROMS("Å",  " - Angstrom"),
    NANOMETRES("NM",  " - Nanometre"),
    MILLIMETRES("MM",  " - Millimetre"),
    CENTIMETRES("CM",  " - Centimetre"),
    METRES("M",  " - Metre"),
    KILOMETRES("KM",  " - Kilometre"),
    POLEWARDS("POL", " - Poleward"),
    FEET("FEET",  " - Feet"),
    YARD("YD",  " - Yards"),
    MILES("MI",  " - Miles"),
    LIGHT_YEAR("LY",  " - Light Year");

    private String abbreviationItem;
    private String fullName;

    TipoComprimento(String abbreviationItem, String fullName) {
        this.abbreviationItem = abbreviationItem;
        this.fullName = fullName;
    }

    public String getAbbreviationItem() {
        return this.abbreviationItem;
    }

    public String getFullName() {
        return this.fullName;
    }

//    public abstract int convert(int valueToConvert);
}