package com.example.madsstoltenborg.miniprojekt;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class Storage {

    //TODO Hvad er det her
    private Storage() {};

    private ShoppingDatabaseHelper shoppingDatabaseHelper = ShoppingDatabaseHelper.getInstance();
    private static Storage storage;

    // Singleton pattern
    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
            storage.initStorage();
        }
        return storage;
    }


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

    //TODO CRUD shop
    public void addShop (String name, String address, String website){
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues shopValues = new ContentValues();
        shopValues.put("NAME", name);
        shopValues.put("ADDRESS", address);
        shopValues.put("WEBSITE", website);
        db.insert("SHOP", null, shopValues);
    }
    public Cursor getShops()
    {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        return db.query("SHOP", new String[]{"_id", "NAME", "ADDRESS", "WEBSITE"},
                null, null, null, null, null, null);
    }

//TODO smide produkter på butikker
    public void insertProductsIntoShop(int product_id, int shop_id, double price){
SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
ContentValues shopProductValues = new ContentValues();
shopProductValues.put("SHOP_ID", shop_id);
shopProductValues.put("PRODUCT_ID", product_id);
shopProductValues.put("PRICE", price);
db.insert("SHOP_PRODUCTS", null, shopProductValues);

    }

    // TODO: , groceryLists
public void insertGroceryList (String name){
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues groceryListValues = new ContentValues();
        groceryListValues.put("NAME", name);
        db.insert("GROCERYLISTS", null, groceryListValues);
}

public boolean deleteGroceryList(long id)
{
    SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
return db.delete("GROCERYLISTS", "_id = " + id, null) > 0;
}


    //TODO: QUANTITY --> Volume
    // INIT dummy data
    private void initStorage() {
        if (getProducts().getCount() == 0) {
            addProduct(new Product("Mælk", 200,"1 L"));
            addProduct(new Product("Kage", 5,"500 g"));
            addProduct(new Product("Filter", 12,"50 stk."));
        }
    }

}
