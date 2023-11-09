package com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento;

import java.math.BigDecimal;

public enum TipoComprimento {
    FERMI("FM", " - Femtometre", new BigDecimal("0.000000000000001")),
    ANGSTROM("Å", " - Angstrom", new BigDecimal("0.0000000001")),
    NANOMETRE("NM", " - Nanometre", new BigDecimal("0.000000001")),
    MICRON("μM", " - Micrometre ", new BigDecimal("0.000001")),
    MILLIMETRE("MM", " - Millimetre", new BigDecimal("0.001")),
    CENTIMETRE("CM", " - Centimetre", new BigDecimal("0.01")),
    FOOT("FOOT", " - Foot", new BigDecimal("0.3048")),
    YARD("YD", " - Yard", new BigDecimal("0.9144")),
    METRE("M", " - Metre", new BigDecimal("1.0")),
    FATHOM("FA", " - Fathom", new BigDecimal("1.8288")),
    NAUTICAL_MILE("NTM", " - Nautical Mile", new BigDecimal("1.852")),
    ROD("ROD", " - Rod", new BigDecimal("5.0292")),
    CHAIN("CH", " - Chain", new BigDecimal("20.1168")),
    KILOMETRE("KM", " - Kilometre", new BigDecimal("1000.0")),
    MILE("MI", " - Miles", new BigDecimal("1609.344")),
    EARTH_RADIUS("ER", " - Earth Radius", new BigDecimal("6371000.0")),
    LUNAR_DISTANCE("LD", " - Lunar Distance", new BigDecimal("384400000.0")),
    ASTRONOMICAL("AU", " - Astronomical Unit", new BigDecimal("149597870700.0")),
    LIGHT_YEAR("LY", " - Light Year", new BigDecimal("9460730472580800.0")),
    PARSEC("PC", " - Parsec", new BigDecimal("30856775810000000.0"));

    private final String abbreviationItem;
    private final String fullName;
    private final BigDecimal valueInMeters;

    TipoComprimento(String abbreviationItem, String fullName, BigDecimal valueInMeters) {
        this.abbreviationItem = abbreviationItem;
        this.fullName = fullName;
        this.valueInMeters = valueInMeters;
    }

    public String getAbbreviationItem() {

        return this.abbreviationItem;
    }

    public String getFullName() {

        return this.fullName;
    }

    public BigDecimal getValueInMetre() {

        return this.valueInMeters;
    }


//    public abstract Double convert(int differenceBeteweenItems, String converterToType);
}