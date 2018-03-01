package com.example.madsstoltenborg.miniprojekt;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ProductListActvity extends AppCompatActivity {
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_actvity);
        ListView listDrinks = (ListView) findViewById(R.id.list_products);
        try {
            cursor = Storage.getInstance().getProducts();
            listAdapter = new ShoppingCursorAdapter(this,
                    R.layout.product_list_item,
                    cursor,
                    new String[]{"NAME", "PRICE", "QUANTITY"},
                    new int[]{R.id.productName, R.id.productPrice, R.id.productQuantity},
                    0);
            listDrinks.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cursor = Storage.getInstance().getProducts();
        listAdapter.changeCursor(cursor);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
