package com.example.madsstoltenborg.miniprojekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class ShoppingDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Shopping"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    private static Context applicationContext;

    public static void setApplicationContext(Context context){
        applicationContext = context.getApplicationContext();
    }

    private static ShoppingDatabaseHelper shoppingDatabaseHelper;

    private ShoppingDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static ShoppingDatabaseHelper getInstance(){
        if (shoppingDatabaseHelper == null) {
            shoppingDatabaseHelper = new ShoppingDatabaseHelper(applicationContext);
        }
        return shoppingDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            //Oprettelse af butiksvarer
            db.execSQL("CREATE TABLE SHOP (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADDRESS TEXT, "
                    + "WEBSITE TEXT);");

            //Oprettelse af produkter TODO Droppe price her ?
            db.execSQL("CREATE TABLE PRODUCT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "PRICE TEXT, "
                    + "VOLUME TEXT);");

            //Oprettelse af tabel til butiksprodukter + binding
            db.execSQL("CREATE TABLE SHOP_PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SHOP_ID INTEGER, "
                    + "PRODUCT_ID INTEGER,"
                    + "PRICE NUMERIC);");

            //Oprettelse af tabel til grocerylists
            db.execSQL("CREATE TABLE GROCERYLISTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT);");

            //Oprettelse af tabel til varer til grocerylists
            //TODO evt en boolean til valgt/købt
            db.execSQL("CREATE TABLE GROCERYLISTS_PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "GROCERYLISTS_ID INTEGER, "
                    + "SHOP_PRODUCTS_ID INTEGER, "
                    + "QUANTITY INTEGER);");



            //TODO view til shopproduts
            //TODO view grocerylistproduct view

            //TODO Havde vi ikke droppet priceline?
            db.execSQL("CREATE TABLE PRICELINE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PRODUKTID INTEGER, "
                    + "SHOPID INTERGER, "
                    + "PRICE FLOAT);");


        }
        if (oldVersion < 2) {
           // db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            Log.d("DEMO", "updateMyDatabase: FEJL UPDATEDATABASE!");
        }
    }
}

