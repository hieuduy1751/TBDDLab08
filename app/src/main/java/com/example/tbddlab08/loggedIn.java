package com.example.tbddlab08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loggedIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("MSG", "onCreate: " + user.getDisplayName());

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        TextView txtDisplayName = findViewById(R.id.txtDisplayName);

        if(user.getDisplayName() != null) {
            txtDisplayName.setText(user.getDisplayName());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                openWelcome();
            }
        });
    }

    public void openWelcome() {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}