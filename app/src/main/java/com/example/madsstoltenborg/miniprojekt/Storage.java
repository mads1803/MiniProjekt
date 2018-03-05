package com.example.madsstoltenborg.miniprojekt;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Storage {
    private Storage() {};
    private static Storage storage;

    // Singleton pattern
    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
            storage.initStorage();
        }
        return storage;
    }

    private ShoppingDatabaseHelper shoppingDatabaseHelper = ShoppingDatabaseHelper.getInstance();



    //TODO Product
    public Product getProduct(long id) {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("PRODUCT",
                new String[]{"_id", "NAME", "PRICE", "VOLUME"},
                "_id = ?",
                new String[]{"" + id},
                null, null, null);
        if (cursor.moveToFirst()) {
            String nameText = cursor.getString(cursor.getColumnIndex("NAME"));
            double priceText = (Double.parseDouble(cursor.getString(cursor.getColumnIndex("PRICE"))));
            String quantityText = cursor.getString(cursor.getColumnIndex("VOLUME"));

            cursor.close();
            return new Product(id, nameText, priceText, quantityText);

        } else {
            cursor.close();
            return null;
        }
    }

    public ShoppingWrapper getProducts() {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor  cursor = db.query("PRODUCT",
                new String[]{"_id", "NAME", "VOLUME"},
                null, null,null, null, null);
        return new ShoppingWrapper(cursor);
    }

    public long addProduct(Product product) {
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues productValues = new ContentValues();
        productValues.put("NAME", product.getName());
        productValues.put("VOLUME", product.getQuantity());
        return db.insert("PRODUCT", null, productValues);
    }
    //TODO: QUANTITY --> Volume
    private void initStorage() {
        if (getProducts().getCount() == 0) {
            addProduct(new Product("Mælk", 200,"1 L"));
            addProduct(new Product("Kage", 5,"500 g"));
            addProduct(new Product("Filter", 12,"50 stk."));
        }
    }

}
