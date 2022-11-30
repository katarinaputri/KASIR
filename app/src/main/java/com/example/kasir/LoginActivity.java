package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView tvlogin, tvsi, tvkasir, tvuname, tvpass, tvlupapass;
    EditText etuname, etpass;
    Button btlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvlogin = findViewById(R.id.login);
        tvsi = findViewById(R.id.SI);
        tvkasir = findViewById(R.id.Kasir);
        tvuname = findViewById(R.id.judul_uname);
        tvpass = findViewById(R.id.judul_pass);
        tvlupapass = findViewById(R.id.lupa_pass);

        etuname = findViewById(R.id.username);
        etpass = findViewById(R.id.password);

        btlogin = findViewById(R.id.btnlogin);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(login);
            }
        });

        tvlupapass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lpass = new Intent(LoginActivity.this, LupaPassActivity.class);
                startActivity(lpass);
            }
        });
    }
}