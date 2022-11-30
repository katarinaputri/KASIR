package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailLaporanActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database dbHelper;
    Button btnback;
    TextView text1, text2, text3, text4,text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);
        dbHelper = new Database(this);

        text1 = findViewById(R.id.id_lapo);
        text2 = findViewById(R.id.nama_pembeli);
        text3 = findViewById(R.id.total_belanja);
        text4 = findViewById(R.id.total_bayar);
        text5 = findViewById(R.id.total_kembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM laporan WHERE namapembeli = '" +
                getIntent().getStringExtra("namapembeli") +
                "'", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
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