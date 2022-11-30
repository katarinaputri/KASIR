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

public class UpdateMenuActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database dbHelper;
    Button bt1, bt2;
    EditText text1, text2;
    TextView tvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        dbHelper = new Database(this);

        text1 = findViewById(R.id.menuupdate);
        text2 = findViewById(R.id.hargaupdate);
        tvid = findViewById(R.id.id);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu WHERE menu = '" +
                getIntent().getStringExtra("menu") +
                "'", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            tvid.setText(cursor.getString(0).toString());
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
        }

        bt1 = findViewById(R.id.btnsimpan);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update menu set menu = '" +
                        text1.getText().toString() + "', harga ='" +
                        text2.getText().toString() + "' where id='" +
                        tvid.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                DaftarMenuActivity.ma.RefreshList();
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