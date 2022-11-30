package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LupaPassActivity extends AppCompatActivity {
    Button btnlpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_pass);
        btnlpass = findViewById(R.id.btnlupapass);

        btnlpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backlogin = new Intent(LupaPassActivity.this, LoginActivity.class);
                startActivity(backlogin);
            }
        });
    }
}