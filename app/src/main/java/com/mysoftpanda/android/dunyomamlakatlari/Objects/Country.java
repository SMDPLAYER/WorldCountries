package com.mysoftpanda.android.dunyomamlakatlari.Objects;

public class Country {
    private int countryFlag;
    private String countryName;
    private String countryNameUz;

    public Country(int i, String str, String str2) {
        this.countryFlag = i;
        this.countryName = str;
        this.countryNameUz = str2;
    }

    public int getCountryFlag() {
        return this.countryFlag;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getCountryNameUz() {
        return this.countryNameUz;
    }
}
