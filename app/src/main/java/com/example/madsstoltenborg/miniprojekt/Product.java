package com.example.madsstoltenborg.miniprojekt;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Product {
    private long id;
    private String name;
    private String volume;


//todo har ændret til volume
    private double price;

    public Product(String name, double price, String quantity) {
        this(-1, name, price, quantity);

    }



    public Product(long id, String name, double price, String volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return volume;
    }

    public void setQuantity(String quantity) {
        this.volume = volume;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
