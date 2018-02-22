package com.example.madsstoltenborg.miniprojekt;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Shop {
    private String name;
    private String adress;
    private String website;

    public Shop(String name, String adress, String website) {
        this.name = name;
        this.adress = adress;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
