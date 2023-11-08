package com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento;

public enum TipoComprimento {
    METRE("M",  " - Metre", 1),
    KILOMETRE("KM",  " - Kilometre", Math.pow(10, 3)),
    CENTIMETRE("CM",  " - Centimetre", Math.pow(10, -2)),
    MILLIMETRE("MM",  " - Millimetre", Math.pow(10,-3)),
    MICRON("μM",  " - Micron", Math.pow(10, -6)),
    NANOMETRE("NM",  " - Nanometre", Math.pow(10, -9)),
    ANGSTROM("Å",  " - Angstrom", Math.pow(10, -10)),
    FERMI("FM" ,  " - Femto-metre", Math.pow(10, -15)),
    FOOT("FOOT",  " - Foot", 0.3048),
    YARD("YD",  " - Yard",  0.9144),
    MILE("MI",  " - Miles", Double.parseDouble("1,609.34")),
    NAUTICAL_MILE("NTM", " - Nautical Mile", Double.parseDouble("1,852")),
    FATHOM("FA", " - Fathom", 1.8288),
    CHAIN("CH",  " - Chain", 20.1168),
    ROD("ROD",  " - Rod", 5.0292),
    EARTH_RADIUS("ER",  " - Earth Radius", Double.parseDouble("6,371,000")),
    LUNAR_DISTANCE("LD",  " - Lunar Distance", Double.parseDouble("384,400,000")),
    ASTRONOMICAL("AU",  " - Astronomical Unit", Double.parseDouble("149,600,000,000")),
    LIGHT_YEAR("LY",  " - Light Year", Double.parseDouble("9,461,000,000,000,000 ")),
    PARSEC("PC",  " - Parsec", Double.parseDouble("30,900,000,000,000,000"));

    private String abbreviationItem;
    private String fullName;
    private double valueInMetre;

    TipoComprimento(String abbreviationItem, String fullName, double valueInMetre) {
        this.abbreviationItem = abbreviationItem;
        this.fullName = fullName;
        this.valueInMetre = valueInMetre;
    }

    public String getAbbreviationItem() {
        return this.abbreviationItem;
    }

    public String getFullName() {
        return this.fullName;
    }

    public double getValueInMetre() {
        return this.valueInMetre;
    }

//    public abstract int convert(int valueToConvert);
}