package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahMenuActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database dbHelper;
    Button btback, btsave;
    Menu menu;
    EditText text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);
        dbHelper = new Database(this);

        text1 = findViewById(R.id.idbaru);
        text2 = findViewById(R.id.menubaru);
        text3 = findViewById(R.id.hargabaru);

        btback = findViewById(R.id.btnkembali);
        btsave = findViewById(R.id.btnsimpan);

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into menu values ('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                DaftarMenuActivity.ma.RefreshList();
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