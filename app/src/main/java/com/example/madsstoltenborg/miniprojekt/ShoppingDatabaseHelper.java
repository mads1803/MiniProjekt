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
            db.execSQL("CREATE TABLE PRODUCT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "QUANTITY TEXT);");

            db.execSQL("CREATE TABLE SHOP (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADRESS TEXT, "
                    + "WEBSITE TEXT);");

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

