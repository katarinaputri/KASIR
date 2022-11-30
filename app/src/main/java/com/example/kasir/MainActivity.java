package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btmenu, btkasir, btlapo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmenu = findViewById(R.id.menu);
        btkasir = findViewById(R.id.kasir);
        btlapo = findViewById(R.id.laporan);

        btmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(MainActivity.this, DaftarMenuActivity.class);
                startActivity(menu);
            }
        });

        btkasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kasir = new Intent(MainActivity.this, KasirActivity.class);
                startActivity(kasir);
            }
        });

        btlapo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lap = new Intent(MainActivity.this, LaporanActivity.class);
                startActivity(lap);
            }
        });

    }
}