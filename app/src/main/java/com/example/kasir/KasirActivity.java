package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KasirActivity extends AppCompatActivity {

    Menu menu;
    protected Cursor cursor;
    Database dbHelper;

    EditText etnama, etbayar, etid;
    EditText etmenu1, etmenu2, etmenu3, etmenu4, etmenu5;
    EditText etqty1, etqty2, etqty3, etqty4, etqty5;
    TextView tvtotal1, tvtotal2, tvtotal3, tvtotal4, tvtotal5;
    TextView tvtotal, tvkembali;

    Button bttotal, btkembali;
    Button btreset, btsave;

    ImageView ivback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);

        dbHelper = new Database(this);

        etnama = findViewById(R.id.nama_pembeli);

        etmenu1 = findViewById(R.id.menu1);
        etmenu2 = findViewById(R.id.menu2);
        etmenu3 = findViewById(R.id.menu3);
        etmenu4 = findViewById(R.id.menu4);
        etmenu5 = findViewById(R.id.menu5);

        etqty1 = findViewById(R.id.qty1);
        etqty2 = findViewById(R.id.qty2);
        etqty3 = findViewById(R.id.qty3);
        etqty4 = findViewById(R.id.qty4);
        etqty5 = findViewById(R.id.qty5);

        tvtotal1 = findViewById(R.id.total1);
        tvtotal2 = findViewById(R.id.total2);
        tvtotal3 = findViewById(R.id.total3);
        tvtotal4 = findViewById(R.id.total4);
        tvtotal5 = findViewById(R.id.total5);

        tvtotal = findViewById(R.id.bayar);
        etbayar = findViewById(R.id.total_pembayaran);
        tvkembali = findViewById(R.id.kembali);

        ivback = findViewById(R.id.back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bttotal = findViewById(R.id.total);
        bttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int m1 = Integer.parseInt(etmenu1.getText().toString());
                int q1 = Integer.parseInt(etqty1.getText().toString());
                int total1 = m1 * q1;
                tvtotal1.setText(String.valueOf(total1));

                int m2 = Integer.parseInt(etmenu2.getText().toString());
                int q2 = Integer.parseInt(etqty2.getText().toString());
                int total2 = m2 * q2;
                tvtotal2.setText(String.valueOf(total2));

                int m3 = Integer.parseInt(etmenu3.getText().toString());
                int q3 = Integer.parseInt(etqty3.getText().toString());
                int total3 = m3 * q3;
                tvtotal3.setText(String.valueOf(total3));

                int m4 = Integer.parseInt(etmenu4.getText().toString());
                int q4 = Integer.parseInt(etqty4.getText().toString());
                int total4 = m4 * q4;
                tvtotal4.setText(String.valueOf(total4));

                int m5 = Integer.parseInt(etmenu5.getText().toString());
                int q5 = Integer.parseInt(etqty5.getText().toString());
                int total5 = m5 * q5;
                tvtotal5.setText(String.valueOf(total5));

                int totalbeli = total1 + total2 + total3 + total4 + total5;
                tvtotal.setText(String.valueOf(totalbeli));
            }
        });

        btkembali = findViewById(R.id.judul_kembalian);
        btkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bayar = Integer.parseInt(etbayar.getText().toString());
                int totalbeli = Integer.parseInt(tvtotal.getText().toString());
                int kembali = bayar - totalbeli;
                tvkembali.setText(String.valueOf(kembali));
            }
        });

        btreset = findViewById(R.id.btnreset);
        btreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etnama.setText("");

                etmenu1.setText("0");
                etmenu2.setText("0");
                etmenu3.setText("0");
                etmenu4.setText("0");
                etmenu5.setText("0");

                etqty1.setText("0");
                etqty2.setText("0");
                etqty3.setText("0");
                etqty4.setText("0");
                etqty5.setText("0");

                tvtotal1.setText("0");
                tvtotal2.setText("0");
                tvtotal3.setText("0");
                tvtotal4.setText("0");
                tvtotal5.setText("0");

                tvtotal.setText("0");
                etbayar.setText("");
                tvkembali.setText("0");
            }
        });

        etid = findViewById(R.id.id_pesanan);
        btsave = findViewById(R.id.btnsimpan);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into laporan values ('" +
                        etid.getText().toString() + "','" +
                        etnama.getText().toString() + "','" +
                        tvtotal.getText().toString() + "','" +
                        etbayar.getText().toString() + "','" +
                        tvkembali.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                LaporanActivity.la.RefreshListLapo();
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