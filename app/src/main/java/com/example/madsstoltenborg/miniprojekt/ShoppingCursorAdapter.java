package com.example.madsstoltenborg.miniprojekt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Mads Stoltenborg on 22-02-2018.
 */

public class ShoppingCursorAdapter extends SimpleCursorAdapter {
    ShoppingCursorAdapter(Context context, int layout, Cursor c,
                       String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context,context.getString(R.string.checkbox_ischecked)
                        + isChecked, Toast.LENGTH_SHORT).show();
            }
        });*/

        final int id = cursor.getInt(cursor.getColumnIndex("_id"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.EXTRA_PRODUCTID, (int) id);
                context.startActivity(intent);
            }
        });
    }
}
