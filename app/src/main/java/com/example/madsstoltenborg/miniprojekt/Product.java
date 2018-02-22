package com.example.madsstoltenborg.miniprojekt;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Product {
    private long id;
    private String name;
    private String quantity;

    public Product(String name, String quantity) {
        this(-1, name, quantity);

    }

    public Product(long id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
