package com.example.madsstoltenborg.miniprojekt;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */


public class ShoppingWrapper extends CursorWrapper {
    public ShoppingWrapper(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct() {
        long id = getLong(getColumnIndex("_id"));
        String nameText = getString(getColumnIndex("NAME"));
        double priceText = (Double.parseDouble(getString(getColumnIndex("PRICE"))));
        String quantityText = getString(getColumnIndex("QUANTITY"));
        return new Product(id, nameText, priceText, quantityText);
    }
}

