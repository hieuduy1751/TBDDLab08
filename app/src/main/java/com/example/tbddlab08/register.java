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
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.UserDataHandler;

public class register extends AppCompatActivity {
    Context context;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnLogin = (Button) findViewById(R.id.btnSIRG);
        Button btnRegister = (Button) findViewById(R.id.btnRGT);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmailRG);
        EditText txtName = (EditText) findViewById(R.id.txtNameRG);
        EditText txtPassword = (EditText) findViewById(R.id.txtPasswordRG);
        EditText txtCfPassword = (EditText) findViewById(R.id.txtPasswordRG2);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("msg", "onClick: " + txtEmail.getText().toString() + txtPassword.getText().toString());
                register(txtEmail.getText().toString(), txtPassword.getText().toString(), txtName.getText().toString());
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void openLoggedIn() {
        Intent intent = new Intent(this, loggedIn.class);
        startActivity(intent);
    }

    public void register(String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Register", "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(user.getUid() != null) {
                                        openLoggedIn();
                                        Log.d("msg", "onComplete: " + user.getDisplayName());
                                    }
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Register", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }
}