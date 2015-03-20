package com.owlscoffeehouse.ormlitedemo.Model;

import com.j256.ormlite.field.DatabaseField;

public class Country {

    public static final String C_ID = "country_id";
    public static final String C_NAME = "country_name";
    public static final String C_LANGUAGE = "country_language";
    public static final String C_CAPITAL = "country_capital";

    //primary key
    @DatabaseField(id = true, canBeNull = false, columnName = C_ID)
    private int cID;

    @DatabaseField(columnName = C_NAME)
    private String cName;
    @DatabaseField(columnName = C_LANGUAGE)
    private String cLanguage;
    @DatabaseField(columnName = C_CAPITAL)
    private String cCapital;

    public Country() {
        // no-argument (default) constructor
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcLanguage() {
        return cLanguage;
    }

    public void setcLanguage(String cLanguage) {
        this.cLanguage = cLanguage;
    }

    public String getcCapital() {
        return cCapital;
    }

    public void setcCapital(String cCapital) {
        this.cCapital = cCapital;
    }
}