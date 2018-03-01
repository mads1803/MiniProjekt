package com.example.madsstoltenborg.miniprojekt;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Product {
    private long id;
    private String name;
    private String quantity;



    private double price;

    public Product(String name, double price, String quantity) {
        this(-1, name, price, quantity);

    }



    public Product(long id, String name, double price, String quantity) {
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
