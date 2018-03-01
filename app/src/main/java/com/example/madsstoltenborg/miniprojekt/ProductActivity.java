package com.example.madsstoltenborg.miniprojekt;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {
    public static final String EXTRA_PRODUCTID = "productId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        try {
            //Get the drink from the intent
            int productId = (Integer) getIntent().getExtras().get(EXTRA_PRODUCTID);

            Product product = Storage.getInstance().getProduct(productId);

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(product.getName());

            TextView price = (TextView) findViewById(R.id.price);
            price.setText(""+product.getPrice());

            TextView quantity = (TextView) findViewById(R.id.quantity);
            quantity.setText(product.getQuantity());

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
