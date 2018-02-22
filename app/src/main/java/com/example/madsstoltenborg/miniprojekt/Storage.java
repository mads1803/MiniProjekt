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
    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
            storage.initStorage();
        }
        return storage;
    }

    private ShoppingDatabaseHelper shoppingDatabaseHelper = ShoppingDatabaseHelper.getInstance();

    public Product getProduct(long id) {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("PRODUCT",
                new String[]{"_id", "NAME", "QUANTITY"},
                "_id = ?",
                new String[]{"" + id},
                null, null, null);
        if (cursor.moveToFirst()) {
            String nameText = cursor.getString(cursor.getColumnIndex("NAME"));
            String quantityText = cursor.getString(cursor.getColumnIndex("QUANTITY"));

            cursor.close();
            return new Product(id, nameText, quantityText);

        } else {
            cursor.close();
            return null;
        }
    }

    public ShoppingWrapper getProducts() {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor  cursor = db.query("PRODUCT",
                new String[]{"_id", "NAME", "QUANTITY"},
                null, null,null, null, null);
        return new ShoppingWrapper(cursor);
    }

    public long addProduct(Product product) {
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues productValues = new ContentValues();
        productValues.put("NAME", product.getName());
        productValues.put("QUANTITY", product.getQuantity());
        return db.insert("PRODUCT", null, productValues);
    }
    //TODO: QUANTITY --> Volume
    private void initStorage() {
        if (getProducts().getCount() == 0) {
            addProduct(new Product("Mælk", "1 L"));
            addProduct(new Product("Kage", "500 g"));
            addProduct(new Product("Filter", "50 stk."));
        }
    }

}
