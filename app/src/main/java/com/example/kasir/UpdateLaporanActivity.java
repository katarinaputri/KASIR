package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateLaporanActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database dbHelper;
    Button bt1, bt2;
    EditText text1, text2, text3, text4;
    TextView tvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_laporan);

        dbHelper = new Database(this);

        text1 = findViewById(R.id.namaupdate);
        text2 = findViewById(R.id.beliupdate);
        text3 = findViewById(R.id.bayarupdate);
        text4 = findViewById(R.id.kembalianupdate);
        tvid = findViewById(R.id.id);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM laporaan WHERE namapembeli = '" +
                getIntent().getStringExtra("namapembeli") +
                "'", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            tvid.setText(cursor.getString(0).toString());
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
            text4.setText(cursor.getString(4).toString());
        }

        bt1 = findViewById(R.id.btnsimpan);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update laporan set namapembeli = '" +
                        text1.getText().toString() + "', totalbelanja ='" +
                        text2.getText().toString() + "', totalbayar ='" +
                        text3.getText().toString() + "', kembalian ='" +
                        text4.getText().toString() + "' where id='" +
                        tvid.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LaporanActivity.la.RefreshListLapo();
                finish();
            }
        });

        bt2 = findViewById(R.id.btnkembali);
        bt2.setOnClickListener(new View.OnClickListener() {
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