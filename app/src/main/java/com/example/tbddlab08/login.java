package com.example.tbddlab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    Context context;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = (Button) findViewById(R.id.btnRGLG);
        Button btnLogin = (Button) findViewById(R.id.btnSignIn);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmailLG);
        EditText txtPassword = (EditText) findViewById(R.id.txtPasswordLG);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    public void openRegister(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void openLoggedIn() {
        Intent intent = new Intent(this, loggedIn.class);
        startActivity(intent);
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("MSG", "onComplete: " + user.getDisplayName());
                    openLoggedIn();
                } else {
                    Log.d("MSG", "onComplete: Failue");
                }
            }
        });
    }
}