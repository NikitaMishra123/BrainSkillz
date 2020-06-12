package com.example.brainskillz;


public class CountryItem {
    int CountryFlag;
    String CountryName;
    String CountryCode;

    public CountryItem(int countryFlag, String countryName, String countryCode) {
        CountryFlag = countryFlag;
        CountryName = countryName;
        CountryCode = countryCode;
    }

    public int getCountryFlag() {
        return CountryFlag;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryFlag(int countryFlag) {
        CountryFlag = countryFlag;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }
}



