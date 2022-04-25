package com.example.tbddlab08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnLogin = (Button) findViewById(R.id.btnWelcomeSignIn);
        Button btnRegister = (Button) findViewById(R.id.btnWelcomeRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this.context, login.class);
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent = new Intent(this.context, register.class);
        startActivity(intent);
    }
}