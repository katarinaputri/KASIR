package com.example.kasir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DaftarMenuActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    Database dbcenter;
    public static DaftarMenuActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);
        ImageView ivback = (ImageView) findViewById(R.id.back);
        FloatingActionButton fabadd = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent add = new Intent(DaftarMenuActivity.this, TambahMenuActivity.class);
                startActivity(add);
            }
        });

        ma = this;
        dbcenter = new Database(this);
        RefreshList();
    }

    @SuppressLint("ResourceType")
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu", null);

        daftar = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int cc = 0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = findViewById(R.id.list_menu);

        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, daftar));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Detail Menu", "Update Menu", "Hapus Menu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarMenuActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailMenuActivity.class);
                                i.putExtra("menu", selection);
                                startActivity(i);
                                break;

                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateMenuActivity.class);
                                in.putExtra("menu", selection);
                                startActivity(in);
                                break;

                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from menu where menu = '" + selection + "' ");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}