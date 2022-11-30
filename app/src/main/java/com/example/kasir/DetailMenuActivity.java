package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailMenuActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database dbHelper;
    Button btnback;
    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        dbHelper = new Database(this);

        text1 = findViewById(R.id.id_menu);
        text2 = findViewById(R.id.nama_menu);
        text3 = findViewById(R.id.harga_menu);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu WHERE menu = '" +
                getIntent().getStringExtra("menu") +
                "'", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
        }
        btnback = findViewById(R.id.btnkembali);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}