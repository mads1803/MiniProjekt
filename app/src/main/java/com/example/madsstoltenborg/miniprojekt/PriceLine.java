package com.example.madsstoltenborg.miniprojekt;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class PriceLine {
    private Product product;
    private Shop shop;
    private float price;

    public PriceLine(Product product, Shop shop, float price) {
        this.product = product;
        this.shop = shop;
        this.price = price;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
